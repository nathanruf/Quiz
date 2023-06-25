package com.example.quiz.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quiz.Cadastro;

public class SqlliteConnector extends SQLiteOpenHelper {
    private SQLiteDatabase writer = getWritableDatabase();
    private SQLiteDatabase reader = getReadableDatabase();
    private ContentValues content = new ContentValues();

    private static final String TABLE_USERS = "login";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "nome";
    private static final String COLUMN_PASSWORD = "senha";
    private static final String COLUMN_EMAIL = "email";
    private static final String BANCO = "login";
    private static final int DATABASE_version =1;


    public SqlliteConnector(Context context) {
        super(context, BANCO, null,DATABASE_version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists login(id integer primary key autoincrement, nome text, senha text, email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean checkUser(String nome, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_EMAIL, COLUMN_PASSWORD},
                COLUMN_EMAIL + "=? and " + COLUMN_PASSWORD + "=?",
                new String[]{nome, senha},
                null,
                null,
                null,
                "1");

        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_EMAIL},
                COLUMN_EMAIL + "=?",
                new String[]{email},
                null,
                null,
                null,
                "1");

        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    public void cadastro(Cadastro cadastro){
        this.content.put("email",cadastro.getEmail());
        this.content.put("senha", cadastro.getSenha_cod());

        try{
            long sucess = this.writer.insert("login", null, this.content);
        }catch(Exception ex) {
            Log.e("##", "Erro no banco de dados : " +ex );
        }
    }
}
