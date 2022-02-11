package com.example.marketapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_BD = "bd_marketApp";
    public static String TABELA_USUARIOS = "usuarios";
    public static String TABELA_CEPS = "ceps";
    public static String TABELA_PRODUTOS = "produtos";


    public DBHelper(@Nullable Context context){
        super(context,NOME_BD,null,VERSION);
    }


    /*  private Long id;
    private String nome;
    private String cpf;
    private CEP cep;*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Long id;
          String nome;
          String cpf;
          CEP cep;*/
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIOS
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "nome VARCHAR(50) NOT NULL,"
                + "cpf VARCHAR(50) NOT NULL,"
                + "cepId INTEGER NOT NULL);";

        try{
            db.execSQL(sql);
            Log.i("INFO DB","Sucesso ao criar ao tabela usuarios!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela usuarios"+e.getMessage());
        }

    /*   Long id;
         String cep;
         String logradouro;
         String complemento;
         String bairro;
         String localidade;
         String uf;*/

        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABELA_CEPS
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "cep VARCHAR(50) NOT NULL,"
                + "logradouro VARCHAR(50) NOT NULL,"
                + "complemento VARCHAR(50),"
                + "bairro VARCHAR(50) NOT NULL,"
                + "localidade VARCHAR(50),"
                + "uf VARCHAR(50) NOT NULL);";

        try{
            db.execSQL(sql2);
            Log.i("INFO DB","Sucesso ao criar ao tabela ceps!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela ceps"+e.getMessage());
        }
        /*   Long id;
             String fotoURL;
             String nome;
             String descricao;
             Double valor;
            */
        String sql3 = "CREATE TABLE IF NOT EXISTS " + TABELA_PRODUTOS
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "fotoURL VARCHAR(100) NOT NULL,"
                + "nome VARCHAR(50) NOT NULL,"
                + "descricao VARCHAR(50),"
                + "valor float NOT NULL);";

        try{
            db.execSQL(sql3);
            Log.i("INFO DB","Sucesso ao criar ao tabela produtos!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela produtos"+e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_USUARIOS +";";

        try{
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB","Sucesso ao criar a tabela usuarios!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela usuarios"+e.getMessage());
        }

        String sql2 = "DROP TABLE IF EXISTS " + TABELA_CEPS +";";

        try{
            db.execSQL(sql2);
            onCreate(db);
            Log.i("INFO DB","Sucesso ao criar a tabela ceps!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela ceps"+e.getMessage());
        }

        String sql3 = "DROP TABLE IF EXISTS " + TABELA_PRODUTOS +";";

        try{
            db.execSQL(sql3);
            onCreate(db);
            Log.i("INFO DB","Sucesso ao criar a tabela produtos!");
        }catch(Exception e){
            Log.i("INFO DB","Erro ao criar tabela produtos"+e.getMessage());
        }

    }
}
