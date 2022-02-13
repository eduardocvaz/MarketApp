package com.example.marketapp.services;

import com.example.marketapp.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("/ws/{codigo}/json")
    public Call<CEP> retornarInfo(@Path("codigo") String codigoCEP);

}
