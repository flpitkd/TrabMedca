package com.example.medca;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EspecializacaoDAO {

    public static void inserir(Context context, String nome){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.execSQL("INSERT INTO especializacao (nome) VALUES (  '" + nome + "'  ) " ) ;
        db.close();
    }


    public static List<Especializacao> getEspecializacao(Context context){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery( "SELECT id, nome FROM especializacao ORDER BY nome", null );

        List<Especializacao> lista = new ArrayList<>();

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            do{
                Especializacao g = new Especializacao();
                g.setId(  cursor.getInt( 0 ) );
                g.setNome(  cursor.getString( 1 ) );
                lista.add( g );
            }while (cursor.moveToNext());
        }
        return lista;
    }

}
