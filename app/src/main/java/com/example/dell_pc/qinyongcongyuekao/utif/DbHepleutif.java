package com.example.dell_pc.qinyongcongyuekao.utif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * 作者：秦永聪
 *日期：2018/9/25
 * */public class DbHepleutif {
    private  SQLiteDatabase db;
     public DbHepleutif(Context context){
         DbHeple dbHeple = new DbHeple(context, "bm.db", null, 1);
          db = dbHeple.getReadableDatabase();
     }
     //添加
     public void add(String data){
         ContentValues values = new ContentValues();
         values.put("data",data);
         db.insert("bm",null,values);
     }
     //查询
    public String all(){
        String s="";
        Cursor bm = db.rawQuery("select bm from bm", null);
        while(bm.moveToNext()){
             s = bm.getString(bm.getColumnCount());
        }
        return s;
    }
}
