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
import android.widget.EditText;

import com.malfoy.leblanko.villes_emploi.R;

public class Coordonnees extends AppCompatActivity {

    private SharedPreferences settings;
    private EditText adresseEdit,CPEdit,villeEdit,mobileEdit;
    private String adresse,cp,ville,mobile;
    private Button noUpdate,submitUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordonnees);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = getSharedPreferences("PREF1", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        noUpdate = (Button) findViewById(R.id.noUpdate);
        submitUpdate = (Button) findViewById(R.id.submitUpdate);
        adresseEdit = (EditText) findViewById(R.id.adresseEdit);
        CPEdit = (EditText) findViewById(R.id.CPEdit);
        villeEdit = (EditText) findViewById(R.id.villeEdit);
        mobileEdit = (EditText) findViewById(R.id.mobileEdit);

        adresseEdit.setText(settings.getString("adresse",""));
        CPEdit.setText(settings.getString("cp",""));
        villeEdit.setText(settings.getString("ville",""));
        mobileEdit.setText(settings.getString("mobile",""));


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
                adresse = adresseEdit.getText().toString();
                cp = CPEdit.getText().toString();
                ville = villeEdit.getText().toString();
                mobile = mobileEdit.getText().toString();

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("adresse",adresse);
                editor.putString("cp", cp);
                editor.putString("ville",ville);
                editor.putString("mobile",mobile);
                editor.commit();

                Intent goToUpdate = new Intent(Coordonnees.this,UpdateData.class);
                startActivity(goToUpdate);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        //menuInflater.inflate(R.menu.menu_back_main,menu);
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

        if(adresse.isEmpty())
        {
            adresseEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(ville.isEmpty())
        {
            villeEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(cp.isEmpty())
        {
            CPEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(mobile.isEmpty())
        {
            mobileEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }

        return allOk;
    }

}
