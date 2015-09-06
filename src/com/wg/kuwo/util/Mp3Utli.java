package com.wg.kuwo.util;

import android.util.Log;

import java.io.*;

/**
 * 解析mp3 文件信息
 * Created by EXP on 2015/7/28.
 */
public class Mp3Utli {
    public static void getInfo(File file) {
//        file = new File("/storage/emulated/0/KuwoMusic/music/Love_The_Way_You_Lie-Eminem_Rihanna.mp3");
        if(file==null) return;
        Log.i("文件：", file.getName());
//        if (!file.getName().endsWith(".mp3")) {
//            Log.e("错误：", "不是MP3 文件！");
//
//            return;
//        }
        RandomAccessFile randomAccessFile = null;
        byte[] buf = new byte[128];
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(randomAccessFile.length() - 128);
            randomAccessFile.read(buf);
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(buf.length<128){

            return;
        }
        if(!"tag".equalsIgnoreCase(new String(buf,0,3))){
            Log.e("错误：","歌曲格式不正确！");
            return;
        }

        String str = null;
        try {
            str = new String(buf,3,33,"utf-8");
            String Artist = new String(buf,33,30,"utf-8").trim();//歌手名字

            String Album = new String(buf,63,30,"utf-8").trim();//专辑名称

            String Year = new String(buf,93,4,"utf-8").trim();//出品年份

            String Comment = new String(buf,97,28,"utf-8").trim();//备注信息
            Log.e("歌曲-->", str.trim()+"歌手名："+Artist+"专辑名："+Album+"年月："+Year+"备注："+Comment);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    }
}
