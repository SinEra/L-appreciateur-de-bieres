package com.example.erasi.appreciateurdebieres;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erasi on 2016-10-16.
 */
public class MicrobrasserieDAO extends DAOBase{

    public static final String TABLE_NAME = "Microbrasserie";
    public static final String KEY = "_id";
    public static final String NOM = "nom";
    public static final String REGION = "region";
    public static final String LOGO = "logo";
    public static final String ADRESSE = "adresse";
    public static final String SITEWEB = "siteweb";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " " +
            "(" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOM + " TEXT, " +
            REGION + " TEXT," +
            LOGO + " INTEGER," +
            ADRESSE + " TEXT," +
            SITEWEB + " TEXT" + ");";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public MicrobrasserieDAO(Context context) {
        super(context);
    }

    public Cursor selectionnerTout(){

        SQLiteDatabase db = open();

        Cursor curseur = db.rawQuery("SELECT " + KEY + ", " + NOM + ", " + REGION + ", " + LOGO
                + ", " + ADRESSE + ", " + SITEWEB + " FROM " + TABLE_NAME, null);

        return curseur;
    };

    public Microbrasserie selectionner(int id) {

        SQLiteDatabase db = open();

        Cursor curseur = db.rawQuery("SELECT " + KEY + ", " + NOM + ", " + REGION + ", " + LOGO
                + ", " + ADRESSE + ", " + SITEWEB + " FROM " + TABLE_NAME + " WHERE _id = ?", new String[]{Integer.toString(id)});

        if(curseur.moveToNext()) {
            String nom = curseur.getString(1);
            String region = curseur.getString(2);
            int logo = curseur.getInt(3);
            String adresse = curseur.getString(4);
            String siteWeb = curseur.getString(5);

            return new Microbrasserie(id, nom, logo, region, adresse, siteWeb);
        }

        return null;
    }

    public void ajouter(Microbrasserie mb){
        ContentValues value = new ContentValues();
        value.put(NOM, mb.getNom());
        value.put(LOGO, mb.getLogo());
        value.put(REGION, mb.getRegion());
        value.put(ADRESSE, mb.getAdresse());
        value.put(SITEWEB, mb.getSiteWeb());
        open();
        db.insert(TABLE_NAME, null, value);;
    };

    public void ajouterMb(){
        ajouter(new Microbrasserie(1, "Birra", R.drawable.birra, "Montréal",
                "7129 Boul St-Laurent, Montréal, QC H2S", "https://birra.ca/"));
        ajouter(new Microbrasserie(2, "La Succursale", R.drawable.succursale, "Montréal",
                "3188 Rue Masson, Montréal, QC H1Y 1Y1", "http://lasuccursale.com/"));
        ajouter(new Microbrasserie(3, "Boswell", R.drawable.boswell, "Montréal",
                "2407 Avenue du Mont-Royal E, Montréal, QC H2H 1L2", "http://brasserieboswell.com/"));
        ajouter(new Microbrasserie(4, "Le Trou du diable", R.drawable.troududiable, "Shawinigan",
                "412 Avenue Willow, Shawinigan, QC G9N 1X2", "http://troududiable.com/"));
        ajouter(new Microbrasserie(5, "Pit Caribou", R.drawable.pit_caribou, "Gaspésie",
                "182, QC-132, Percé, QC G0C 2L0", "http://www.pitcaribou.com/"));
        ajouter(new Microbrasserie(6, "Benelux", R.drawable.benelux, "Montréal",
                "245 Rue Sherbrooke Ouest, Montréal, QC H2X 1X7", "http://brasseriebenelux.com/"));
        ajouter(new Microbrasserie(7, "Tête d'Allumette", R.drawable.tete_allumette, "Bas St-Laurent",
                "265 route 132 ouest, Saint-André, QC G0L 2H0", "http://tetedallumette.com/"));
    }
}
