package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.marketapp.model.CEP;
import com.example.marketapp.services.CEPServiceImplementation;

public class MainActivity extends AppCompatActivity {

    private Button bt_pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_pg = findViewById(R.id.bt_pagamento);

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
}