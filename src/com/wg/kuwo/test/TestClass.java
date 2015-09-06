package com.wg.kuwo.test;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.*;

/**
 * Created by EXP on 2015/7/28.
 */
public class TestClass {
    public static void getMediaStore(Activity activity){
        File reslut = new File("/storage/emulated/0/list.txt");
//        File reslut = new File("/storage/sdcard1/list.txt");
        if(reslut.exists()){
            try {
                reslut.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reslut),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentResolver contentResolver = activity.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor cursor = contentResolver.query(uri, null,MediaStore.Audio.Media.DURATION+">?",new String[]{"25000"}, null);
        Cursor cursor = contentResolver.query(uri, null,null,null, null);
        String[] columns = cursor.getColumnNames();
        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext()){

            for(String s:columns){
                String item = cursor.getString(cursor.getColumnIndex(s));
                try {
                    bw.write(s +" : ");
                    bw.write(item==null?"":item);
                    bw.write("\t");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));//������
//            String zj = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));//ר��
//            String geshou = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));//����
//            String dname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));//��ʾ����
//            String shijian = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));//--ʱ��
//            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));//路径
//            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));//
//            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));//
//            String str = "歌曲名"+name +" 专辑:"+zj+" 歌手:"+geshou+" 显示名字:"+dname +" 时间:"+shijian+" 路径："+data;
//            Log.e("Music -->", str);
        }
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
