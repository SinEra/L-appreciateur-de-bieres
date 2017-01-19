package com.example.erasi.appreciateurdebieres;

/**
 * Created by erasi on 2016-10-16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context){
        super(context, "Appreciateurdebieres", null, 8);
    }
    //créer tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MicrobrasserieDAO.TABLE_CREATE);
        db.execSQL(BiereDAO.TABLE_CREATE);
    }
    //recréer tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MicrobrasserieDAO.TABLE_DROP);
        db.execSQL(BiereDAO.TABLE_DROP);
        onCreate(db);
    }
}