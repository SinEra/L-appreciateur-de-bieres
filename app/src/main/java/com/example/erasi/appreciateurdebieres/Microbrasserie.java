package com.example.erasi.appreciateurdebieres;

import android.media.Image;

/**
 * Created by erasi on 2016-10-14.
 */
public class Microbrasserie {

    int id;
    String nom;
    String region;
    int logo;
    String adresse;
    String siteWeb;

    public Microbrasserie(int id, String nom, int logo, String region, String adresse, String siteWeb) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.region = region;
        this.adresse = adresse;
        this.siteWeb = siteWeb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }
}
