package com.wg.kuwo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.wg.kuwo.R;
import com.wg.kuwo.adapter.MusicListAdapter;
import com.wg.kuwo.bean.MusicBean;
import com.wg.kuwo.control.ListServer;
import com.wg.kuwo.service.MusicPalyerService;
import com.wg.kuwo.util.StorageTool;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by EXP on 2015/7/24.
 */
public class MyFragment extends Fragment {
    private ListView mMyMusic, mMyList;
    private MusicBean mCurredMusic;
//    private
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_my_layout, null);
        mMyMusic = (ListView) view.findViewById(R.id.my_mymsic_listview);
        mMyList = (ListView) view.findViewById(R.id.my_mylist_listview);
        MusicListAdapter myListAdapter = new MusicListAdapter(getActivity());
        myListAdapter.setData(ListServer.getInstens(getActivity()).getMyMusicList());

        //设置服务数据列表
        final ArrayList<MusicBean> list = (ArrayList<MusicBean>) ListServer.getInstens(getActivity()).getMusicList();
        Intent intent  = new Intent(getActivity(),MusicPalyerService.class);
        intent.putParcelableArrayListExtra("MUSIC_LIST",list);
        getActivity().startService(intent);

        mMyMusic.setAdapter(myListAdapter);
        mMyMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(getActivity(),LocalMusicActivity.class);
                    Intent activityIntent = getActivity().getIntent();
                    intent.putExtra("STATUS",activityIntent.getBooleanExtra("STATUS",false));
                    intent.putExtra("MUSIC",(MusicBean)activityIntent.getParcelableExtra("MUSIC"));
                    intent.putParcelableArrayListExtra("MUSIC_LIST",list);
                    startActivity(intent);
                }
            }
        });
//        mMyList.setAdapter(null);


        return view;
    }
}
