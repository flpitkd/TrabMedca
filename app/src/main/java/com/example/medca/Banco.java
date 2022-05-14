package com.example.medca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "medica";

    public Banco(Context context){
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS especializacao ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " nome TEXT NOT NULL ); "
        );

        db.execSQL( "CREATE TABLE IF NOT EXISTS consulta ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " nome TEXT NOT NULL," +
                " data TEXT ," +
                " codesp INT ); "
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
