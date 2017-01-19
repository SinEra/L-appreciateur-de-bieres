package com.example.erasi.appreciateurdebieres;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by erasi on 2016-10-16.
 */
public class DAOBase {
    protected SQLiteDatabase db = null;
    protected DatabaseHandler handler = null;

    public DAOBase(Context context) {
        this.handler = new DatabaseHandler(context);
    }
    //pour ouvrir connection
    public SQLiteDatabase open() {
        db = handler.getWritableDatabase();
        return db;
    }
}
