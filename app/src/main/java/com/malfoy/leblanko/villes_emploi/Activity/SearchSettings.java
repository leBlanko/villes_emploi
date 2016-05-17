package com.malfoy.leblanko.villes_emploi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.malfoy.leblanko.villes_emploi.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchSettings extends AppCompatActivity {

    private WebView desc_inf,desc_without,desc_choice,desc_ville;
    private Switch switch_inf,switch_without;
    private Button askMetier,askVille;
    private AutoCompleteTextView autoCompleteTextView;
    private AutoCompleteTextView autoCompleteTextViewVille;

    private Button submitButton;
    private int colorBack;
    private SearchSettings thisone;
    private AlertDialog dialog;
    private AlertDialog dialogVille;

    private String [] metiers;
    private String [] villes;

    private ImageView add;
    private ImageView addVille;

    private ListView listViewMetier;
    private ListView listViewVille;

    private ArrayList<String> arrayListMetier = new ArrayList<String>();
    private ArrayList<String> arrayListVille = new ArrayList<String>();

    private ArrayAdapter arrayAdapterMetier;
    private ArrayAdapter arrayAdapterVille;

    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        thisone=this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        colorBack = getResources().getColor(R.color.background_color1);

        //Recuperation des données de la vue (XML)
        desc_inf = (WebView) findViewById(R.id.desc_inf);
        switch_inf = (Switch) findViewById(R.id.switch_inf);
        desc_without = (WebView) findViewById(R.id.desc_without);
        desc_ville = (WebView) findViewById(R.id.desc_ville);
        switch_without = (Switch) findViewById(R.id.switch_without);
        desc_choice = (WebView) findViewById(R.id.desc_choice);
        askMetier = (Button) findViewById(R.id.askMetier);
        askVille = (Button) findViewById(R.id.askVille);

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        switch_inf.setChecked(sharedPref.getBoolean("infCheck", false));
        switch_without.setChecked(sharedPref.getBoolean("withoutCheck", false));

        //La fenetre de dialogue pour les metiers
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        //On lui donne en parametre notre layout
        final View dialogView = inflater.inflate(R.layout.ask_metiers,null);

        builder
                .setView(dialogView)

                .setPositiveButton(R.string.submitSearch, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        Set<String> setMetier = new HashSet<String>();
                        setMetier.addAll(arrayListMetier);
                        editor.putStringSet("listMetier", setMetier);
                        editor.commit();

                    }
                });

        dialog = builder.create();


        //On recupere les donnees du dialog
        autoCompleteTextView = (AutoCompleteTextView) dialogView.findViewById(R.id.autoCompleteMetier);
        metiers = getResources().getStringArray(R.array.annonces_titles);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,metiers);
        autoCompleteTextView.setAdapter(adapter);

        arrayAdapterMetier = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayListMetier);
        listViewMetier = (ListView) dialogView.findViewById(R.id.listViewMetier);
        listViewMetier.setAdapter(arrayAdapterMetier);


        //Click sur la task l efface
        listViewMetier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                arrayListMetier.remove(position);
                arrayAdapterMetier.notifyDataSetChanged();
            }
        });

        add = (ImageView) dialogView.findViewById(R.id.submitMetier);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = autoCompleteTextView.getText().toString();
                //On va verifier si le metier correspond à un metier de la bdd, puis si il n est pas deja ajouté
                //Id 0 correspond aux metiers
                if (exist(m,0) && !alreadyPresent(m,0)) {
                    arrayListMetier.add(m);
                    arrayAdapterMetier.notifyDataSetChanged();
                }
            }
        });





        //La fenetre de dialogue pour les villes
        final AlertDialog.Builder builderVille = new AlertDialog.Builder(this);
        //On lui donne en parametre notre layout
        final View dialogViewVille = inflater.inflate(R.layout.ask_villes,null);

        builderVille
                .setView(dialogViewVille)

                .setPositiveButton(R.string.submitSearch, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        Set<String> setVille = new HashSet<String>();
                        setVille.addAll(arrayListVille);
                        editor.putStringSet("listVille", setVille);
                        editor.commit();

                    }
                });

        dialogVille = builderVille.create();

        //On recupere les donnees du dialog
        autoCompleteTextViewVille = (AutoCompleteTextView) dialogViewVille.findViewById(R.id.autoCompleteVille);
        villes = getResources().getStringArray(R.array.annonces_city);
        ArrayAdapter<String> adapterVille = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,villes);
        autoCompleteTextViewVille.setAdapter(adapterVille);

        arrayAdapterVille = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayListVille);
        listViewVille = (ListView) dialogViewVille.findViewById(R.id.listViewVille);
        listViewVille.setAdapter(arrayAdapterVille);


        //Click sur la task l efface
        listViewVille.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                arrayListVille.remove(position);
                arrayAdapterVille.notifyDataSetChanged();
            }
        });

        addVille = (ImageView) dialogViewVille.findViewById(R.id.submitVille);

        addVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = autoCompleteTextViewVille.getText().toString();
                //On va verifier si le metier correspond à un metier de la bdd, puis si il n est pas deja ajouté
                //Id 1 correspond aux villes
                if (exist(m,1) && !alreadyPresent(m,1)) {
                    arrayListVille.add(m);
                    arrayAdapterVille.notifyDataSetChanged();
                }
            }
        });






        //On defini les differents background
        desc_inf.setBackgroundColor(colorBack);
        desc_without.setBackgroundColor(colorBack);
        desc_choice.setBackgroundColor(colorBack);
        desc_ville.setBackgroundColor(colorBack);

        submitButton = (Button) findViewById(R.id.submit_search);

        //On affiche le texte correspondant justifié
        justifyWebView(desc_inf, R.string.text_inf);
        justifyWebView(desc_without, R.string.text_without);
        justifyWebView(desc_choice, R.string.text_choice);
        justifyWebView(desc_ville,R.string.villes_choice);

        askMetier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> setMetier = new HashSet<String>();
                setMetier = sharedPref.getStringSet("listMetier", null);
                arrayListMetier.clear();
                if (setMetier != null)
                    arrayListMetier.addAll(setMetier);
                dialog.show();
            }
        });

        askVille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Set<String> setVille = new HashSet<String>();
                setVille = sharedPref.getStringSet("listVille", null);
                arrayListVille.clear();
                if (setVille != null)
                    arrayListVille.addAll(setVille);
                dialogVille.show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On recupere ici les preferences
                boolean infCheck = switch_inf.isChecked();
                boolean withoutCheck = switch_without.isChecked();

                //On enregistre dans les preferences
                sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("infCheck", infCheck);
                editor.putBoolean("withoutCheck", withoutCheck);
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
        webView.loadData(text, "text/html; charset=UTF-8", null);
    }

    public boolean alreadyPresent(String s,int id)
    {
        switch (id)
        {
            case 0:
                for(int i=0; i<arrayListMetier.size();i++)
                    if(arrayListMetier.get(i).equals(s))
                        return true;
                return false;

            case 1:
                for(int i=0; i<arrayListVille.size();i++)
                    if(arrayListVille.get(i).equals(s))
                        return true;
                return false;
        }

        return false;
    }

    public boolean exist(String s, int id)
    {
        switch (id)
        {
            case 0:
                for(int i=0; i<metiers.length;i++)
                    if(metiers[i].equals(s))
                        return true;
                return false;

            case 1:
                for(int i=0; i<villes.length;i++)
                    if(villes[i].equals(s))
                        return true;
                return false;
        }
        return false;
    }

}
