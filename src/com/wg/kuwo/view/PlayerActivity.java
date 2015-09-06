package com.wg.kuwo.view;

import android.app.Activity;
import android.app.Service;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wg.kuwo.R;
import com.wg.kuwo.bean.MusicBean;
import com.wg.kuwo.broadcast.MusicBroadcast;
import com.wg.kuwo.service.MusicPalyerService;

/**
 * Created by EXP on 2015/7/29.
 */
public class PlayerActivity extends Activity implements View.OnClickListener {

    private ImageView mPlayImg;
    private TextView mTextTime,mMusicName;
    private SeekBar mSeekBar;
    private boolean threadFlag = true;
    private MusicPalyerService.MusicServicebinder binder;
    private MusicBroadcast musicBroadcast = new MusicBroadcast() {
        @Override
        public void musicChangePlayStatus(MusicBean bean, boolean status) {
            setMusicInfo(bean,status);
        }
    };
    Handler mHandler =new Handler();
    //service connection
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (MusicPalyerService.MusicServicebinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            binder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palyer_layout);
        //绑定服务
        Intent intent = new Intent(this, MusicPalyerService.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicBroadcast.MUSIC_STATUS);
        registerReceiver(musicBroadcast, intentFilter);

        //初始化控件
        mPlayImg = (ImageView) findViewById(R.id.player_play_img);
        mTextTime = (TextView) findViewById(R.id.player_music_time);
        mMusicName = (TextView) findViewById(R.id.localmusic_actionbar_name);
        mPlayImg.setOnClickListener(this);
        findViewById(R.id.player_play_next_img).setOnClickListener(this);
        findViewById(R.id.player_play_up_img).setOnClickListener(this);

        Intent actvityIntent = getIntent();
        setMusicInfo(actvityIntent.getParcelableExtra("MUSIC"),actvityIntent.getBooleanExtra("STATUS",false));

        mSeekBar = (SeekBar) findViewById(R.id.player_seekbar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    binder.setMusicProgess(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        new Thread() {
            @Override
            public void run() {
                while (threadFlag){
                    if(binder!=null){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                int curProgess = binder.getCurredProgess();
                                int max = binder.getMusicDuration();
                                mSeekBar.setMax(max);
                                mSeekBar.setProgress(curProgess);
//                                String s = (max/1000/60)+":"+(max/1000%60);
//                                String us = (curProgess/1000/60)+":"+(curProgess/1000%60);
                                mTextTime.setText(timeFmart(curProgess)+" | "+timeFmart(max));
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

        }.start();

    }
    private void setMusicInfo(MusicBean bean, boolean status){
        if (status) {
            mPlayImg.setImageResource(R.drawable.selector_pause_img);
        } else {
            mPlayImg.setImageResource(R.drawable.selector_play_img);
        }
        if(bean==null)return;
        mMusicName.setText(bean.getName());
    }
    private String timeFmart(int time){
        int s = time/1000/60;
        int us = time/1000%60;
        return (s<10?"0"+s:s)+":"+(us<10?"0"+us:us);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.e("onPause", "" + binder);
//        mSeekBar.setMax(binder.getMusicDuration());
//        //启动线程
//        new Thread(){
//            @Override
//            public void run() {
//                int max =binder.getMusicDuration();
//                mSeekBar.setMax(max);
//                while (threadFlag){
//                    new Handler().post(new Runnable() {
//                        @Override
//                        public void run() {
//                            int curProgess = binder.getCurredProgess();
//                            mSeekBar.setProgress(curProgess);
//
//
//                        }
//                    });
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        threadFlag = false;
        unbindService(conn);
        unregisterReceiver(musicBroadcast);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.player_play_img:
                Intent intent = new Intent();
                intent.setAction(MusicPalyerService.MUSIC_PLAYORPAUST);
                sendBroadcast(intent);
                break;
            case R.id.player_play_next_img:
//                Log.e("------", "" + binder);
                binder.playNext();
                break;
            case R.id.player_play_up_img:
                binder.playUp();
                break;
        }

    }
}
