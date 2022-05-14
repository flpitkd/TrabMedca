package com.example.medca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;


    private Spinner spEspecializacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButton= findViewById(R.id.radioGroup);
        textView= findViewById(R.id.text_view_selected);




        spEspecializacao = findViewById(R.id.spEspecializacao);

        carregarEspecializacao();

    }

    private void carregarEspecializacao(){
        Especializacao fake = new Especializacao(0, "Selecione a Especialização...");
        List<Especializacao> lista = EspecializacaoDAO.getEspecializacao(  this );
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista );
        spEspecializacao.setAdapter( adapter );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Cadastrar Especialização...");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.toString().equals("Cadastrar Especialização...")){
            cadastrarEspecializacao();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cadastrarEspecializacao(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Cadastrar Especializacao");
        alerta.setIcon(android.R.drawable.ic_input_add);

        EditText etNomeEspecializacao = new EditText(this);
        etNomeEspecializacao.setHint("Digite aqui a Especialização...");
        alerta.setView( etNomeEspecializacao );

        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nome = etNomeEspecializacao.getText().toString();
                if( !nome.isEmpty() ){
                    EspecializacaoDAO.inserir(MainActivity.this, nome);
                    carregarEspecializacao();
                }
            }
        });
        alerta.show();

    }

    public void checkButton(View view) {
    }
}