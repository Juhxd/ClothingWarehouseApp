package com.example.clothingwarehouseapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionFactory extends SQLiteOpenHelper {

    private static final String NAME = "banco.bd";
    private static final int VERSION = 1;

    public ConnectionFactory(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sql_camiseta = "CREATE TABLE camiseta(idCamiseta INTERGER PRIMARY KEY AUTOINCREMENT, ivCamiseta BLOB);";
        String sql_calca = "CREATE TABLE calca(idCalca INTERGER PRIMARY KEY AUTOINCREMENT, ivCalca BLOB);";
        String sql_calcado = "CREATE TABLE calcado(idCalcado INTERGER PRIMARY KEY AUTOINCREMENT, ivCalcado BLOB);";

        bd.execSQL(sql_camiseta);
        bd.execSQL(sql_calca);
        bd.execSQL(sql_calcado);

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        String sql_upgrade_camiseta = "DROP TABLE IF EXISTS camiseta";
        String sql_upgrade_calca = "DROP TABLE IF EXISTS calca";
        String sql_upgrade_calcado = "DROP TABLE IF EXISTS calca";

        bd.execSQL(sql_upgrade_camiseta);
        bd.execSQL(sql_upgrade_calca);
        bd.execSQL(sql_upgrade_calcado);

        onCreate(bd);

    }
}
