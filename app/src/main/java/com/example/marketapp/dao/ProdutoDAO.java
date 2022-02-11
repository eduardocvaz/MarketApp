package com.example.marketapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.marketapp.model.Produto;
import com.example.marketapp.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public ProdutoDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    public boolean salvar(Produto produto){

        //1. definir o conteudo a ser salvo

        ContentValues cv = new ContentValues();
        cv.put("fotoURL",produto.getFotoURL());
        cv.put("nome",produto.getFotoURL());
        cv.put("descricao",produto.getDescricao());
        cv.put("valor",produto.getValor());

        try{
            escreve.insert(DBHelper.TABELA_PRODUTOS,null,cv);
            Log.i("INFO","Registro salvo com sucesso na tabela produtos!");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar registro na tabela produtos: "+e.getMessage());
            return false;
        }
        return true;
    }

    public List<Produto> listar(){

        List<Produto> produtos = new ArrayList<>();

        //1. string sql de consulta
        String sql = "SELECT * FROM "+DBHelper.TABELA_PRODUTOS+ ";";

        //2. Cursor para acesso aos dados
        Cursor c = le.rawQuery(sql,null);

        //3. percorrer o cursor
        c.moveToFirst();
        while(c.moveToNext()){

            Produto produto = new Produto();
             /*Long id;
               String fotoURL;
               String nome;
               String descricao;
               Double valor;
             */
            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String fotoURL = c.getString(c.getColumnIndexOrThrow("fotoURL"));
            String nome = c.getString(c.getColumnIndexOrThrow("nome"));
            String descricao = c.getString(c.getColumnIndexOrThrow("descricao"));
            Double valor = Double.valueOf(c.getString(c.getColumnIndexOrThrow("valor")));

            produto.setId(id);
            produto.setFotoURL(fotoURL);
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setValor(valor);


            produtos.add(produto);
        }
        c.close();
        return produtos;
    }


    public boolean atualizar(Produto produto){

        //1. definir conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("fotoURL",produto.getFotoURL());
        cv.put("nome",produto.getFotoURL());
        cv.put("descricao",produto.getDescricao());
        cv.put("valor",produto.getValor());


        //2. atualizar valor no banco
        try{
            String[] args = {produto.getId().toString()};
            //2.1 update(nome da tabela, conteudo para atualizar, clausula de atualização (where)
            // o argumento da condição --> ?)
            escreve.update(DBHelper.TABELA_PRODUTOS,cv,"id=?",args);
            Log.i("INFO","Registro atualizado com sucesso na tabela produtos!");
        }catch(Exception e){
            Log.i("INFO","Erro ao atualizar registro na tabela produtos!" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletar(Produto produto){

        //1. deletar um registro de tarefa na tabela tarefas

        try{
            //id do registro que será deletado
            String[] args = {produto.getId().toString()};
            escreve.delete(DBHelper.TABELA_USUARIOS,"id=?",args);
            Log.i("INFO","Registro apagado com sucesso da tabela produtos!");
        }catch(Exception e){
            Log.i("INFO","Erro apagar registro da tabela produtos!"+e.getMessage());
            return false;
        }
        return true;
    }


}
