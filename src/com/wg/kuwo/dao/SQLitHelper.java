package com.wg.kuwo.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.wg.kuwo.bean.ListBean;
import com.wg.kuwo.bean.MusicBean;

/**
 * Created by EXP on 2015/7/24.
 */
public class SQLitHelper extends SQLiteOpenHelper {
    private static SQLitHelper sqlitHelper;

    public static SQLitHelper getInstens(Context context) {
        if (sqlitHelper == null) {
            sqlitHelper = new SQLitHelper(context);
        }
        return sqlitHelper;
    }

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "music.db";

    public SQLitHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer sbf = new StringBuffer();
        //创建music表
        sbf.append("create table ");
        sbf.append(MusicBean.TAB_NAME);
        sbf.append("( ");
        sbf.append(MusicBean._ID);
        sbf.append(" integer primary key,");
        sbf.append(MusicBean.NAME);
        sbf.append(" text,");
        sbf.append(MusicBean.DOWNLOAD_STUST);
        sbf.append(" text,");
        sbf.append(MusicBean.PATH);
        sbf.append(" text,");
        sbf.append(MusicBean.LIKE);
        sbf.append(" text )");

        sqLiteDatabase.execSQL(sbf.toString());
//        sbf
        //创建musicList 表
        sbf = new StringBuffer();
        sbf.append("create table ");
        sbf.append(ListBean.TAB_NAME);
        sbf.append("( ");
        sbf.append(ListBean._ID);
        sbf.append("integer primary key,");
        sbf.append(ListBean.NAME);
        sbf.append(" text,");
        sbf.append(ListBean.CREATEDATA);
        sbf.append(" integer )");

        sqLiteDatabase.execSQL(sbf.toString());

        //创建关系表
        sbf = new StringBuffer();
        sbf.append("create table ");
        sbf.append(" music_list (");
        sbf.append(" music_id");
        sbf.append(" integer,");
        sbf.append(" list_id");
        sbf.append(" integer )");
        sqLiteDatabase.execSQL(sbf.toString());

        //添加外鍵
        sbf = new StringBuffer();
        sbf.append("alter music_list add foreign key music_id references ");
        sbf.append(MusicBean.TAB_NAME);
        sbf.append("(");
        sbf.append(MusicBean._ID);
        sbf.append(")");
        sqLiteDatabase.execSQL(sbf.toString());

        //添加外鍵
        sbf = new StringBuffer();
        sbf.append("alter music_list add foreign key list_id references ");
        sbf.append(ListBean.TAB_NAME);
        sbf.append("(");
        sbf.append(ListBean._ID);
        sbf.append(")");
        sqLiteDatabase.execSQL(sbf.toString());


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
