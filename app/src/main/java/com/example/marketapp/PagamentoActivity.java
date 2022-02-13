package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

//Parte da montagem do SnackBar foi baseado no site:https://www.geeksforgeeks.org/how-to-add-a-snackbar-in-android/
import com.google.android.material.snackbar.Snackbar;

public class PagamentoActivity extends AppCompatActivity {

    // EditText edtDataVenc;
    //private Button btFin_Pag;
    private ImageButton ibBack;

    private CoordinatorLayout coordinatorLayout;
    private Button btCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        //btFin_Pag = findViewById(R.id.bt_ConfPag);
        ibBack = findViewById(R.id.ibt_Voltar);

        coordinatorLayout = findViewById(R.id.layout);
        btCompra = findViewById(R.id.btPagar);

        btCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence pergunta = "Confirma a Compra?";
                int sbar_duracao = Snackbar.LENGTH_LONG;

                Snackbar sbar = Snackbar.make(coordinatorLayout, pergunta,
                        sbar_duracao).setAction("SIM", new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //Usando Toast
                                Context ctx = getApplicationContext();
                                CharSequence txt = "Confirmando a Compra...";
                                CharSequence txt_ = "Compra Confirmada!";
                                int duracao = Toast.LENGTH_LONG;

                                Toast confP = Toast.makeText(ctx, txt, duracao);
                                Toast EfetP = Toast.makeText(ctx, txt_, duracao);

                                confP.show();
                                EfetP.show();
                                finish();
                            }
                });
                sbar.show();
            }
        });

        /*btFin_Pag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Usando Toast
                Context ctx = getApplicationContext();
                CharSequence txt = "Confirmando a Compra...";
                CharSequence txt_ = "Compra Confirmada!";
                int duracao = Toast.LENGTH_LONG;

                Toast confP = Toast.makeText(ctx, txt, duracao);
                Toast EfetP = Toast.makeText(ctx, txt_, duracao);

                confP.show();
                EfetP.show();

                finish();
            }
        });*/

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }
}