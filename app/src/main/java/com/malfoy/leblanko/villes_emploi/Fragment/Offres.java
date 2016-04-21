package com.malfoy.leblanko.villes_emploi.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.malfoy.leblanko.villes_emploi.MyWebViewClient;
import com.malfoy.leblanko.villes_emploi.OffreObject;
import com.malfoy.leblanko.villes_emploi.R;
import com.malfoy.leblanko.villes_emploi.RecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Offres extends Fragment {

    private View view;
    private WebView descView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private String [] titreTab,villeTab,dateTab;
    private int [] logoTab = { R.drawable.adecco};
    private ArrayList <OffreObject> offresList = new ArrayList<OffreObject>();


    public Offres() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_offres, container, false);

        descView = (WebView) view.findViewById(R.id.descOffres);
        descView.setBackgroundColor(getResources().getColor(R.color.background_color1));
        justifyWebViewAndAddLink(descView, R.string.descOffres, R.string.descOffres2);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //On recupere les donnes en local pour l'instant
        titreTab = getResources().getStringArray(R.array.annonces_titles);
        villeTab = getResources().getStringArray(R.array.annonces_city);
        dateTab = getResources().getStringArray(R.array.annonces_dates);

        int i=0;
        for(String title : titreTab)
        {
            OffreObject offreObject = new OffreObject(logoTab[0],title,dateTab[i],villeTab[i]);
            offresList.add(offreObject);
            i++;
        }

        recyclerViewAdapter = new RecyclerAdapter(offresList);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    //Methode qui va permettre de justifier le texte dans la vue principalement
    public void justifyWebViewAndAddLink(WebView webView, int id, int id2)
    {
        String text;
        String getDesc = getResources().getString(id);
        String getDesc2 = getResources().getString(id2);

        text = "<html><body><p align=\"justify\" style=\"color:#262626;\" >";
        text+= getDesc;
        //Il faut ici faire le lien vers le updateCVMAP
        text+= " <a href=\"activity://updatecvmap\">ici.</a> ";
        text+= getDesc2;
        text+= "</p></body></html>";
        webView.loadData(text, "text/html; charset=UTF-8",null);

        //Il faut activer le lien vers une autre activité
        webView.setWebViewClient(new MyWebViewClient(view.getContext()));
    }

}
