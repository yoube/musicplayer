package com.wg.kuwo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.wg.kuwo.bean.MusicBean;

/**
 * Created by EXP on 2015/7/30.
 */
public abstract class MusicBroadcast extends BroadcastReceiver {
    public static final String MUSIC_STATUS = "com.wg.kuwo.MUSIC_PLAY_STATUS";
    public static final String MUSIC_NEXT = "com.wg.kuwo.MUSIC_PLAY_NEXT";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(MUSIC_STATUS.equals(action)){
            MusicBean bean = intent.getParcelableExtra("MUSIC");
            musicChangePlayStatus(bean ,intent.getBooleanExtra("STATUS", false));
        }else if(MUSIC_NEXT.equals(action)){

        }
    }

    /**
     * ÒôÀÖ²¥·Å×´Ì¬¸Ä±ä
     * @param status
     */
    public abstract void musicChangePlayStatus(MusicBean bean ,boolean status);


}

