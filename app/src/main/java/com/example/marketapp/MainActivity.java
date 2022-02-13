package com.example.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.marketapp.fragments.ProductsListFragment;
import com.example.marketapp.model.Produto;
import com.example.marketapp.services.CEPServiceImplementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductsListFragment.AoClicarNoProduto{

    List<Produto> produtosSelecionados;
    private ProductsListFragment productsListFragment;
    private FragmentManager myFragmentManager;


    private Button bt_pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produtosSelecionados = new ArrayList<Produto>();
        myFragmentManager =getSupportFragmentManager();
        productsListFragment = (ProductsListFragment) myFragmentManager.findFragmentById(R.id.fragmentLista);

        bt_pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), PagamentoActivity.class);
                startActivity(it);
            }
        });

        CEPServiceImplementation cepServiceImplementation = new CEPServiceImplementation();
        cepServiceImplementation.httpRequestCall("01001000");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
     getMenuInflater().inflate(R.menu.menu,menu);

     return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent it;
        switch(item.getItemId()){
            case R.id.acao_adicionar:
                 it = new Intent(this,CadastrarProdutoActivity.class);
                startActivityForResult(it,0);
//                ProdutoDialogFragment produtoDialogFragment =
//                        ProdutoDialog.newInstance();
//                produtoDialogFragment.show(myFragmentManager,ProdutoDialogFragment.Dialog_TAG);
                break;

            case R.id.acao_login:
//                LoginDialogFragment loginDialogFragment =
//                        LoginDialog.newInstance();
//                loginDialogFragment.show(myFragmentManager,LoginDialogFragment.Dialog_TAG);
                break;

            case R.id.carrinho:
                 it = new Intent(this,CarrinhoActivity.class);
                it.putExtra("SizeLista",produtosSelecionados.size());
                for(int i=0; i< produtosSelecionados.size(); i++){
                    it.putExtra(String.valueOf(i),produtosSelecionados.get(i));
                }

                startActivityForResult(it,0);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clicouNoProduto(Produto produto) {
        produtosSelecionados.add(produto);
    }
}