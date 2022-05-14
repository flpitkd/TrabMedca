package com.example.medca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    private ListView lvConsulta;
    private ArrayAdapter adapter;
    private List<Consulta> listaDeConsultas;
    private Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvConsulta = findViewById(R.id.lvConsulta);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        lvConsulta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idConsulta = listaDeConsultas.get( position ).getId();
                Intent intent = new Intent(ConsultaActivity.this, formularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idConsulta" , idConsulta);
                startActivity(intent);
            }
        });

        lvConsulta.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                excluir(position);
                return true;
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaActivity.this, formularioActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

    }

    private void carregarConsultas() {
        listaDeConsultas = ConsultaDAO.getConsulta( this);

        if (listaDeConsultas.size() == 0) {
            Consulta fake = new Consulta("Lista Vazia...","","");
            listaDeConsultas.add(fake);
            lvConsulta.setEnabled(false);
        } else {
            lvConsulta.setEnabled(true);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDeConsultas);
        lvConsulta.setAdapter(adapter);
    }

    private void excluir(int posicao){
        Consulta prod = listaDeConsultas.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir...");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Confirma a exclusão do produto " + prod.getNome() +"?");
        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ConsultaDAO.excluir(ConsultaActivity.this, new formularioActivity());
                carregarConsultas();
            }
        });
        alerta.show();
    }
}