package com.rintran.irregularverbs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATA_NAME = "IrregularVerbs";
    public static final String TABLE_VERB = "Verbs";

    public static final String NGUYENMAU = "NGUYENMAU";
    public static final String QUAKHU = "QUAKHU";
    public static final String QUAKHUPHANTU = "QUAKHUPHANTU";
    public static final String NGHIA = "NGHIA";

    Context context;

    String linkDatabase = "";

    public DatabaseHelper(Context context) {
        super(context,DATA_NAME,null,1);
        this.context = context;
        linkDatabase = context.getFilesDir().getParent() + "/databases/" + DATA_NAME ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkDatabase(){
        SQLiteDatabase kiemTraDB = null;
        try {
            kiemTraDB = SQLiteDatabase.openDatabase(linkDatabase,null,kiemTraDB.OPEN_READONLY);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (kiemTraDB!=null){
            kiemTraDB.close();
        }
        return kiemTraDB != null;
    }

    public SQLiteDatabase openDB(){
        return SQLiteDatabase.openDatabase(linkDatabase, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDatabase(){
        try {
            InputStream inputStream = context.getAssets().open(DATA_NAME);
            OutputStream outputStream = new FileOutputStream(linkDatabase);
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, lenght);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDatabase(){
        boolean check = checkDatabase();
        if (check){
            Log.d("Ket noi","ket noi thanh cong");
        }else{
            Log.d("Ket noi","khong the ket noi");
            this.getWritableDatabase(); // tao thu muc database
            copyDatabase();
        }
    }
}  
