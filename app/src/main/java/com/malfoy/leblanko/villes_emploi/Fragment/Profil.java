package com.malfoy.leblanko.villes_emploi.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.malfoy.leblanko.villes_emploi.R;


public class Profil extends Fragment {


    private WebView descView;

    public Profil() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        descView = (WebView) view.findViewById(R.id.descProfil);
        descView.setBackgroundColor(getResources().getColor(R.color.focusButton));
        justifyWebView(descView, R.string.descProfil);


        return view;
    }

    //Methode qui va permettre de justifier le texte dans la vue
    public void justifyWebView(WebView webView, int id)
    {
        String text;
        String getDesc = getResources().getString(id);
        text = "<html><body><p align=\"justify\" style=\"color:white;\" >";
        text+= getDesc;
        text+= "</p></body></html>";
        webView.loadData(text, "text/html; charset=UTF-8",null);
    }

}
