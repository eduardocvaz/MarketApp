package com.example.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.marketapp.fragments.ProductsListFragment;
import com.example.marketapp.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoActivity extends AppCompatActivity implements ProductsListFragment.AoClicarNoProduto{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        Intent it = getIntent();
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        for(int i=0; i<it.getExtras().getInt("SizeLista");i++) {
            Produto produto = (Produto) it.getExtras().getSerializable(String.valueOf(i));
            produtos.add(produto);
        }
        ProductsListFragment productsListFragment = ProductsListFragment.newInstance(produtos);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentLista,productsListFragment,ProductsListFragment.PRODUTOS);
        ft.commit();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_carrinho, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent it;
        switch(item.getItemId()){
            case R.id.acao_cadastrar:
                it = new Intent(this,CadastrarUsuarioActivity.class);
                startActivityForResult(it,0);
                break;

            case R.id.acao_login:
                it = new Intent(this,CadastrarUsuarioActivity.class);
                startActivity(it);
//                LoginDialogFragment loginDialogFragment =
//                        LoginDialog.newInstance();
//                loginDialogFragment.show(myFragmentManager,LoginDialogFragment.Dialog_TAG);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clicouNoProduto(Produto produto) {

    }
}