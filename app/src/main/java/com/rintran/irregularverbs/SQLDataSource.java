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

    public List<Verbs> getListFavorite(){

        List<Verbs> list = new ArrayList<>();

        String[] colum = {DatabaseHelper.NGUYENMAU, DatabaseHelper.QUAKHU, DatabaseHelper.QUAKHUPHANTU, DatabaseHelper.NGHIA};

        Cursor c = sqLiteDatabase.query(DatabaseHelper.TABLE_FAVORITE, colum, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
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

    public boolean insertFavorite(Verbs v) {
//        INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
//        VALUES (1, 'Paul', 32, 'California', 20000.00 );
        String truyvan = "INSERT INTO " + DatabaseHelper.TABLE_FAVORITE +
                "( " + DatabaseHelper.NGUYENMAU + ", " + DatabaseHelper.QUAKHU +
                ", " + DatabaseHelper.QUAKHUPHANTU + ", " + DatabaseHelper.NGHIA + ") " +
                "VALUES (" + "'" + v.getNguyenmau() + "'" + " ," + "'" + v.getQuakhu() + "'" + " ," + "'" +
                v.getQuakhuphantu() + "'" + " ," + "'" + v.getNghia() + "'" + " );";
        Cursor c = sqLiteDatabase.rawQuery(truyvan, null);
        c.moveToFirst();
        return true;
    }

    public boolean removeFromFavorite(String nguyenmau) {
//        DELETE FROM table_name
//        WHERE [condition];
        String truyvan = "DELETE FROM " + DatabaseHelper.TABLE_FAVORITE +
                " WHERE " + DatabaseHelper.NGUYENMAU + " = " + "'" + nguyenmau + "';";
        Cursor c = sqLiteDatabase.rawQuery(truyvan, null);
        c.moveToFirst();
        return true;
    }

//    public int getBackgroundId(){
//        int bg = 0;
//        String truyvan = "SELECT *" +
//                " FROM " + DatabaseHelper.TABLE_STATUS +
//                " WHERE " + DatabaseHelper.NAME +
//                " = 'backgroundid';";
//        Log.v("get bgid",truyvan);
//        Cursor c = sqLiteDatabase.rawQuery(truyvan, null);
//        c.moveToFirst();
//        while (!c.isAfterLast()){
//            bg = Integer.parseInt(c.getString(0));
//              c.moveToNext();
//        }
//        c.close();
//        return 0;
//    }

//    public boolean updateBacground(int bg){
////        UPDATE table_name
////        SET column1 = value1, column2 = value2...., columnN = valueN
////        WHERE [condition];
//        String truyvan = "UPDATE " + DatabaseHelper.TABLE_STATUS +
//        " SET " + DatabaseHelper.BACKGROUND + " = " + bg +
//        " WHERE rowid = 1;";
//        Cursor c = sqLiteDatabase.rawQuery(truyvan, null);
//        c.moveToFirst();
//        return true;
//    }
}
