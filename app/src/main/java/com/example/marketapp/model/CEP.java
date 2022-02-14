package com.example.marketapp.model;

import androidx.annotation.NonNull;

public class CEP {

    /*
      "cep": "01001-000",
      "logradouro": "Praça da Sé",
      "complemento": "lado ímpar",
      "bairro": "Sé",
      "localidade": "São Paulo",
      "uf": "SP",




      "ibge": "3550308",
      "gia": "1004",
      "ddd": "11",
      "siafi": "7107"

    */

    private Long id;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String Numero;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


    public String getIbge() { return ibge; }

    public void setIbge(String ibge) { this.ibge = ibge; }

    public String getGia() { return gia; }

    public void setGia(String gia) { this.gia = gia; }

    public String getDdd() { return ddd; }

    public void setDdd(String ddd) { this.ddd = ddd; }

    public String getSiafi() { return siafi; }

    public void setSiafi(String siafi) { this.siafi = siafi; }


    public boolean isEmpty(){

        if(cep.isEmpty() && logradouro.isEmpty() && bairro.isEmpty()
        && localidade.isEmpty() && uf.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }




    @Override
    public String toString() {
        return  " cep: " + cep + "\n" +
                "logradouro: " + logradouro + "\n" +
                "bairro: " + bairro + "\n" +
                "localidade: " + localidade + "\n" +
                "uf: " + uf;

    }
}
