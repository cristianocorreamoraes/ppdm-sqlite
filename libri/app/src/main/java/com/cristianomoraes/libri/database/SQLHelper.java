package com.cristianomoraes.libri.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cristianomoraes.libri.helpers.DateFormat;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "libri.db";
    private static final int DB_VERSION = 1;
    private static SQLHelper INSTANCE;

    public static SQLHelper getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = new SQLHelper(context);

        }

        return INSTANCE;

    }

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE tbl_usuario" +
                                "(cod_usuario INTEGER PRIMARY KEY, " +
                                "nome TEXT, sobrenome TEXT, email TEXT, " +
                                "login TEXT, senha TEXT, created_date DATETIME);");

        Log.d("SQLITE", "BANCO CRIADO COM SUCESSO! - VERSÃO: " + DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        Log.d("SQLITE", "BANCO ALTERADO COM SUCESSO! - VERSÃO: " + DB_VERSION);

    }

    /** INSERÇÃO DE DADOS **/
    public int addUser(String nome, String sobrenome, String email, String login, String senha, String created_date){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int cod_usuario = 0;

        try{

            sqLiteDatabase.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("nome", nome);
            values.put("sobrenome", sobrenome);
            values.put("email", email);
            values.put("login", login);
            values.put("senha", senha);

            DateFormat df = new DateFormat();
            values.put("created_date", df.getDateFormat());

            cod_usuario = (int) sqLiteDatabase.insertOrThrow("tbl_usuario", null, values);
            sqLiteDatabase.setTransactionSuccessful();

        }catch (Exception e){

            Log.d("SQLITE", e.getMessage());

        }finally{

            if(sqLiteDatabase.isOpen()){
                sqLiteDatabase.endTransaction();
            }

            return cod_usuario;

        }

    }

    /** INSERÇÃO DE DADOS **/
    public int login(String login, String senha){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_usuario WHERE login = ? AND senha = ?", new String[]{login, senha});

        int cod_usuario = 0;
        try{

            if(cursor.moveToFirst()){

                Log.d("COUNT-", String.valueOf(cursor.getCount()));
                cod_usuario = cursor.getInt(cursor.getColumnIndex("cod_usuario"));
                return cod_usuario;

            }else{

                Log.d("COUNT-", String.valueOf(cursor.getCount()));

            }

        }catch(Exception e){

            Log.d("SQLite", e.getMessage());

        }finally{

            if(cursor != null && !cursor.isClosed()){

                cursor.close();

            }

        }

        return 0;

    }
}
