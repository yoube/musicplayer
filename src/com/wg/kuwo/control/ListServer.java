package com.wg.kuwo.control;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.util.Log;
import com.wg.kuwo.R;
import com.wg.kuwo.bean.MusicBean;
import com.wg.kuwo.util.Mp3Utli;
import com.wg.kuwo.util.StorageTool;

import java.io.File;
import java.util.*;

/**
 * Created by EXP on 2015/7/28.
 */
public class ListServer {
    private Context context;
    public int conut=0;
    private static ListServer listServer;
    private List<MusicBean> list;
    public static ListServer getInstens (Context context){
        if(listServer==null){
            listServer = new ListServer(context);
        }
        return listServer;
    }
    private ListServer(Context context){
        this.context = context;
        list = new ArrayList<MusicBean>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor =  contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null
                                         ,MediaStore.Audio.Media.DURATION+">? or "+MediaStore.Audio.Media.DURATION+" is null"
                                         ,new String[]{"100000"} ,null);
        while (cursor.moveToNext()){
            MusicBean bean = new MusicBean();
            bean.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            bean.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            bean.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));//专辑
            bean.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));//歌手名
            bean.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
//            Log.e("data",bean.getPath());
//            Log.e("duration",cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
//            Log.e("ismusic",cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)));
            list.add(bean);
        }

        conut = cursor.getCount();
    }
    public List<MusicBean> getMusicList(){
        return list;
    }
    public List<Map<String,Object>> getMyMusicList(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("icon", R.drawable.recom_top_local);
        map.put("name","本地歌曲");
        map.put("conut",conut+"首歌曲");
        map.put("reigthicon", R.drawable.nowplayplay_normal);
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("icon", R.drawable.nowplaydown_normal);
        map.put("name","我的下载");


        map.put("conut","0首歌曲，0首下载完成");
        list.add(map);
        return list;


    }

    //遍历文件获取MP3文件
//    public List<Map<String,Object>> getMyMusicList(){
//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//        for(int i=0;i<=1;i++){
//            String[] dris = StorageTool.getStorageList(context);
//            File f = new File(dris[i]);
//            listDir(f,list);
//            Log.e("dri-->"+i+" -> "+dris[i],f==null?"":f.getAbsolutePath());
//        }
//        Map<String, Object> map = new HashMap<String,Object>();
//        map.put("icon", R.drawable.recom_top_local);
//        map.put("name","本地歌曲");
//        map.put("conut",conut+"首歌曲");
//        map.put("reigthicon", R.drawable.nowplayplay_normal);
//        list.add(map);
//        map = new HashMap<String,Object>();
//        map.put("icon", R.drawable.nowplaydown_normal);
//        map.put("name","我的下载");
//
//
//        map.put("conut","0首歌曲，0首下载完成");
//        list.add(map);
//        return list;
//    }
//    private void listDir(File file,List<Map<String,Object>> list){
//
//        for(File f :file.listFiles()){
//            if(f.isDirectory()){
//                listDir(f,list);
//            }else if (f.getName().endsWith(".mp3")||f.getName().endsWith(".ape")) {
//                conut++;
//
//            }
//        }
//
//
//    }
}
