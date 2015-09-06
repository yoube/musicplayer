package com.wg.kuwo.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import com.wg.kuwo.R;

/**
 * Created by EXP on 2015/7/28.
 */
public class LocalMusicActivity extends Activity {
    RadioGroup mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music_layout);
        mTabs = (RadioGroup) findViewById(R.id.localmusic_tab_head);

        mTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
    }
}
