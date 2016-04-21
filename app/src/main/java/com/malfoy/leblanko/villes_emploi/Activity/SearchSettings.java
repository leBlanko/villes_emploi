package com.malfoy.leblanko.villes_emploi.Activity;

import android.content.Context;
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
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.malfoy.leblanko.villes_emploi.R;

public class SearchSettings extends AppCompatActivity {

    private WebView desc_inf,desc_without,desc_choice;
    private Switch switch_inf,switch_without,switch_choice;
    private AutoCompleteTextView autoCompleteTextView;
    private Button submitButton;
    private int colorBack;

    private SharedPreferences sharedPref;

    private String [] list_metiers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        colorBack = getResources().getColor(R.color.background_color1);

        desc_inf = (WebView) findViewById(R.id.desc_inf);
        switch_inf = (Switch) findViewById(R.id.switch_inf);
        desc_without = (WebView) findViewById(R.id.desc_without);
        switch_without = (Switch) findViewById(R.id.switch_without);
        desc_choice = (WebView) findViewById(R.id.desc_choice);
        switch_choice = (Switch) findViewById(R.id.switch_choice);

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        switch_inf.setChecked(sharedPref.getBoolean("infCheck", false));
        switch_without.setChecked(sharedPref.getBoolean("withoutCheck", false));
        switch_choice.setChecked(sharedPref.getBoolean("choiceCheck", false));

        //autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_complete_metier);
        //list_metiers = getResources().getStringArray(R.array.list_metiers);
        //ArrayAdapter<String> metierAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_metiers);
        //autoCompleteTextView.setAdapter(metierAdapter);

        desc_inf.setBackgroundColor(colorBack);
        desc_without.setBackgroundColor(colorBack);
        desc_choice.setBackgroundColor(colorBack);

        submitButton = (Button) findViewById(R.id.submit_search);

        justifyWebView(desc_inf, R.string.text_inf);
        justifyWebView(desc_without, R.string.text_without);
        justifyWebView(desc_choice, R.string.text_choice);


        switch_choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Il faut faire apparaitre ici la barre de recherche des metiers
                } else {
                    //Faire disparaitre la barre de recherche et les metiers associés ( les sauvegarder quand meme)
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On recupere ici les preferences
                boolean infCheck = switch_inf.isChecked();
                boolean withoutCheck = switch_without.isChecked();
                boolean choiceCheck = switch_choice.isChecked();

                //On enregistre dans les preferences
                sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("infCheck", infCheck);
                editor.putBoolean("withoutCheck", withoutCheck);
                editor.putBoolean("choiceCheck", choiceCheck);
                editor.commit();

                finish();
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

    //Methode qui va permettre de justifier le texte dans la vue
    public void justifyWebView(WebView webView, int id)
    {
        String text;
        String getDesc = getResources().getString(id);
        text = "<html><body><p align=\"justify\" style=\"color:#262626;\" >";
        text+= getDesc;
        text+= "</p></body></html>";
        webView.loadData(text, "text/html; charset=UTF-8",null);
    }
}
