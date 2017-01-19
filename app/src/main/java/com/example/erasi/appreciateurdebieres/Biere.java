package com.example.erasi.appreciateurdebieres;

import android.media.Image;

/**
 * Created by erasi on 2016-10-14.
 */
public class Biere {

    int id;
    String nom;
    int microbrasserie;
    String type;
    float niveauAlcool;
    String commentaire;
    float note;

    public Biere(int id, String nom, int microbrasserie, String type, float niveauAlcool, String commentaire, float note) {
        this.id = id;
        this.nom = nom;
        this.microbrasserie = microbrasserie;
        this.type = type;
        this.niveauAlcool = niveauAlcool;
        this.commentaire = commentaire;
        this.note = note;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMicrobrasserie() {
        return microbrasserie;
    }

    public void setMicrobrasserie(int microbrasserie) {
        this.microbrasserie = microbrasserie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getNiveauAlcool() {
        return niveauAlcool;
    }

    public void setNiveauAlcool(float niveauAlcool) {
        this.niveauAlcool = niveauAlcool;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
