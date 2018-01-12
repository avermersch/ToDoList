package com.example.formation.todolist.controller;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.formation.todolist.R;
import com.example.formation.todolist.model.DatabaseHandler;


public class TacheActivity extends AppCompatActivity {

    private EditText tacheEditText;
    private String tacheId;
    private Button buttonValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache);

        ActionBar actionBar = getActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Récupération bouton
        buttonValider = (Button)findViewById(R.id.buttonValid);

        //Récupération des données
        Intent intention = getIntent();
        String tache = intention.getStringExtra("tache");
        String id = intention.getStringExtra("id");

        //Récupération de l'id dans une variable globale
        this.tacheId = id;

        //Référence aux editText
        this.tacheEditText = findViewById(R.id.editTextTache);

        //Affichage des données dans les champs de saisie
        this.tacheEditText.setText(tache);
    }

    public void onValid(View v){
        Button clickedButton = (Button) v;

        //Récupération de la saisie de l'utilisateur
        String editTextTache = ((EditText) findViewById(R.id.editTextTache)).getText().toString();

        //Instanciation de la base de données
        DatabaseHandler db = new DatabaseHandler(this);

        //Définition des données à insérer
        ContentValues insertValues = new ContentValues();
        insertValues.put("tache", editTextTache);

        //Insertion des données
        try {
            //Insertion d'une nouvelle tache
            db.getWritableDatabase().insert("tache", null, insertValues);
            Toast.makeText(this, "Insertion OK", Toast.LENGTH_SHORT).show();

            ((EditText)findViewById(R.id.editTextTache)).setText("");

        }catch (SQLiteException ex){
            Log.e("SQL EXCEPTION", ex.getMessage());
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
