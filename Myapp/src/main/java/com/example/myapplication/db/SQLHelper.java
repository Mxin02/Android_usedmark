package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by L on 2018/2/10.
 */

public class SQLHelper extends SQLiteOpenHelper {

    public static final String CREATE = "create table USER ("
            + "id integer primary key autoincrement, " + "phoneNum text, " + "money text, "
            + "password text)";

    public static final String CREATE_Goods = "create table MyGoods ("
            + "id integer primary key autoincrement, " + "title text, " + "msg text, " + "price text, " + "phone text, " + "location text, " + "sellnum text, " + "usename text, "
            + "img text)";

    public static final String CREATE_REMARK = "create table MyRemark ("
            + "id integer primary key autoincrement, " + "postion text, " + "username text, " + "date text, "
            + "remark text)";
    public static final String CREATE_ANSWER = "create table MyAnswer ("
            + "id integer primary key autoincrement, " + "answerId text, " + "name text, "
            + "answers text)";
    public static final String CREATE_SELL = "create table MySell ("
            + "id integer primary key autoincrement, " + "username text, " + "number text, " + "time text, " + "title text, " + "img text, "
            + "buygoodsid text)";

    public static final String CREATE_Bill = "create table MyBill ("
            + "id integer primary key autoincrement, " + "username text, "
            + "money text)";


    public SQLHelper(Context context) {
        super(context, "message", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_Goods);
        sqLiteDatabase.execSQL(CREATE);
        sqLiteDatabase.execSQL(CREATE_REMARK);
        sqLiteDatabase.execSQL(CREATE_ANSWER);
        sqLiteDatabase.execSQL(CREATE_SELL);
        sqLiteDatabase.execSQL(CREATE_Bill);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
