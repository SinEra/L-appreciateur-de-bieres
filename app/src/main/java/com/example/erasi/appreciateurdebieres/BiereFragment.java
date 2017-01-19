package com.example.erasi.appreciateurdebieres;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by erasi on 2016-10-29.
 */
public class BiereFragment extends Fragment {

    Biere biere;
    Microbrasserie micro;
    EditText nomB;
    EditText typeB;
    Spinner niveau;
    EditText commentaire;
    RatingBar note;
    BiereDAO bdao;
    MicrobrasserieDAO mdao;
    Button boutonMicro;

    public static BiereFragment newInstance(Biere biere) {
        BiereFragment fragment = new BiereFragment();
        Bundle args = new Bundle();
        args.putInt(BiereDAO.KEY, biere.getId());
        args.putString(BiereDAO.NOM, biere.getNom());
        args.putInt(BiereDAO.MICRO, biere.getMicrobrasserie());
        args.putString(BiereDAO.TYPE, biere.getType());
        args.putFloat(BiereDAO.NIVEAU, biere.getNiveauAlcool());
        args.putString(BiereDAO.COMMENTAIRE, biere.getCommentaire());
        args.putFloat(BiereDAO.NOTE, biere.getNote());
        fragment.setArguments(args);
        return fragment;
    }

    public BiereFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // crée le fragment, équivalent à onCreate pour les Activités
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_ajoutbiere, container, false);

        mdao = new MicrobrasserieDAO(this.getContext());
        bdao = new BiereDAO(this.getContext());

        boutonMicro = (Button)rootView.findViewById(R.id.buttonMicro);
        Button boutonSauvegarder = (Button)rootView.findViewById(R.id.buttonSauvegarder);
        Button boutonQuitter = (Button)rootView.findViewById(R.id.buttonQuitter);

        nomB = (EditText)rootView.findViewById(R.id.editTextNomBiere);
        typeB = (EditText)rootView.findViewById(R.id.editTextTypeBiere);
        niveau = (Spinner)rootView.findViewById(R.id.spinnerTaux);
        commentaire = (EditText)rootView.findViewById(R.id.editTextCommentaire);
        note = (RatingBar)rootView.findViewById(R.id.ratingBarNote);


        // Initialise les champs avec les valeurs passé en parametres
        Bundle args = this.getArguments();
        biere = new Biere(args.getInt(BiereDAO.KEY), args.getString(BiereDAO.NOM),
                args.getInt(BiereDAO.MICRO), args.getString(BiereDAO.TYPE), args.getFloat(BiereDAO.NIVEAU),
                args.getString(BiereDAO.COMMENTAIRE), args.getFloat(BiereDAO.NOTE));
        ;
        micro = mdao.selectionner(biere.getMicrobrasserie());
        boutonMicro.setText(micro.getNom());
        nomB.setText(biere.getNom());
        typeB.setText(biere.getType());
        niveau.setSelection((int)(biere.getNiveauAlcool()/0.5 + 0.5));
        commentaire.setText(biere.getCommentaire());
        note.setRating(biere.getNote());

        Ecouteur ec = new Ecouteur();

        boutonSauvegarder.setOnClickListener(ec);
        boutonQuitter.setOnClickListener(ec);
        boutonMicro.setOnClickListener(ec);

        return rootView;

    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonSauvegarder:
                    biere.setNom(nomB.getText().toString());
                    biere.setMicrobrasserie(micro.getId());
                    biere.setType(typeB.getText().toString());
                    biere.setNiveauAlcool(Float.parseFloat((String)niveau.getSelectedItem()));
                    biere.setCommentaire(commentaire.getText().toString());
                    biere.setNote(note.getRating());
                    bdao.changer(biere);

                    CharSequence text = "Bière sauvegardée";

                    Toast toast = Toast.makeText(getContext(), text , Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.buttonQuitter:
                    getActivity().finish();
                    break;/*
                case R.id.buttonMicro:
                    Intent myIntent1 = new Intent (ListeBieresActivity.class, SelectionMicroActivity.class);
                    startActivityForResult(myIntent1, 1);
                    break;*/
            }
        }
    }
}
