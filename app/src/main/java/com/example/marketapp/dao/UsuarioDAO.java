package com.example.marketapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.marketapp.model.Usuario;
import com.example.marketapp.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;
    private Context context;

    public UsuarioDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
        this.context = context;
    }

    public boolean salvar(Usuario usuario){

        //1. definir o conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("nome",usuario.getNome());
        cv.put("cpf",usuario.getCpf());
        cv.put("cepId",usuario.getCep().getId());

        try{
            escreve.insert(DBHelper.TABELA_USUARIOS,null,cv);
            Log.i("INFO","Registro salvo com sucesso na tabela usuarios!");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar registro na tabela usuarios: "+e.getMessage());
            return false;
        }
        return true;
    }

    public List<Usuario> listar(){

        List<Usuario> usuarios = new ArrayList<>();

        //1. string sql de consulta
        String sql = "SELECT * FROM "+DBHelper.TABELA_USUARIOS+ ";";

        //2. Cursor para acesso aos dados
        Cursor c = le.rawQuery(sql,null);

        //3. percorrer o cursor
        c.moveToFirst();
        while(c.moveToNext()){

            Usuario usuario = new Usuario();
            /*    private Long id;
            private String nome;
            private String cpf;
            private CEP cep;*/
            //Long id = c.getLong( 0 );
            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String nome = c.getString(c.getColumnIndexOrThrow("nome"));
            String cpf = c.getString(c.getColumnIndexOrThrow("cpf"));
            String cepId = c.getString(c.getColumnIndexOrThrow("cepId"));

            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setCpf(cpf);

            usuarios.add(usuario);
        }
        c.close();
        return usuarios;
    }

    public boolean atualizar(Usuario usuario){

        //1. definir conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("nome",usuario.getNome());
        cv.put("cpf",usuario.getCpf());
        cv.put("cepId",usuario.getCep().getId());

        //2. atualizar valor no banco
        try{
            String[] args = {usuario.getId().toString()};
            //2.1 update(nome da tabela, conteudo para atualizar, clausula de atualização (where)
            // o argumento da condição --> ?)
            escreve.update(DBHelper.TABELA_USUARIOS,cv,"id=?",args);
            Log.i("INFO","Registro atualizado com sucesso na tabela usuarios!");
        }catch(Exception e){
            Log.i("INFO","Erro ao atualizar registro na tabela usuarios!" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletar(Usuario usuario){

        //1. deletar um registro de tarefa na tabela tarefas

        try{
            //id do registro que será deletado
            String[] args = {usuario.getId().toString()};
            escreve.delete(DBHelper.TABELA_USUARIOS,"id=?",args);
            Log.i("INFO","Registro apagado com sucesso da tabela usuarios!");
        }catch(Exception e){
            Log.i("INFO","Erro apagar registro da tabela usuarios!"+e.getMessage());
            return false;
        }
        return true;
    }

    public Usuario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM " +DBHelper.TABELA_USUARIOS+ " WHERE cpf='"+cpf+"';";
        Usuario usuario = new Usuario();

        try{
            Cursor c = le.rawQuery(sql, null);
            c.moveToFirst();

            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String nome = c.getString(c.getColumnIndexOrThrow("nome"));
            String myCpf = c.getString(c.getColumnIndexOrThrow("cpf"));
            String cepId = c.getString(c.getColumnIndexOrThrow("cepId"));

            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setCpf(myCpf);
            usuario.setCep(new CEPDAO(context).buscarPorId(cepId));

            c.close();
            Log.i("INFO","O objeto recuperado com sucesso da tabela usuarios!");
        }catch(Exception e){
            Log.i("INFO","Erro recuperar registro da tabela usuarios!"+e.getMessage());
            return null;
        }

        return usuario;
    }


}
