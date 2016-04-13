package com.malfoy.leblanko.villes_emploi.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.malfoy.leblanko.villes_emploi.R;

public class Inscription extends AppCompatActivity {

    private EditText prenomXml,nomXml,pseudoXml,emailXml,codePostalXml,passwordXml,verifPasswordXml;
    private RadioButton homme,femme;
    private Button submitInscription;
    private String prenom,nom,pseudo,email,codePostal,password,verifPassword;
    private Bundle bundle;
    private String nameB,genderB,emailB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insciption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = getSharedPreferences(Login.PREFS_NAME,1);
        nameB = prefs.getString("name","");
        genderB = prefs.getString("gender","X");
        emailB = prefs.getString("email","");



        //Recuperation infos du XML (la vue)
        prenomXml = (EditText) findViewById(R.id.prenom);
        nomXml = (EditText) findViewById(R.id.nom);
        pseudoXml = (EditText) findViewById(R.id.pseudo);
        emailXml  = (EditText) findViewById(R.id.email);
        codePostalXml = (EditText) findViewById(R.id.CP);
        passwordXml = (EditText) findViewById(R.id.password);
        verifPasswordXml = (EditText) findViewById(R.id.verifPassword);

        homme = (RadioButton) findViewById(R.id.homme);
        femme = (RadioButton) findViewById(R.id.femme);
        submitInscription = (Button) findViewById(R.id.submitInscription);

        //On va remplir les champs avec les infos facebook/twitter
        if(!nameB.equals(""))
        {
            String [] parts = nameB.split(" ");
            prenomXml.setText(parts[0]);
            nomXml.setText(parts[1]);
        }
        if(!genderB.equals(""))
        {
            if(genderB.equals("male"))
                homme.setChecked(true);
            else if(genderB.equals("female"))
                femme.setChecked(true);
            else
            {
                homme.setChecked(false);
                femme.setChecked(false);
            }
        }
        if(!emailB.equals(""))
        {
            emailXml.setText(emailB);
        }

        //Au click sur le bouton s'inscrire
        submitInscription.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //On recupere les valeurs
                prenom = prenomXml.getText().toString();
                nom = nomXml.getText().toString();
                pseudo = pseudoXml.getText().toString();
                email = emailXml.getText().toString();
                codePostal = codePostalXml.getText().toString();
                password = passwordXml.getText().toString();
                verifPassword = verifPasswordXml.getText().toString();

                if(checkData())
                {
                    //Si tout est ok on peut créer le compte
                    final ProgressDialog progressDialog = new ProgressDialog(Inscription.this,
                            R.style.Base_Theme_AppCompat_Light_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage(getString(R.string.progressInscription));
                    progressDialog.show();

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    progressDialog.dismiss();
                                    //Il faudra ici envoyer les infos à la BDD
                                }
                            }, 3000);
                }
                //On repart vers la page au choix
                Intent inscriptionIntent = new Intent(Inscription.this, Login.class);
                startActivity(inscriptionIntent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean checkData()
    {
        boolean allOk = true;

        if(!homme.isChecked() && !femme.isChecked()) {
            femme.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }

        //Verification que tous les champs soient remplis
        if(prenom.isEmpty())
        {
            prenomXml.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(nom.isEmpty())
        {
            nomXml.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(pseudo.isEmpty())
        {
            pseudoXml.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(email.isEmpty())
        {
            emailXml.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(codePostal.isEmpty())
        {
            codePostalXml.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(password.isEmpty())
        {
            passwordXml.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }
        if(verifPassword.isEmpty())
        {
            verifPasswordXml.setError(String.format(getString(R.string.errorAskS)));
            allOk = false;
        }

        if(!isPasswordValid(password))
        {
            passwordXml.setError(String.format(getString(R.string.errorPassword)));
            allOk = false;
        }
        //Verification password identiques
        if(!password.equals(verifPassword))
        {
            verifPasswordXml.setError(String.format(getString(R.string.errorVerifPassword)));
            allOk = false;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailXml.setError(String.format(getString(R.string.errorEmail)));
            allOk = false;
        }



        return allOk;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 5;
    }

}
