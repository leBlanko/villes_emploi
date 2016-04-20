package com.malfoy.leblanko.villes_emploi;

/**
 * Created by leBlanko on 20/04/2016.
 * Cette classe représente une ligne dans la liste des offres
 * >> Représente une offre
 */
public class OffreObject {

    private int logo;
    private String titre,date,ville;

    public OffreObject(int img, String titre, String date, String ville)
    {
        this.setLogo(img);
        this.setDate(date);
        this.setTitre(titre);
        this.setVille(ville);
    }

    public String getVille() {return ville;}
    public String getDate() {return date;}
    public String getTitre() {return titre;}
    public int getLogo() {return logo;}


    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }


}
