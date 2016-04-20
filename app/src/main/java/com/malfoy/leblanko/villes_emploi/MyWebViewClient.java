package com.malfoy.leblanko.villes_emploi;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.malfoy.leblanko.villes_emploi.Activity.UpdateCVMap;

/**
 * Created by leBlanko on 20/04/2016.
 * Methode qui va permettre de convertir un lien hypertext en action android
 * Ici on va detecter un certain type de <a href> </a> en identifiant le lien
 * Transformation de celui ci en changement d'activité dans l'application
 */
public class MyWebViewClient extends WebViewClient {

    private Context context;

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.equals("activity://updatecvmap")) {

            Intent goToUpdateCVMap = new Intent(context, UpdateCVMap.class);
            context.startActivity(goToUpdateCVMap);
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }
}
