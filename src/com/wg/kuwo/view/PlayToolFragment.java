package com.wg.kuwo.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.*;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wg.kuwo.R;
import com.wg.kuwo.bean.MusicBean;
import com.wg.kuwo.broadcast.MusicBroadcast;
import com.wg.kuwo.service.MusicPalyerService;

/**
 * Created by EXP on 2015/7/23.
 */
public class PlayToolFragment extends Fragment implements View.OnClickListener{
    private ImageView mPlayImg,mPlayNextImg,mPlayListImg;
    private TextView mMusicName, mDiscription;

    private MusicBean mCurredMusic;
    private boolean MusicStatus;

    private MusicBroadcast palytoolRecceiver = new MusicBroadcast() {
        @Override
        public void musicChangePlayStatus(MusicBean bean, boolean status) {

            setPlayImg(status);

            getActivity().getIntent().putExtra("MUSIC", bean).putExtra("STATUS", status);
            //设置音乐信息
            setMusicInfo(bean);

        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        Intent intent = getActivity().getIntent();

        view = inflater.inflate(R.layout.fragment_player_tool_layout, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                Intent activityIntent = getActivity().getIntent();
                intent.putExtra("STATUS",MusicStatus);
                intent.putExtra("MUSIC",mCurredMusic);
                startActivity(intent);
            }
        });

        mMusicName = (TextView) view.findViewById(R.id.play_music_name);
        mDiscription = (TextView) view.findViewById(R.id.play_music_discription);
        //播放暂停
        mPlayImg = (ImageView) view.findViewById(R.id.play_play_img);
        mPlayImg.setOnClickListener(this);
        mPlayNextImg = (ImageView) view.findViewById(R.id.play_playnext_img);
        mPlayNextImg.setOnClickListener(this);

        setPlayImg(intent.getBooleanExtra("STATUS", false));
        setMusicInfo(intent.getParcelableExtra("MUSIC"));

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicBroadcast.MUSIC_STATUS);
        getActivity().registerReceiver(palytoolRecceiver, intentFilter);

        return view;
    }

    public void setPlayImg(boolean flag) {
        MusicStatus = flag;
        if (flag) {
            mPlayImg.setImageResource(R.drawable.selector_pause_img);
        } else {
            mPlayImg.setImageResource(R.drawable.selector_play_img);
        }
    }
    public void setMusicInfo(MusicBean bean){
        mCurredMusic =bean;
        if(bean==null) return;
        mMusicName.setText(bean.getName());
        mDiscription.setText(bean.getArtist() + bean.getAlbum());
    }

    private void error() {
//        view = inflater.inflate(R.layout.fragment_player_tool_layout, null);
//        ViewPager viewPager = (ViewPager) view.findViewById(R.id.main_fragment_viewpager);
//        PagerTabStrip pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.main_fragment_pagertabstrip);
//        pagerTabStrip.setTextSpacing(10);
//        //取消横线
//        pagerTabStrip.setDrawFullUnderline(false);
//        pagerTabStrip.setTabIndicatorColor(0xffffff);
//        FragmentManager fragmentManager = getFragmentManager();

//        FragmentActivity

//        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
//
//            @Override
//            public int getCount() {
//                return 0;
//            }
//
//            @Override
//            public android.support.v4.app.Fragment getItem(int i) {
//                return null;
//            }
//        });
    }

    //设置viewpager
    private void setAdapter() {
//        List<View> viewse = new ArrayList();
//        List<String> tabs = new ArrayList();
//        viewse.add(inflater.inflate(R.layout.view_my_layout, null));
//        viewse.add(inflater.inflate(R.layout.view_tuijian_layout, null));
//        viewse.add(inflater.inflate(R.layout.view_quku_layout, null));
//        viewse.add(inflater.inflate(R.layout.view_shiping_layout, null));
//
//        tabs.add("我的");
//        tabs.add("推荐");
//        tabs.add("曲库");
//        tabs.add("视频");
//        viewPager.setAdapter(new PagerAdapter() {
//
//            @Override
//            public int getCount() {
//                return viewse.size();
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object o) {
//                return view == o;
//            }
//
//            //生成的对象
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                container.addView(viewse.get(position));
//                return viewse.get(position);
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView(viewse.get(position));
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return tabs.get(position);
//            }
//        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(palytoolRecceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play_play_img:
                Intent intent = new Intent();
                intent.setAction(MusicPalyerService.MUSIC_PLAYORPAUST);
                getActivity().sendBroadcast(intent);
                break;
            case R.id.play_playnext_img:
                intent = new Intent();
                intent.setAction(MusicPalyerService.MUSIC_NEXT);
                getActivity().sendBroadcast(intent);
                break;
            case R.id.play_music_list_meun:
                break;
        }
    }
}
