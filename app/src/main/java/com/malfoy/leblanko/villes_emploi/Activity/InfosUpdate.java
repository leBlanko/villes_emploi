package com.malfoy.leblanko.villes_emploi.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.malfoy.leblanko.villes_emploi.R;

public class InfosUpdate extends AppCompatActivity {

    private SharedPreferences settings;
    private EditText nomEdit, prenomEdit, emailEdit;
    private String nom,prenom,email;
    private CheckedTextView homme,femme;
    private Button submitUpdate,noUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        settings = getSharedPreferences("PREF1", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nomEdit = (EditText) findViewById(R.id.nomEdit);
        prenomEdit = (EditText) findViewById(R.id.prenomEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        homme = (CheckedTextView) findViewById(R.id.homme);
        femme = (CheckedTextView) findViewById(R.id.femme);
        submitUpdate = (Button) findViewById(R.id.submitUpdate);
        noUpdate = (Button) findViewById(R.id.noUpdate);

        nomEdit.setText(settings.getString("nom",""));
        prenomEdit.setText(settings.getString("prenom",""));
        emailEdit.setText(settings.getString("email",""));

        if(settings.getString("sexe","").equals("Homme"))
            homme.setChecked(true);
        else if(settings.getString("sexe","").equals("Femme"))
            femme.setChecked(true);

        homme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homme.toggle();
                if(femme.isChecked())
                    femme.setChecked(false);
            }
        });

        femme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femme.toggle();
                if(homme.isChecked())
                    homme.setChecked(false);
            }
        });


        noUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(-1); //On previent qu'on a fait un back
                finish();
            }
        });

        submitUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IL faut enregistrer ici les données changés
                nom = nomEdit.getText().toString();
                prenom = prenomEdit.getText().toString();
                email = emailEdit.getText().toString();

                if (checkData()) {
                    //On enregistre dans les preferences

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("nom", nom);
                    editor.putString("prenom", prenom);
                    editor.putString("email", email);

                    if(homme.isChecked())
                        editor.putString("sexe","Homme");
                    else if(femme.isChecked())
                        editor.putString("sexe","Femme");

                    editor.commit();

                    Intent goToUpdate = new Intent(InfosUpdate.this, UpdateData.class);
                    startActivity(goToUpdate);
                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(-1); //On previent qu'on a fait un back
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkData()
    {
        boolean allOk = true;

        if(nom.isEmpty())
        {
            nomEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(prenom.isEmpty())
        {
            prenomEdit.setError(String.format(getString(R.string.errorAskS)));
        }
        if(email.isEmpty())
        {
            emailEdit.setError(String.format(getString(R.string.errorAskS)));
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailEdit.setError(String.format(getString(R.string.errorEmail)));
            allOk = false;
        }
        if(!homme.isChecked() && !femme.isChecked())
        {
            homme.setError(String.format(getString(R.string.errorSexe)));
            allOk=false;
        }

        return allOk;
    }

}
