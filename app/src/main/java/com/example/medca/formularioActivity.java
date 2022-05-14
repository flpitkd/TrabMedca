package com.example.medca;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class formularioActivity extends AppCompatActivity {


    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    private EditText etNome;
    private EditText etData;
    private Button btnSalvar;
    private Spinner spEspecializacao;
    private String acao;
    private Object Consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        radioButton = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);
        etNome = findViewById(R.id.etNome);
        etData = findViewById(R.id.etData);
        btnSalvar = findViewById(R.id.btnSalvar);
        spEspecializacao = findViewById(R.id.spEspecializacao);
        carregarEspecializacao();

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")) {
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }

        });

    }

    private void carregarEspecializacao() {
        Especializacao fake = new Especializacao(0, "Selecione a Especialização...");
        List<Especializacao> lista = EspecializacaoDAO.getEspecializacao(this);
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        spEspecializacao.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Cadastrar Especialização...");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.toString().equals("Cadastrar Especialização...")) {
            cadastrarEspecializacao();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregarFormulario() {

        // Recuperando o ID do produto selecionado (provavelmente de alguma lista que vc tem)
        int id = getIntent().getIntExtra("idProduto", 0);

        // Recuperando o objeto "Produto" a partir do ID.
        Consulta = ConsultaDAO.getConsulta(this);

        // Com o objeto recuperado é hora de alimentar os componentes na tela.


    }

    private void salvar() {

        String nome = etNome.getText().toString();

        if (nome.isEmpty() || spEspecializacao.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        } else {
            if (acao.equals("inserir")) {
                Consulta = new Consulta("Lista Vazia...", "", "");
            }


            String data = etData.getText().toString();

            String especializacao = spEspecializacao.getSelectedItem().toString();


            if (acao.equals("inserir")) {
                // Insetindo os dados do produto no banco de dados.
                ConsultaDAO.inserir(this, (com.example.medca.Consulta) Consulta);

                // Limpando os componentes da tela
                etNome.setText("");
                etData.setText("");
                spEspecializacao.setSelection(0, true);
            } else {
                ConsultaDAO.editar(this, (com.example.medca.Consulta) Consulta);
                finish();
            }
        }
    }
    private void cadastrarEspecializacao() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Cadastrar Especializacao");
        alerta.setIcon(android.R.drawable.ic_input_add);

        EditText etNomeEspecializacao = new EditText(this);
        etNomeEspecializacao.setHint("Digite aqui a Especialização...");
        alerta.setView(etNomeEspecializacao);

        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nome = etNomeEspecializacao.getText().toString();
                if (!nome.isEmpty()) {
                    EspecializacaoDAO.inserir(formularioActivity.this, nome);
                    carregarEspecializacao();
                }
            }
        });
        alerta.show();

    }

    public void checkButton(View view) {
    }


}