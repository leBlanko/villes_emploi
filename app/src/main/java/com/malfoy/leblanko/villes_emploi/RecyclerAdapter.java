package com.malfoy.leblanko.villes_emploi;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by leBlanko on 20/04/2016.
 * Adapter de la liste des offres
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<OffreObject> offresList = new ArrayList<OffreObject>();

    public RecyclerAdapter(ArrayList<OffreObject> offresList)
    {
        this.offresList = offresList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        //Il faut récuperer tous les elements de la vue, on fait appel à la classe interne
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        OffreObject offreObject = offresList.get(position);
        //On va set les données recuperer pour mettre dans la vue
        holder.logo.setImageResource(offreObject.getLogo());
        holder.titre.setText(offreObject.getTitre());
        holder.ville.setText(offreObject.getVille());
        holder.date.setText(offreObject.getDate());
    }

    @Override
    public int getItemCount() {
        return this.offresList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView logo;
        TextView titre,date,ville;

        public RecyclerViewHolder(View view)
        {
            super(view);
            logo = (ImageView) view.findViewById(R.id.logo_annonce);
            titre = (TextView) view.findViewById(R.id.titre_annonce);
            date = (TextView) view.findViewById(R.id.date_annonce);
            ville = (TextView) view.findViewById(R.id.ville_annonce);


        }
    }
}
