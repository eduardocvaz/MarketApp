package com.example.marketapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.marketapp.model.CEP;
import com.example.marketapp.utils.DBHelper;

public class CEPDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public CEPDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }



    public boolean salvar(CEP cep){

        //1. definir o conteudo a ser salvo

        ContentValues cv = new ContentValues();

    /*   Long id;
         String cep;
         String logradouro;
         String complemento;
         String bairro;
         String localidade;
         String uf;*/

        cv.put("cep",cep.getCep());
        cv.put("logradouro",cep.getLogradouro());
        cv.put("complemento",cep.getComplemento());
        cv.put("bairro",cep.getBairro());
        cv.put("localidade",cep.getLocalidade());
        cv.put("uf",cep.getUf());

        try{
            escreve.insert(DBHelper.TABELA_CEPS,null,cv);
            Log.i("INFO","Registro salvo com sucesso na tabela ceps!");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar registro na tabela ceps: "+e.getMessage());
            return false;
        }
        return true;
    }

    public boolean atualizar(CEP cep){

        //1. definir conteudo a ser salvo
        ContentValues cv = new ContentValues();

        cv.put("cep",cep.getCep());
        cv.put("logradouro",cep.getLogradouro());
        cv.put("complemento",cep.getComplemento());
        cv.put("bairro",cep.getBairro());
        cv.put("localidade",cep.getLocalidade());
        cv.put("uf",cep.getUf());

        //2. atualizar valor no banco
        try{
            String[] args = {cep.getId().toString()};
            //2.1 update(nome da tabela, conteudo para atualizar, clausula de atualização (where)
            // o argumento da condição --> ?)
            escreve.update(DBHelper.TABELA_CEPS,cv,"id=?",args);
            Log.i("INFO","Registro atualizado com sucesso na tabela produtos!");
        }catch(Exception e){
            Log.i("INFO","Erro ao atualizar registro na tabela produtos!" + e.getMessage());
            return false;
        }
        return true;
    }


    public CEP buscarPorId(String id) {
        String sql = "SELECT * FROM " +DBHelper.TABELA_CEPS + " WHERE id='"+id+"';";
        CEP cep = new CEP();

        try{
            Cursor c = le.rawQuery(sql, null);

                /*   Long id;
         String cep;
         String logradouro;
         String complemento;
         String bairro;
         String localidade;
         String uf;*/

            Long myid = c.getLong( c.getColumnIndexOrThrow("id") );
            String Mycep = c.getString(c.getColumnIndexOrThrow("cep"));
            String logradouro = c.getString(c.getColumnIndexOrThrow("logradouro"));
            String complemento = c.getString(c.getColumnIndexOrThrow("complemento"));
            String bairro = c.getString(c.getColumnIndexOrThrow("bairro"));
            String localidade = c.getString(c.getColumnIndexOrThrow("localidade"));
            String uf = c.getString(c.getColumnIndexOrThrow("uf"));

            cep.setId(myid);
            cep.setCep(Mycep);
            cep.setLogradouro(logradouro);
            cep.setComplemento(complemento);
            cep.setBairro(bairro);
            cep.setLocalidade(localidade);
            cep.setUf(uf);
            c.close();
            Log.i("INFO","O objeto recuperado com sucesso da tabela usuarios!");
        }catch(Exception e){
            Log.i("INFO","Erro recuperar registro da tabela usuarios!"+e.getMessage());
            return null;
        }

        return cep;
    }

}
