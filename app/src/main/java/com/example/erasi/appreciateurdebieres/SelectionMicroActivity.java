package com.example.erasi.appreciateurdebieres;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by erasi on 2016-10-28.
 */
public class SelectionMicroActivity extends Activity{

        public ListView listeMB;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_listemicro);
            setTitle("Liste des Microbrasserie du Québec");

            listeMB = (ListView)findViewById(R.id.listViewMicro);

            MicrobrasserieDAO mbDAO = new MicrobrasserieDAO(this);

            SimpleCursorAdapter adapter = new SimpleCursorAdapter
                    (this, R.layout.item_micro, mbDAO.selectionnerTout(), new String[]{mbDAO.NOM, mbDAO.REGION, mbDAO.LOGO},
                            new int[]{R.id.textViewNom, R.id.textViewRegion, R.id.imageViewMb});

            listeMB.setAdapter(adapter);

            Ecouteur ec = new Ecouteur();

            listeMB.setOnItemClickListener(ec);
        }

        private class Ecouteur implements AdapterView.OnItemClickListener {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor micro = (Cursor) listeMB.getItemAtPosition(position);
                int microId = micro.getInt(micro.getColumnIndex(MicrobrasserieDAO.KEY));

                Intent myIntent1 = new Intent();
                //pour donner le id de la micro à l'activity micro
                myIntent1.putExtra("id", microId);
                setResult(RESULT_OK, myIntent1);
                finish();
            }
        }
    }
