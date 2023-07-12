package com.example.clothingwarehouseapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.clothingwarehouseapp.Armario;
import com.example.clothingwarehouseapp.model.Armazen;
import com.example.clothingwarehouseapp.util.ConnectionFactory;

public class ArmarioDAO {

    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    public ArmarioDAO(Context context){
        //ConnectionFactory com o banco de dados
        conexao = new ConnectionFactory(context);
        banco = conexao.getWritableDatabase();
    }

    public long salvarcamiseta(byte[] camiseta){
        Log.d("DEBUG", "Objeto banco: " + banco);
        ContentValues values = new ContentValues();
        values.put("ivCamiseta", camiseta);
        return(banco.insert("armario", null, values));
    }
    public long salvarcalca(Armazen calca){
        ContentValues values = new ContentValues();
        values.put("ivCalca", calca.getIvCalca());
        return(banco.insert("armario", null, values));
    }
    public long salvarcalcado(Armazen calcado){
        ContentValues values = new ContentValues();
        values.put("ivCalcado", calcado.getIvCalcado());
        return(banco.insert("armario", null, values));
    }
}