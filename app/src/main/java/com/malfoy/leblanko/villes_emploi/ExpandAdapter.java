package com.malfoy.leblanko.villes_emploi;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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
        String title = (String) this.getChild(groupPosition,childPosition);
        if(convertView==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_layout,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.child_item);
        textView.setText(title);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
