package com.example.marketapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.marketapp.dao.CEPDAO;
import com.example.marketapp.dao.ProdutoDAO;
import com.example.marketapp.dao.UsuarioDAO;
import com.example.marketapp.model.CEP;
import com.example.marketapp.model.Usuario;
import com.example.marketapp.services.CEPService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etCpf;
    private EditText etCep;
    private EditText etRua, etBairro, etNumero, etCidade, etEstado;

    private Button btCadastrar;

    private Usuario usuario = new Usuario();
    private CEP armazenarCepUsuario = new CEP();

    private boolean atualizar = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        etNome = findViewById(R.id.nomeUsuarioEt);
        etCpf = findViewById(R.id.cpfUsuarioEt);
        etCep = findViewById(R.id.cepUsuraioEt);

        etRua = findViewById(R.id.ruaUsuarioEt);
        etBairro = findViewById(R.id.bairroUsuarioEt);
        etNumero = findViewById(R.id.numeroUsuarioEt);
        etCidade = findViewById(R.id.cidadeUsuarioEt);
        etEstado = findViewById(R.id.estadoUsuarioEt);

        final String[] cepText = {etCep.getText().toString()};


        btCadastrar = findViewById(R.id.btCadastrarUsuario);

        AlertDialog.Builder dialog = new AlertDialog.Builder(CadastrarUsuarioActivity.this);



        etCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
                cepText[0] = etCep.getText().toString();
                httpRequestCall(cepText[0]);
            }
        });




        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = etNome.getText().toString();
                String cpf  = etCpf.getText().toString();
                String numero = etNumero.getText().toString();


                if(nome.isEmpty() || cpf.isEmpty() || cepText[0].isEmpty() || numero.isEmpty()){
                    dialog.setTitle("Aviso");
                    dialog.setMessage("Preencha os campos corretamente");
                    dialog.setNeutralButton("Ok",null);

                    dialog.create();
                    dialog.show();
                }
                else {


                    armazenarCepUsuario.setCep(etCep.getText().toString());
                    armazenarCepUsuario.setLogradouro(etRua.getText().toString());
                    armazenarCepUsuario.setLocalidade(etCidade.getText().toString());
                    armazenarCepUsuario.setBairro(etBairro.getText().toString());
                    armazenarCepUsuario.setUf(etEstado.getText().toString());


                    usuario.setNome(nome);
                    usuario.setCpf(cpf);



                    // salvar cep no banco de dados
                    if(atualizar) {

                        new CEPDAO(getApplicationContext()).atualizar(armazenarCepUsuario);
                    }
                    else if(!new CEPDAO(getApplicationContext()).salvar(armazenarCepUsuario)){
                        Log.i("TAG", "Erro ao cadastrar cep no banco de dados");
                    } else {
                        Log.i("TAG", "cep cadastrado com sucesso");
                    }



                    usuario.setCep(new CEPDAO(getApplicationContext()).buscarPorCep(armazenarCepUsuario.getCep()));

                    //salvar usuario no banco de dados
                    if(atualizar) {
                        new UsuarioDAO(getApplicationContext()).atualizar(usuario);
                    }
                    else if(!new UsuarioDAO(getApplicationContext()).salvar(usuario)){
                        dialog.setTitle("Aviso");
                        dialog.setMessage("Erro ao cadastrar no banco de dados");
                        dialog.setNeutralButton("Ok",null);

                        dialog.create();
                        dialog.show();
                    }
                    dialog.setTitle("Aviso");
                    dialog.setMessage("Usu??rio cadastrado com sucesso!");
                    dialog.setNeutralButton("Ok",null);

                    dialog.create();
                    dialog.show();



                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent it;
        switch(item.getItemId()){
            case R.id.acao_pagamento:
                it = new Intent(this,PagamentoActivity.class);
                startActivityForResult(it,0);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void httpRequestCall(String codigoCEP){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CEPService service = retrofit.create(CEPService.class);
        Call<CEP> requestInfoCep = service.retornarInfo(codigoCEP);


        requestInfoCep.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(!response.isSuccessful()){
                    Log.i("TAG", "Erro de response: " + codigoCEP + "  " + response.code());
                }
                else {     // response retornou com sucesso
                    CEP cep = response.body();
                    Log.i("TAG", "RESPONSE: " + String.valueOf(cep));

                    etRua.setText(cep.getLogradouro());
                    etBairro.setText(cep.getBairro());
                    etCidade.setText(cep.getLocalidade());
                    etEstado.setText(cep.getUf());


                    /* String cep;
                    String logradouro;
                    String complemento;
                    String bairro;
                    String localidade;
                    String uf;  */


                }

            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                Log.e("onFailure resultado", "Erro: " + t.getMessage());
            }
        });
    }



}
