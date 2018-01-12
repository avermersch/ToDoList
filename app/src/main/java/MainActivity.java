import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.formation.todolist.R;

import java.util.List;

import database.DatabaseHandler;
import database.TacheDAO;
import model.Tache;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView tacheListView;
    private List<Tache> tacheList;
    private Integer selectedIndex;
    private Tache selectedTache;
    private TacheArrayAdapter tacheAdapter;

    private DatabaseHandler db;
    private TacheDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////Instanciation de la connexion de base de données
        this.db = new DatabaseHandler(this);
        //Instantciation du DAO pour les taches
        this.dao = new TacheDAO(this.db);

        //Référence au widget  ListView sur le layout
        tacheListView = findViewById(R.id.tacheListView);
        tacheListInit();

        //Récupération des données persistées dans le Bundle
        if(savedInstanceState != null){
            //Récupération de l'index de sélection de sauvegarde
            this.selectedIndex = savedInstanceState.getInt("selectedIndex");
            if(this.selectedIndex != null){
                this.selectedTache = this.tacheList.get(this.selectedIndex);
                tacheAdapter.setSelection(this.selectedIndex);
            }
        }

    }

    private void tacheListInit() {
        //Récupération de la liste des taches
        tacheList = this.dao.findAll();

        //Création d'un tacheArrayAdapter
        tacheAdapter = new TacheArrayAdapter(this, tacheList);

        //Définition de l'adapter de notre listView
        tacheListView.setAdapter(tacheAdapter);

        //Définition d'un écouteur d'évènement pour OnItemClickListener
        tacheListView.setOnItemClickListener(this);
    }


    //Lancement de l'activité tache au clic sur un bouton
    public void onAddTache(View view){
        Intent TacheIntent = new Intent(this.getBaseContext(), TacheActivity.class);
        startActivity(TacheIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
        this.selectedIndex = position;
        this.selectedTache = tacheList.get(position);
        Toast.makeText(this, "Tache" + position + "selectionnée", Toast.LENGTH_SHORT).show();
    }


}
