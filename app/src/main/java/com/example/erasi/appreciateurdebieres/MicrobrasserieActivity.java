package com.example.erasi.appreciateurdebieres;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by erasi on 2016-10-28.
 */
public class MicrobrasserieActivity extends Activity {

    Microbrasserie micro;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_micro);

        //avoir les paramètres de l'activité (Id de la micro à afficher)
        int microId = getIntent().getIntExtra("id", 0);

        MicrobrasserieDAO mdao = new MicrobrasserieDAO(this);

        //aller chercher la micro dans la table
        micro = mdao.selectionner(microId);

        TextView nom = (TextView)findViewById(R.id.textViewNom);
        TextView adresse = (TextView)findViewById(R.id.textViewAdresse);
        TextView siteWeb = (TextView)findViewById(R.id.textViewSite);
        ImageView logo = (ImageView)findViewById(R.id.imageViewLogo);
        Button boutonCarte = (Button)findViewById(R.id.buttonCarte);
        Button boutonListe = (Button)findViewById(R.id.buttonBieresEvaluees);
        Button boutonAjout = (Button)findViewById(R.id.buttonAjoutBieres);
        Button boutonQuitter = (Button)findViewById(R.id.buttonQuitter);

        nom.setText(micro.getNom());
        adresse.setText(micro.getAdresse());
        siteWeb.setText(micro.getSiteWeb());
        logo.setImageResource(micro.getLogo());

        //#1
        Ecouteur ec = new Ecouteur();

        //#2
        boutonCarte.setOnClickListener(ec);
        boutonAjout.setOnClickListener(ec);
        boutonListe.setOnClickListener(ec);
        boutonQuitter.setOnClickListener(ec);
    }

    //#3
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonCarte:
                    String uri = "geo:0,0?q=" + Uri.encode(micro.getNom() + " Qc");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                    break;
                case R.id.buttonAjoutBieres:
                    Intent intent2 = new Intent(MicrobrasserieActivity.this, AjoutBiereActivity.class);
                    //micro.getId() pour assigner une micro a la bière
                    intent2.putExtra("micro",micro.getId());
                    startActivity(intent2);
                    break;
                case R.id.buttonBieresEvaluees:
                    Intent intent3 = new Intent(MicrobrasserieActivity.this, ListeBieresActivity.class);
                    //micro.getId() pour assigner une micro a la bière
                    intent3.putExtra("micro",micro.getId());
                    startActivity(intent3);
                    break;
                case R.id.buttonQuitter:
                    finish();
            }
        }
    }
}
