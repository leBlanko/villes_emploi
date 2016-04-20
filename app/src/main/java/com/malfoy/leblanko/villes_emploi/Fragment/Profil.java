package com.malfoy.leblanko.villes_emploi.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.malfoy.leblanko.villes_emploi.ExpandAdapter;
import com.malfoy.leblanko.villes_emploi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Profil extends Fragment {


    private WebView descView;

    private ExpandableListView expandableListView;
    private List<String> Headings,L1;
    private HashMap<String, List<String>> ChildList;
    private String [] heading_items;
    private String [] l1;

    private List<String> strongPointList;
    private List<String> bestWordsList;
    private List<String> betterTaskList;
    private List<String> personnalAdviceList;
    private List<String> coachList;

    private String [] splTab;
    private String [] bwlTab;
    private String [] btlTab;
    private String [] palTab;
    private String [] clTab;

    public Profil() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        descView = (WebView) view.findViewById(R.id.descProfil);
        descView.setBackgroundColor(getResources().getColor(R.color.focusButton));
        justifyWebView(descView, R.string.descProfil);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expand_1);
        Headings = new ArrayList<String>();
        ChildList = new HashMap<String, List<String>>();
        heading_items = getResources().getStringArray(R.array.head_titles);

        strongPointList = new ArrayList<String>();
        bestWordsList = new ArrayList<String>();
        betterTaskList = new ArrayList<String>();
        personnalAdviceList = new ArrayList<String>();
        coachList = new ArrayList<String>();

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

        clTab = new String[5];
        for(int i=0; i<clTab.length;i++)
            clTab[i] = "Coach conseil "+(i+1);


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
        for(String title: clTab)
        {
            coachList.add(title);
        }

        ChildList.put(Headings.get(0), strongPointList);
        ChildList.put(Headings.get(1), bestWordsList);
        ChildList.put(Headings.get(2), betterTaskList);
        ChildList.put(Headings.get(3), personnalAdviceList);
        ChildList.put(Headings.get(4), coachList);

        ExpandAdapter expandAdapter = new ExpandAdapter(container.getContext(),Headings,ChildList);
        expandableListView.setAdapter(expandAdapter);

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
