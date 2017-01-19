package com.example.erasi.appreciateurdebieres;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erasi on 2016-10-28.
 */
public class BiereDAO extends DAOBase{

    public static final String TABLE_NAME = "Biere";
    public static final String KEY = "_id";
    public static final String NOM = "nom";
    public static final String MICRO = "microbrasserie";
    public static final String TYPE = "type";
    public static final String NIVEAU = "niveau_alcool";
    public static final String COMMENTAIRE = "commentaire";
    public static final String NOTE = "note";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " " +
            "(" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOM + " TEXT, " +
            MICRO + " INTEGER," +
            TYPE + " TEXT," +
            NIVEAU + " FLOAT," +
            COMMENTAIRE + " TEXT," +
            NOTE + " FLOAT" + ");";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public BiereDAO(Context context) {
        super(context);
    }

    public void ajouter(Biere b){
        ContentValues value = new ContentValues();
        value.put(NOM, b.getNom());
        value.put(MICRO, b.getMicrobrasserie());
        value.put(TYPE, b.getType());
        value.put(NIVEAU, b.getNiveauAlcool());
        value.put(COMMENTAIRE, b.getCommentaire());
        value.put(NOTE, b.getNote());
        open();
        db.insert(TABLE_NAME, null, value);;
    };

    public void changer(Biere b){
        ContentValues value = new ContentValues();
        value.put(NOM, b.getNom());
        value.put(MICRO, b.getMicrobrasserie());
        value.put(TYPE, b.getType());
        value.put(NIVEAU, b.getNiveauAlcool());
        value.put(COMMENTAIRE, b.getCommentaire());
        value.put(NOTE, b.getNote());
        open();
        db.update(TABLE_NAME, value, KEY + " = ?", new String[] {String.valueOf(b.getId())});;
    };

    public Cursor selectionnerTout(){

        SQLiteDatabase db = open();

        Cursor curseur = db.rawQuery("SELECT " + KEY + ", " + NOM + ", " + MICRO + ", " + TYPE
                + ", " + NIVEAU + ", " + COMMENTAIRE + ", " + NOTE + " FROM " + TABLE_NAME, null);

        return curseur;
    };

    public Cursor selectionnerParMicro(int microId){

        SQLiteDatabase db = open();

        Cursor curseur = db.rawQuery("SELECT " + KEY + ", " + NOM + ", " + MICRO + ", " + TYPE
                + ", " + NIVEAU + ", " + COMMENTAIRE + ", " + NOTE + " FROM " + TABLE_NAME
                + " WHERE " + MICRO + " = ?",
                new String[]{Integer.toString(microId)});

        return curseur;
    };

    public Biere fromCurseur(Cursor curseur) {
        Biere b = new Biere(curseur.getInt(0), curseur.getString(1), curseur.getInt(2), curseur.getString(3),
                            curseur.getFloat(4), curseur.getString(5), curseur.getFloat(6));
        return b;
    }
}
