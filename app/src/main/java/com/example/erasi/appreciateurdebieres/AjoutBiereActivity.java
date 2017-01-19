package com.example.erasi.appreciateurdebieres;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by erasi on 2016-10-15.
 */
public class AjoutBiereActivity extends Activity{

    Microbrasserie micro;
    EditText nomB;
    EditText typeB;
    Spinner niveau;
    EditText commentaire;
    RatingBar note;
    BiereDAO bdao;
    MicrobrasserieDAO mdao;
    Button boutonMicro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ajoutbiere);

        mdao = new MicrobrasserieDAO(this);
        bdao = new BiereDAO(this);

        boutonMicro = (Button)findViewById(R.id.buttonMicro);
        Button boutonSauvegarder = (Button)findViewById(R.id.buttonSauvegarder);
        Button boutonQuitter = (Button)findViewById(R.id.buttonQuitter);

        nomB = (EditText)findViewById(R.id.editTextNomBiere);
        typeB = (EditText)findViewById(R.id.editTextTypeBiere);
        niveau = (Spinner)findViewById(R.id.spinnerTaux);
        commentaire = (EditText)findViewById(R.id.editTextCommentaire);
        note = (RatingBar)findViewById(R.id.ratingBarNote);

        //S'il y a un paramètre micro, on va chercher le id sinon on laisse le bouton comme il est
        if(getIntent().hasExtra("micro")){
            int microId = getIntent().getIntExtra("micro", 0);
            micro = mdao.selectionner(microId);
            boutonMicro.setText(micro.getNom());
        };

        Ecouteur ec = new Ecouteur();

        boutonSauvegarder.setOnClickListener(ec);
        boutonQuitter.setOnClickListener(ec);
        boutonMicro.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonSauvegarder:
                    Biere b = new Biere(0, nomB.getText().toString(), micro.getId(), typeB.getText().toString(),
                            (Float.parseFloat((String)niveau.getSelectedItem())), commentaire.getText().toString(),
                            note.getRating());
                    bdao.ajouter(b);

                    CharSequence text = "Bière sauvegardée";

                    Toast toast = Toast.makeText(getApplicationContext(), text , Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.buttonQuitter:
                    finish();
                    break;
                case R.id.buttonMicro:
                    Intent myIntent1 = new Intent(AjoutBiereActivity.this, SelectionMicroActivity.class);
                    startActivityForResult(myIntent1, 1);
                    break;
            }
        }
    }
    @Override
    protected void onActivityResult(int codeRequete, int codeResultat, Intent data){
        if(codeRequete == 1){
            int microid = data.getIntExtra("id", 0);
            micro = mdao.selectionner(microid);
            boutonMicro.setText(micro.getNom());
        }
    }
}
