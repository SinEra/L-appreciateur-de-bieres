package com.example.erasi.appreciateurdebieres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("L'appréciateur de bières");

        Button buttonListeMicro = (Button)findViewById(R.id.buttonListeMicro);
        Button buttonListeBieres = (Button)findViewById(R.id.buttonListeBieres);
        Button buttonAjoutBiere = (Button)findViewById(R.id.buttonAjoutBiere);

        Ecouteur ec = new Ecouteur();

        buttonListeMicro.setOnClickListener(ec);
        buttonListeBieres.setOnClickListener(ec);
        buttonAjoutBiere.setOnClickListener(ec);

        MicrobrasserieDAO mbDao = new MicrobrasserieDAO(this);

        //Pour créer les micros initiales
        //Si lorsqu'on sélectionne tout, et que c'est déjà à la fin c'est qu'elle est vide
        if(mbDao.selectionnerTout().isAfterLast()){
            mbDao.ajouterMb();
        }
    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.buttonListeMicro:
                    Intent myIntent1 = new Intent(MainActivity.this, ListeMicroActivity.class);
                    startActivity(myIntent1);
                    break;
                case R.id.buttonListeBieres:
                    Intent myIntent2 = new Intent(MainActivity.this, ListeBieresActivity.class);
                    startActivity(myIntent2);
                    break;
                case R.id.buttonAjoutBiere:
                    Intent myIntent3 = new Intent(MainActivity.this, AjoutBiereActivity.class);
                    startActivity(myIntent3);
                    break;
            }
        }
    }
}
