package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.marketapp.model.CEP;
import com.example.marketapp.services.CEPServiceImplementation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        CEPServiceImplementation cepServiceImplementation = new CEPServiceImplementation();
        cepServiceImplementation.httpRequestCall("01001000");



    }
}