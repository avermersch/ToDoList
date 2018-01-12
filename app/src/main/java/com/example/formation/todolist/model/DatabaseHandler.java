package com.example.formation.todolist.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Formation on 11/01/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_TACHE = "tache_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TACHE_TABLE_SQL = "CREATE TABLE tache("+
                                                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                        "tache TEXT NOT NULL)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_TACHE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TACHE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //supprime la table si existe
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tache");
        //table se créer
        this.onCreate(sqLiteDatabase);
    }
}
