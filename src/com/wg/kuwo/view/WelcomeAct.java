package com.wg.kuwo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import com.wg.kuwo.MainActivity;
import com.wg.kuwo.R;
import com.wg.kuwo.bean.MusicBean;
import com.wg.kuwo.service.MusicPalyerService;

import java.util.List;

/**
 * Created by EXP on 2015/7/23.
 */
public class WelcomeAct extends Activity{
    public List<MusicBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater.from(this).inflate(R.layout.main,null).setBackgroundResource(R.drawable.bk);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_layout);
        //启动服务
        Intent intent = new Intent(this, MusicPalyerService.class);
        startService(intent);

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(WelcomeAct.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
