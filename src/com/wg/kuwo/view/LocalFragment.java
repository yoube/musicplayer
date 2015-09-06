package com.wg.kuwo.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.wg.kuwo.R;
import com.wg.kuwo.adapter.MusicAdapter;
import com.wg.kuwo.bean.MusicBean;
import com.wg.kuwo.control.ListServer;
import com.wg.kuwo.service.MusicPalyerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EXP on 2015/7/28.
 */
public class LocalFragment  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_localmusic_music_layout,null);
        ListView listVied = (ListView) view;
        MusicAdapter adpater = new MusicAdapter(getActivity());

        final ArrayList<MusicBean> list = (ArrayList<MusicBean>) ListServer.getInstens(getActivity()).getMusicList();
        adpater.setData(list);
        listVied.setAdapter(adpater);


        listVied.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MusicBean bean = (MusicBean) adapterView.getAdapter().getItem(i);
                Intent intent  = new Intent();
                intent.setAction(MusicPalyerService.MUSIC_PLAY);
//                intent.putParcelableArrayListExtra()
                intent.putExtra("INDEX", i);
                getActivity().sendBroadcast(intent);
            }
        });
        return view;
    }
}
