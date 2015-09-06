package com.wg.kuwo.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.wg.kuwo.bean.MusicBean;

/**
 * Created by EXP on 2015/7/26.
 */
public class MusicDao  {
    private static MusicDao musicDao;
    public static MusicDao getInstens(Context context){
        if(musicDao==null){
            musicDao = new MusicDao(context);
        }
        return musicDao;
    }
    private SQLitHelper sqLitHelper;
    private MusicDao (Context context){
        sqLitHelper = SQLitHelper.getInstens(context);
    }
    public void add(MusicBean bean){
        SQLiteDatabase db = sqLitHelper.getReadableDatabase();

        db.beginTransaction();
        db.execSQL(bean.getInsertSQL());
        db.endTransaction();
        db.close();
    }

    public MusicBean findById(int id){
        SQLiteDatabase db = sqLitHelper.getReadableDatabase();
        Cursor cursor = db.query(MusicBean.TAB_NAME
                , new String[]{MusicBean.NAME, MusicBean.DOWNLOAD_STUST, MusicBean.PATH, MusicBean.LIKE, MusicBean._ID}
                , MusicBean._ID + "=?"
                , new String[]{id + ""}, null, null, null);
        MusicBean bean = null;
        while (cursor.moveToNext()){
            bean = new MusicBean();
            bean.setId(cursor.getInt(cursor.getColumnIndex(bean._ID)));
            bean.setName(cursor.getString(cursor.getColumnIndex(bean.NAME)));
            bean.setPath(cursor.getString(cursor.getColumnIndex(bean.PATH)));
            bean.setDownloadStust(Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(bean.DOWNLOAD_STUST))));
            bean.setLike(Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(bean.LIKE))));
        }
        return bean;
    }
    public void del(int id){

    }
    public void edit(MusicBean bean){

    }





}
