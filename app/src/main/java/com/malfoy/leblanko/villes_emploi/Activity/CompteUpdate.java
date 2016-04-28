package com.malfoy.leblanko.villes_emploi.Activity;

import android.content.Context;
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

public class CompteUpdate extends AppCompatActivity {

    private EditText pseudoEdit,passswordEdit,verifPasswordEdit;
    private String pseudo, password,verifPassword;
    private Button submitUpdate,noUpdate;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = getSharedPreferences("PREF1", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        pseudoEdit = (EditText) findViewById(R.id.pseudoEdit);
        passswordEdit = (EditText) findViewById(R.id.passwordEdit);
        verifPasswordEdit = (EditText) findViewById(R.id.verifPasswordEdit);
        submitUpdate = (Button) findViewById(R.id.submitUpdate);
        noUpdate = (Button) findViewById(R.id.noUpdate);

        pseudoEdit.setText(settings.getString("pseudo", ""));

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
                pseudo = pseudoEdit.getText().toString();
                password = passswordEdit.getText().toString();
                verifPassword = verifPasswordEdit.getText().toString();
                if(checkData())
                {
                    //On enregistre dans les preferences

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("pseudo",pseudo);
                    editor.putString("password", password);
                    editor.commit();

                    Intent goToUpdate = new Intent(CompteUpdate.this,UpdateData.class);
                    startActivity(goToUpdate);
                }

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

        if(pseudo.isEmpty())
        {
            pseudoEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(password.isEmpty() && !verifPassword.isEmpty())
        {
            passswordEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(verifPassword.isEmpty() && !password.isEmpty())
        {
            verifPasswordEdit.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }

        if(!isPasswordValid(password) && (!password.isEmpty() || !verifPassword.isEmpty()))
        {
            passswordEdit.setError(String.format(getString(R.string.errorPassword)));
            allOk = false;
        }
        //Verification password identiques
        if(!password.equals(verifPassword))
        {
            verifPasswordEdit.setError(String.format(getString(R.string.errorVerifPassword)));
            allOk = false;
        }

        return allOk;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 5;
    }

}
