package com.malfoy.leblanko.villes_emploi;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by leBlanko on 18/04/16.
 */
public class ExpandAdapter extends BaseExpandableListAdapter {

    private List<String> head_titles;
    private HashMap<String, List<String>> child_titles;
    private Context context;

    public ExpandAdapter(Context context, List<String> head_titles, HashMap<String, List<String>> child_titles)
    {
        this.context = context;
        this.head_titles = head_titles;
        this.child_titles = child_titles;
    }

    //Recupere la taille du groupe (parent)
    @Override
    public int getGroupCount() {
        return this.head_titles.size();
    }

    //Recupere le nombre d enfant du parent donne en parametre
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.child_titles.get(head_titles.get(groupPosition)).size();
    }

    //Recupere le groupe avec l'id donnee
    @Override
    public Object getGroup(int groupPosition) {
        return head_titles.get(groupPosition);
    }

    //Recupere l'enfant grace a son id du parent donné en parametre
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles.get(head_titles.get(groupPosition)).get(childPosition);
    }

    //Renvoi l'id du groupe(parent)
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //Renvoi l'id de l'enfant grace a son id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);
        if(convertView==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_layout,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.head_item);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String title = (String) this.getChild(groupPosition, childPosition);
        //if(convertView==null)
        //{
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(groupPosition == 0)
            {
                //On est dans les points forts
                convertView = layoutInflater.inflate(R.layout.pointfort_layout,null);
            }
            else if(groupPosition == 3)
            {
                //On est dans les conseils perso
                convertView = layoutInflater.inflate(R.layout.conseilperso_layout,null);
            }
            else if(groupPosition == 4)
            {
                //On est dans le coach
                convertView = layoutInflater.inflate(R.layout.coach_layout, null);
            }
            else
            {
                //On est dans une liste basique
                convertView = layoutInflater.inflate(R.layout.child_layout,null);
            }

        //}

        if(groupPosition == 0)
        {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.categorie);
            if(childPosition == 0)
                imageView.setImageResource(R.drawable.imaginationfull);
            else if(childPosition == 1)
                imageView.setImageResource(R.drawable.reflexionfull);
            else if(childPosition == 2)
                imageView.setImageResource(R.drawable.technicitefull);
        }
        else if(groupPosition == 3)
        {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.categorie);
            if(childPosition == 0)
                imageView.setImageResource(R.drawable.conseilpersotechnicitefull);
        }
        else if(groupPosition == 4)
        {
            // Partie Coach
            WebView webView = (WebView) convertView.findViewById(R.id.bilan_desc);
            Button button = (Button) convertView.findViewById(R.id.submit_coach);

            makeWebView(webView,R.string.bilan_desc,convertView);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
        else
        {
            TextView textView = (TextView) convertView.findViewById(R.id.child_item);
            textView.setText(title);
        }

        return convertView;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void makeWebView(WebView webView, int id, View view)
    {
        String text;
        String getDesc = view.getResources().getString(id);
        text = "<html><body>";
        text += "<p style=\" font-size:16px; color:#777979; background-color:#eef1f5; margin:-10; padding: 15px 40px 15px 40px;\">";
        text += getDesc;
        text += "</p>";
        text+= "</body></html>";
        webView.loadData(text, "text/html; charset=UTF-8",null);
    }
}
