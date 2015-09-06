package com.wg.kuwo.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by EXP on 2015/7/28.
 */
public class ListDao {
    private static SQLitHelper sqLitHelper;

    public void findByName(String name){
        SQLiteDatabase db = sqLitHelper.getReadableDatabase();
//        db.query("music_list",);
        db.close();
    }


}
