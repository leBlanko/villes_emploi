package com.malfoy.leblanko.villes_emploi.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.malfoy.leblanko.villes_emploi.Activity.Coach;
import com.malfoy.leblanko.villes_emploi.ExpandAdapter;
import com.malfoy.leblanko.villes_emploi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Profil extends Fragment {


    private WebView descView;
    private Button coachSubmit;
    private ExpandableListView expandableListView;
    private List<String> Headings,L1;
    private HashMap<String, List<String>> ChildList;
    private String [] heading_items;
    private String [] l1;

    private List<String> strongPointList;
    private List<String> bestWordsList;
    private List<String> betterTaskList;
    private List<String> personnalAdviceList;

    private String [] splTab;
    private String [] bwlTab;
    private String [] btlTab;
    private String [] palTab;

    public Profil() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profil, container, false);
        descView = (WebView) view.findViewById(R.id.descProfil);
        coachSubmit = (Button) view.findViewById(R.id.coachSubmit);
        descView.setBackgroundColor(getResources().getColor(R.color.background_color1));
        justifyWebView(descView, R.string.descProfil);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expand_1);
        Headings = new ArrayList<String>();
        ChildList = new HashMap<String, List<String>>();
        heading_items = getResources().getStringArray(R.array.head_titles);

        strongPointList = new ArrayList<String>();
        bestWordsList = new ArrayList<String>();
        betterTaskList = new ArrayList<String>();
        personnalAdviceList = new ArrayList<String>();


        /*SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        boolean infCheck = sharedPref.getBoolean("infCheck", false);
        boolean withoutCheck = sharedPref.getBoolean("withoutCheck", false);
        boolean choiceCheck = sharedPref.getBoolean("choiceCheck", false);
        System.out.println("infCheck: "+infCheck + " withoutCheck: "+withoutCheck+ " choice: "+ choiceCheck);*/

        splTab = new String[5];
        for(int i=0; i<splTab.length;i++)
            splTab[i] = "Point fort "+(i+1);

        bwlTab = new String[5];
        for(int i=0; i<bwlTab.length;i++)
            bwlTab[i] = "Verbe "+(i+1);

        btlTab = new String[5];
        for(int i=0; i<btlTab.length;i++)
            btlTab[i] = "Tâche "+(i+1);

        palTab = new String[5];
        for(int i=0; i<palTab.length;i++)
            palTab[i] = "Conseil "+(i+1);


        for(String title: heading_items)
        {
            Headings.add(title);
        }

        for(String title : splTab)
        {
            strongPointList.add(title);
        }
        for(String title: bwlTab)
        {
            bestWordsList.add(title);
        }
        for(String title: btlTab)
        {
            betterTaskList.add(title);
        }
        for(String title: palTab)
        {
            personnalAdviceList.add(title);
        }

        ChildList.put(Headings.get(0), strongPointList);
        ChildList.put(Headings.get(1), bestWordsList);
        ChildList.put(Headings.get(2), betterTaskList);
        ChildList.put(Headings.get(3), personnalAdviceList);

        ExpandAdapter expandAdapter = new ExpandAdapter(container.getContext(),Headings,ChildList);
        expandableListView.setAdapter(expandAdapter);

        coachSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCoach = new Intent(view.getContext(), Coach.class);
                startActivity(goToCoach);
            }
        });


        return view;
    }

    //Methode qui va permettre de justifier le texte dans la vue
    public void justifyWebView(WebView webView, int id)
    {
        String text;
        String getDesc = getResources().getString(id);
        text = "<html><body>";
        //text += getBackground();
        text += "<p align=\"justify\" style=\"color:#262626;\" >";
        text += getDesc;
        text += "</p>";
        //text += "</div></div>";
        text+= "</body></html>";
        webView.loadData(text, "text/html; charset=UTF-8",null);
    }

    public String getBackground()
    {
        String text = "<div id=\"Stage_Rectangle\" class=\"Stage_Rectangle_id\" style=\"position: absolute; margin: 0px; left: 0px; top: 0px; width: 980px; height: 1669px; right: auto; bottom: auto; border: 0px none rgb(0, 0, 0); -webkit-tap-highlight-color: rgba(0, 0, 0, 0); transform: translateZ(0px); background-image: linear-gradient(rgb(104, 207, 245) 0%, rgb(15, 126, 188) 49%); background-color: rgb(255, 255, 255); background-size: 100% 100%;\"></div>";
        text += "<div id=\"Stage_grille_diago_blanc_2\" class=\"Stage_grille_diago_blanc_2_id\" style=\"position: absolute; margin: 0px; opacity: 0.2; left: -1px; top: 0px; width: 981px; height: 2513px; right: auto; bottom: auto; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); transform: translateZ(0px); background-image: url(&quot;images/grille_diago_blanc_2.svg&quot;); background-color: rgba(0, 0, 0, 0); background-size: 100% 100%; background-position: 0px 0px; background-repeat: no-repeat;\"></div>";
        return text;
    }

}
