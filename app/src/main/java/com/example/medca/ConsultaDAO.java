package com.example.medca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public static void inserir(Context context, Consulta consulta) {
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", consulta.getNome());
        valores.put("data", consulta.getData());
        valores.put("codesp", consulta.getEspecializacao().getId());

        db.insert("consulta", null, valores);

        db.close();
    }


    public static void editar(Context context, Consulta consulta) {
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", consulta.getNome());
        valores.put("data", consulta.getData());
        valores.put("codesp", consulta.getEspecializacao().getId());

        db.update("consulta", valores, " id = " + consulta.getId(), null);

        db.close();
    }


    public static void excluir(Context context, formularioActivity idConsulta) {
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("consulta", " id = " + idConsulta, null);

        db.close();
    }


    public static List<Consulta> getConsulta(Context context) {
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                " SELECT c.id, c.nome, c.data, e.id, e.nome " +
                        " FROM consulta c " +
                        " INNER JOIN especializacao e ON e.id = c.codesp " +
                        " ORDER BY c.nome ",
                null);

        List<Consulta> lista = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Especializacao e = new Especializacao();
                e.setId(cursor.getInt(3));
                e.setNome(cursor.getString(4));

                Consulta c = new Consulta("Lista Vazia...", "", "");
                c.setId(cursor.getInt(0));
                c.setNome(cursor.getString(1));
                c.setData(cursor.getString(2));
                c.setEspecializacao(e);

                lista.add(c);
            } while (cursor.moveToNext());
        }
        return lista;
    }

}
