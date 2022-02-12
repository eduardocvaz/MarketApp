package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marketapp.dao.ProdutoDAO;
import com.example.marketapp.model.Produto;

public class CadastrarProdutoActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etURL;
    private EditText etValor;
    private EditText etDescricao;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        etNome = findViewById(R.id.etNome);
        etURL = findViewById(R.id.etURL);
        etValor = findViewById(R.id.etValor);
        etDescricao = findViewById(R.id.etDescricao);

        btCadastrar = findViewById(R.id.btCadastrar);

        AlertDialog.Builder dialog = new AlertDialog.Builder(CadastrarProdutoActivity.this);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String url = etURL.getText().toString();
                Double valor = null;
                if(!etValor.getText().toString().isEmpty()) {
                    valor = Double.valueOf(etValor.getText().toString());
                }
                String descricao = etDescricao.getText().toString();


                if(nome.isEmpty() || url.isEmpty() || valor.isNaN() || descricao.isEmpty() ){

                    dialog.setTitle("Aviso");
                    dialog.setMessage("Preencha os campos corretamente");
                    dialog.setNeutralButton("Ok",null);

                    dialog.create();
                    dialog.show();
                } else {
                    Produto produto = new Produto();

                    produto.setFotoURL(url);
                    produto.setNome(nome);
                    produto.setValor(valor);
                    produto.setDescricao(descricao);

                    if(!new ProdutoDAO(getApplicationContext()).salvar(produto)){
                        dialog.setTitle("Aviso");
                        dialog.setMessage("Erro ao cadastrar no banco de dados");
                        dialog.setNeutralButton("Ok",null);

                        dialog.create();
                        dialog.show();
                    }
                    dialog.setTitle("Aviso");
                    dialog.setMessage("Produto cadastrado com sucesso!");
                    dialog.setNeutralButton("Ok",null);

                    dialog.create();
                    dialog.show();

                    etNome.setText(null);
                    etURL.setText(null);
                    etValor.setText(null);
                    etDescricao.setText(null);

                }

            }
        });

    }
}