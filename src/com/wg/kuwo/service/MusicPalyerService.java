package com.wg.kuwo.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.wg.kuwo.bean.MusicBean;
import com.wg.kuwo.broadcast.MusicBroadcast;
import com.wg.kuwo.util.MusicPalyer;
import com.wg.kuwo.view.PlayToolFragment;

import java.io.IOException;
import java.util.List;

/**
 * Created by EXP on 2015/7/30.
 */
public class MusicPalyerService extends Service {
    public static final String MUSIC_PLAY = "com.wg.kuwo.MUSIC_PLAY";
    public static final String MUSIC_NEXT = "com.wg.kuwo.MUSIC_PLAY_NEXT";
    public static final String MUSIC_PLAYORPAUST = "com.wg.kuwo.MUSIC_PLAYORPAUST";

    private MusicPalyer musicPalyer;
    private List<MusicBean> list;

    private BroadcastReceiver musicReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(MUSIC_PLAY.equals(action)){
                int index = intent.getIntExtra("INDEX",-1);
                boolean flag = musicPalyer.play(index);
                Intent intent1 = new Intent();
                intent1.setAction(MusicBroadcast.MUSIC_STATUS);
                intent1.putExtra("STATUS",flag);
                intent1.putExtra("MUSIC",list.get(index));
                sendBroadcast(intent1);
            }else if(MUSIC_NEXT.equals(action)){
                next();
            }else if(MUSIC_PLAYORPAUST.equals(action)){
                if(list==null) return;
                boolean flag = musicPalyer.play();
                Intent intent1 = new Intent();
                intent1.setAction(MusicBroadcast.MUSIC_STATUS);
                intent1.putExtra("STATUS",flag);
                int curindex = musicPalyer.getCurredMusicIndex();
                intent1.putExtra("MUSIC",list.get(curindex<0?0:curindex));
                sendBroadcast(intent1);
            }
        }
    };

    //binder¶ÔÏó
    public class MusicServicebinder extends Binder{
        public int getMusicDuration(){
            if(musicPalyer==null)return 0;
            return musicPalyer.getDuration();
        }
        public void setMusicProgess(int progess){
            if (musicPalyer==null)return;
            musicPalyer.seekTo(progess);
        }
        public int getCurredProgess(){
            if (musicPalyer==null)return 0;
//            if (musicPalyer==null||!musicPalyer.isPlaying()||musicPalyer.isPause())return 0;
            return musicPalyer.getCurrentPosition();
        }

        public void playNext(){
            if (musicPalyer==null)return;
            next();
        }
        public void playUp(){
            if (musicPalyer==null)return;
            up();
        }
        public boolean getMusicStatus(){
            return musicPalyer.isPlaying();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (musicPalyer == null){
            musicPalyer = new MusicPalyer();
            musicPalyer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    musicPalyer.playNext();
                }
            });

        }
        if(list==null&&intent!=null){
            list = intent.getParcelableArrayListExtra("MUSIC_LIST");
            if(list!=null)musicPalyer.setList(list);
        }

        Log.e("startCommand", "Ok");
        //×¢²á¹ã²¥
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MUSIC_PLAY);
        intentFilter.addAction(MUSIC_NEXT);
        intentFilter.addAction(MUSIC_PLAYORPAUST);
        registerReceiver(musicReceiver,intentFilter);

        return super.onStartCommand(intent, flags, startId);
    }
    public void next(){
        musicPalyer.playNext();
        Intent intent1 = new Intent();
        intent1.setAction(MusicBroadcast.MUSIC_STATUS);
        intent1.putExtra("STATUS",musicPalyer.isPlaying());
        intent1.putExtra("MUSIC",list.get(musicPalyer.getCurredMusicIndex()));
        sendBroadcast(intent1);
    }
    public void up(){
        musicPalyer.playupper();
        Intent intent1 = new Intent();
        intent1.setAction(MusicBroadcast.MUSIC_STATUS);
        intent1.putExtra("STATUS",musicPalyer.isPlaying());
        intent1.putExtra("MUSIC",list.get(musicPalyer.getCurredMusicIndex()));
        sendBroadcast(intent1);
    }
    @Override
    public IBinder onBind(Intent intent) {


        return new MusicServicebinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        if(musicPalyer!=null)musicPalyer.reset();
        musicPalyer=null;
        super.onDestroy();
    }
}
