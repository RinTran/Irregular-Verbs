package com.rintran.irregularverbs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class SQLDataSource {
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper databaseHelper;

    public SQLDataSource(Context context){
        databaseHelper =  new DatabaseHelper(context);
        databaseHelper.createDatabase();
        sqLiteDatabase = databaseHelper.openDB();
    }

    public List<Verbs> getListVerb(){

        List<Verbs> list = new ArrayList<>();

        String[] colum = {DatabaseHelper.NGUYENMAU,DatabaseHelper.QUAKHU,DatabaseHelper.QUAKHUPHANTU,DatabaseHelper.NGHIA};

        Cursor c = sqLiteDatabase.query(DatabaseHelper.TABLE_VERB, colum, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Verbs item = new Verbs();
            item.setNguyenmau(c.getString(0));
            item.setQuakhu(c.getString(1));
            item.setQuakhuphantu(c.getString(2));
            item.setNghia(c.getString(3));

            list.add(item);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public List<Verbs> getListVerbByKey(String nguyenmau){

        List<Verbs> list = new ArrayList<>();

        String[] colum = {DatabaseHelper.NGUYENMAU,DatabaseHelper.QUAKHU,DatabaseHelper.QUAKHUPHANTU,DatabaseHelper.NGHIA};

        //Cursor c = sqLiteDatabase.query(DatabaseHelper.TABLE_VERB, colum, null, null, null, null, null);
        String truyvan = "Select " + colum[0] + " , " + colum[1]    + " , " + colum[2] + " , " + colum[3] +
                " From " + DatabaseHelper.TABLE_VERB +
                " Where " + DatabaseHelper.NGUYENMAU +
                " LIKE '%"+ nguyenmau + "%'";
        Cursor c = sqLiteDatabase.rawQuery(truyvan, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Verbs item = new Verbs();
            item.setNguyenmau(c.getString(0));
            item.setQuakhu(c.getString(1));
            item.setQuakhuphantu(c.getString(2));
            item.setNghia(c.getString(3));

            list.add(item);
            c.moveToNext();
        }
        c.close();
        return list;
    }
}
