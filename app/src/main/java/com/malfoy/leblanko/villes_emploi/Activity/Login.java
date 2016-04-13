package com.malfoy.leblanko.villes_emploi.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.malfoy.leblanko.villes_emploi.R;
import com.malfoy.leblanko.villes_emploi.Slider.MainSwipe;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    public static final String PREFS_NAME = "Infos";
    public static final int MIN_SLIDE=150;
    public SharedPreferences prefs;
    private Login thisone;
    private EditText emailXml;
    private EditText passwordXml;
    private Button loginButton;
    private LoginButton fbButton;
    private CallbackManager callbackManager;
    private TextView forgotPassword;
    private TextView goToInscription;
    private String email;
    private String password;
    private String nameB="";
    private String emailB="";
    private String genderB="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisone=this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences(PREFS_NAME, 1);

        //On récupère les différents objets de la Vue
        emailXml = (EditText) findViewById(R.id.email);
        passwordXml = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.submitLogin);
        fbButton = (LoginButton) findViewById(R.id.login_button_facebook);
        forgotPassword = (TextView) findViewById(R.id.forgotpassword_link);
        goToInscription = (TextView) findViewById(R.id.signup_link);

        //On determine les permissions de facebook
        fbButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        //action sur le bouton de connexion a facebook
        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("COnnecté");

                //On va récuperer les differentes données comme le nom, l'email, pour les utiliser par la suite
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            //Ici pour recuperer les données
                            nameB = object.getString("name");
                            genderB = object.getString("gender");
                            emailB = object.getString("email");

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("name", nameB);
                            editor.putString("gender", genderB);
                            editor.putString("email", emailB);
                            editor.commit();

                            Intent inscriptionIntent = new Intent(Login.this, Inscription.class);
                            startActivity(inscriptionIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();



            }

            @Override
            public void onCancel() {
                System.out.println("A quitté");
            }

            @Override
            public void onError(FacebookException e) {
                System.out.println("Erreur connection");
            }
        });

        //Quand l'utilisateur va cliquer sur Se connecter
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                email = emailXml.getText().toString();
                password = passwordXml.getText().toString();

                //Si il verifie la condition, alors on pourra exploiter les données
                if(checkData())
                {
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                            R.style.Base_Theme_AppCompat_Light_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage(getString(R.string.progressLogin));
                    progressDialog.show();

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    //On va executer ici le test de connexion
                                    progressDialog.dismiss();
                                }
                            }, 3000);

                    //On choisis la page à se diriger juste apres que la connexion est marché
                    Intent categorie1 = new Intent(Login.this, MainMenu.class);
                    startActivity(categorie1);
                }
            }
        });

        //On va gérer ici les événements lié à l'oubli de mot de passe
        forgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Mot de passe oublié");
            }
        });

        //Quand l'utilisateur va cliquer sur Pas de compte
        goToInscription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Il faut aller sur l'activité d'inscription
                Intent inscriptionIntent = new Intent(Login.this, Inscription.class);
                startActivity(inscriptionIntent);
                thisone.overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left2);

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

    //Methode qui va verifier plusieurs choses avant d'exploiter les données
    public boolean checkData()
    {
        boolean allOk=true;

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailXml.setError(String.format(getString(R.string.errorEmail)));
            allOk = false;
        }
        if(password.isEmpty() || !isPasswordValid(password))
        {
            passwordXml.setError(String.format(getString(R.string.errorPassword)));
            allOk = false;
        }

        return allOk;
    }

    public void loginFailed()
    {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(false);
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 5;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
