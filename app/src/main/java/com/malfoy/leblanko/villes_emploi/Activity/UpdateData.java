package com.malfoy.leblanko.villes_emploi.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.malfoy.leblanko.villes_emploi.R;

import org.w3c.dom.Text;

public class UpdateData extends AppCompatActivity {

    private View compte,infos,coordonnees;
    private TextView compteSub, infosSub, coordonneesSub;
    private TextView pseudoV,nomV,prenomV,emailV,sexeV,mobileV,adresseV,cpV,villeV;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = getSharedPreferences("PREF1", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        compte = (View) findViewById(R.id.compte_include);
        infos = (View) findViewById(R.id.infos_perso_include);
        coordonnees = (View) findViewById(R.id.coordonnees_include);

        compteSub = (TextView) compte.findViewById(R.id.submit_modifier);
        infosSub = (TextView) infos.findViewById(R.id.submit_modifier);
        coordonneesSub = (TextView) coordonnees.findViewById(R.id.submit_modifier);

        pseudoV = (TextView) findViewById(R.id.pseudoV);
        nomV = (TextView) findViewById(R.id.nomV);
        prenomV = (TextView) findViewById(R.id.prenomV);
        emailV = (TextView) findViewById(R.id.emailV);
        sexeV = (TextView) findViewById(R.id.sexeV);
        mobileV = (TextView) findViewById(R.id.mobileV);
        adresseV = (TextView) findViewById(R.id.adresseV);
        cpV = (TextView) findViewById(R.id.cpV);
        villeV = (TextView) findViewById(R.id.villeV);


        pseudoV.setText(settings.getString("pseudo", ""));
        nomV.setText(settings.getString("nom",""));
        prenomV.setText(settings.getString("prenom",""));
        emailV.setText(settings.getString("email",""));
        sexeV.setText(settings.getString("sexe",""));
        mobileV.setText(settings.getString("mobile",""));
        adresseV.setText(settings.getString("adresse",""));
        cpV.setText(settings.getString("cp",""));
        villeV.setText(settings.getString("ville",""));

        compteSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent compteUpdate = new Intent(UpdateData.this, CompteUpdate.class);
                startActivity(compteUpdate);
            }
        });

        infosSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infosUpdate = new Intent(UpdateData.this, InfosUpdate.class);
                startActivity(infosUpdate);
            }
        });

        coordonneesSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent coordonnees = new Intent(UpdateData.this, Coordonnees.class);
                startActivity(coordonnees);
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

}
