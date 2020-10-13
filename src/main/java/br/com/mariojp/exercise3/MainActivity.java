package br.com.mariojp.exercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnRemovePrimeiro;
    private EditText tarefaText;
    private EditText prioridadeText;
    private ListView listaTarefas;
    private List<Tarefa> tarefas = new ArrayList<>();
    private Tarefa tarefa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // tarefas.add(new Tarefa("Atividade", 1));
        //tarefas.add(new Tarefa("Atividade 2", 3));

        btnAdd = findViewById(R.id.buttonAdicionar) ;
        btnRemovePrimeiro = findViewById(R.id.buttonRemover);
        tarefaText = findViewById(R.id.editDescricao);
        prioridadeText = findViewById(R.id.editPrioridade);
        listaTarefas = findViewById(R.id.listView);
       // btnRemovePrimeiro.setEnabled(false);
        listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                removerDaLista(tarefa);
                Log.i("TAREFA", "POSIÇÃO "+position);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(tarefas.size()>=1) {
            btnRemovePrimeiro.setEnabled(true);
        }ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        listaTarefas.setAdapter(adapter);

    }

    public void adicionar(View view){
        String nome = tarefaText.getText().toString();
        int prioridade = Integer.parseInt(prioridadeText.getText().toString());
        if(validarTarefa(nome)) {
            if(validarPrioridade(prioridade)){
                tarefa = new Tarefa(nome, prioridade);
                tarefas.add(tarefa);
                Collections.sort(tarefas);
                Log.i("TAREFA", "TAREFA INSERIDA:" + nome+" "+prioridade);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "A prioridade deve estar entre 1 e 10.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Tarefa já cadastrada.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void remover(View view){
        tarefas.remove(0);
    }

    private void removerDaLista(Tarefa t){
        tarefas.remove(t);
    }

    private boolean validarTarefa(String descricao){
        for (Tarefa t: tarefas) {
            if(t.getDescricao().equalsIgnoreCase(descricao)){
                return false;
            }
        }
        return true;
    }

    private boolean validarPrioridade(int p){
        if(p < 1 || p > 10){
            return false;
        }
        return true;
    }
}
