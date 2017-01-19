package com.example.erasi.appreciateurdebieres;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by erasi on 2016-10-14.
 */
public class ListeBieresActivity extends FragmentActivity {

    private ViewPager viewpager;
    private SwipeAdapter adaptateur;
    BiereDAO bdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listebieres);
        setTitle("Liste des bières de microbrasserie");

        bdao = new BiereDAO(this);
        Cursor curseur;
        if (getIntent().hasExtra("micro")) {
            curseur = bdao.selectionnerParMicro(getIntent().getIntExtra("micro",0));
        } else {
            curseur = bdao.selectionnerTout();
        }
        //récupérer le ViewPager
        viewpager = ( ViewPager) findViewById(R.id.pager);
        //adaptateur spécial pour le remplir, doit être sous-classe de FragmentStatePagerAdapter
        adaptateur = new SwipeAdapter(getSupportFragmentManager(), curseur);
        viewpager.setAdapter(adaptateur);
    }

    public class SwipeAdapter extends FragmentStatePagerAdapter {
        Cursor curseur;
        public SwipeAdapter(FragmentManager fm, Cursor c) {
            super(fm);
            this.curseur = c;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int id) {
            //changements d'un fragment à l'autre, j'utilise la méthode générée par CouleurFragment
            curseur.moveToPosition(id);
            Biere b = bdao.fromCurseur(curseur);
            return  BiereFragment.newInstance(b); //méthode générée quand je crée un fichier Fragment ( CouleurFragment )
        }

        @Override
        public int getCount() {
            return curseur.getCount(); //SUPER IMPORTANT , NE LAISSER PAS 0 MAIS LE NOMBRE DE FRAGMENTS QUE VOUS AUREZ
        }
    }
}
