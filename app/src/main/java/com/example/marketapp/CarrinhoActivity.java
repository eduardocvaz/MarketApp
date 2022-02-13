package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.example.marketapp.fragments.ProductsListFragment;
import com.example.marketapp.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {

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
}