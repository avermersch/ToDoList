package database;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;


import java.util.ArrayList;
import java.util.List;

import model.Tache;


public class TacheDAO implements DAOInterface<Tache>{

    private DatabaseHandler db;

    public TacheDAO(DatabaseHandler db){this.db = db;}

    public Tache findOneById(int id) throws SQLiteException{
        //Exécution de la requête
        String[] params = {String.valueOf(id)};
        String sql = "SELECT id, tache FROM tache WHERE id=?";
        Cursor cursor = this.db.getReadableDatabase().rawQuery(sql, params);
        //Instanciation de la tache
        Tache tache = new Tache();

        //Hydratation de la tache
        if (cursor.moveToNext()){
            tache = hydrateTache(cursor);
        }

        //Fermeture du curseur
        cursor.close();

        return tache;
    }

    private Tache hydrateTache(Cursor cursor) {
        Tache tache = new Tache();

        tache.setId(cursor.getLong(0));
        tache.setTache(cursor.getString(1));

        return tache;
        }

        //@return List<Tache une liste de tache
    @Override
    public List<Tache> findAll() throws SQLiteException {
        //Instanciation de la liste de tache
        List<Tache> tacheList = new ArrayList<>();

        //Exécution de la requête sql
        String sql = "SELECT id, tache FROM tache";
        Cursor cursor = this.db.getReadableDatabase().rawQuery(sql, null);
        //Boucle sur le curseur
        while(cursor.moveToNext()){
            tacheList.add(this.hydrateTache(cursor));
        }

        //Fermeture du curseur
        cursor.close();

        return tacheList;
    }

}
