package com.example.medca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    private ListView lvConsulta;
    private Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        lvConsulta = findViewById(R.id.lvConsulta);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaActivity.this, MainActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarConsultas();
    }

    private void carregarConsultas() {
        List<Consulta> lista = ConsultaDAO.getConsulta(this);

        if (lista.size() == 0) {
            Consulta fake = new Consulta("Nenhuma consulta cadastrada", "...", null);
            lista.add(fake);
            lvConsulta.setEnabled(false);
        } else {
            lvConsulta.setEnabled(true);
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lista);
        lvConsulta.setAdapter(adapter);
    }
}