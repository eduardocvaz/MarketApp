package com.example.marketapp.services;

import com.example.marketapp.model.CEP;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.util.Log;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CEPServiceImplementation {


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
                    Log.i("TAG", "Erro: " + response.code());
                }
                else {     // response retornou com sucesso
                    CEP cep = response.body();
                    Log.i("TAG", String.valueOf(cep));
                }

            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                Log.e("onFailure resultado", "Erro: " + t.getMessage());
            }
        });
    }


}