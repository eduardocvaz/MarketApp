package com.example.marketapp.model;

public class Produto {

    /* carregar um ImageView por URL
    https://qastack.com.br/programming/2471935/how-to-load-an-imageview-by-url-in-android*/

    private Long id;
    private String fotoURL;
    private String nome;
    private String descricao;
    private Double valor;

    public Produto() {

    }

    public Produto(Long id, String fotoURL, String nome, String descricao, Double valor) {
        this.id = id;
        this.fotoURL = fotoURL;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
