package com.example.marketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.marketapp.fragments.ProductsListFragment;
import com.example.marketapp.services.CEPServiceImplementation;

public class MainActivity extends AppCompatActivity {

    private ProductsListFragment productsListFragment;
    private FragmentManager myFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFragmentManager =getSupportFragmentManager();
        productsListFragment = (ProductsListFragment) myFragmentManager.findFragmentById(R.id.fragmentLista);


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
        return super.onOptionsItemSelected(item);
    }

}