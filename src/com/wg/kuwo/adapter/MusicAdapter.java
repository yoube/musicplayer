package com.wg.kuwo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wg.kuwo.R;
import com.wg.kuwo.bean.MusicBean;

import java.util.List;
import java.util.Map;

/**
 * Created by EXP on 2015/7/26.
 */
public class MusicAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<MusicBean> data;

    public void setData(List<MusicBean> data){
        this.data = data;
        notifyDataSetChanged();
    }
    public MusicAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        if(data==null) return 0;
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        if(data==null)return 0;
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MusicBean bean = data.get(i);
        Hoder hoder = null;
        if(view ==null){
            hoder = new Hoder();
            view = inflater.inflate(R.layout.view_music_list_item,null);
            hoder.localicon = (TextView) view.findViewById(R.id.music_list_item_local_icon);
            hoder.name = (TextView) view.findViewById(R.id.music_list_item_name);
            hoder.autoer = (TextView) view.findViewById(R.id.music_list_item_autoer);
            hoder.rigthIcon = (ImageView) view.findViewById(R.id.music_list_item_reigthicon);

            view.setTag(hoder);
        }else{
            hoder = (Hoder) view.getTag();
        }
        hoder.name.setText(bean.getName());
        hoder.autoer.setText(bean.getArtist()+" -"+bean.getAlbum());

        return view;
    }
    private class Hoder{
        TextView name;
        TextView localicon;
        TextView autoer;
        ImageView rigthIcon;
    }

}
