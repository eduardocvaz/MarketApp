package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
                                int duracao = Toast.LENGTH_SHORT;

                                Toast confP = Toast.makeText(ctx, txt, duracao);
                                Toast EfetP = Toast.makeText(ctx, txt_, duracao);

                                confP.show();
                                EfetP.show();
                                AlertDialog.Builder dialog = new AlertDialog.Builder(PagamentoActivity.this);
                                dialog.setTitle("Aviso");
                                dialog.setMessage("Compra efetuada com sucesso!");
                                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        backToMain();
                                    }
                                });
                                dialog.create();
                                dialog.show();


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
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void backToMain() {
        //Monta a intent para abrir a aplicação.
        Intent mStartActivity = new Intent(this, MainActivity.class);


        //Realiza o agendamento da intent de abrir o aplicativo:
        //No caso abaixo o aplicativo vai ser reaberto daqui 500ms (System.currentTimeMillis() + 500);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 123456, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis(), mPendingIntent);

        //Mata todos processos associados a este aplicativo.
        android.os.Process.killProcess(android.os.Process.myPid());
        //Fecha o aplicativo.
        System.exit(1);
    }
}