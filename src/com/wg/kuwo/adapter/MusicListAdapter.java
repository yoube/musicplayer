package com.wg.kuwo.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wg.kuwo.R;

import java.util.List;
import java.util.Map;

/**
 * Created by EXP on 2015/7/26.
 */
public class MusicListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Map<String, Object>> data;

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public MusicListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        if (data == null) return 0;
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        if (data == null) {
            return null;
        }
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Map<String, Object> map = data.get(i);
        Hoder hoder = null;
        if (view == null) {
            hoder = new Hoder();
            view = inflater.inflate(R.layout.view_list_item_rl, null);
            hoder.icon = (ImageView) view.findViewById(R.id.list_icon);
            hoder.name = (TextView) view.findViewById(R.id.list_name);
            hoder.count = (TextView) view.findViewById(R.id.list_count);
            hoder.rigthIcon = (ImageView) view.findViewById(R.id.list_right_icon);
            view.setTag(hoder);
        } else {
            hoder = (Hoder) view.getTag();
        }
        hoder.icon.setImageResource((Integer) map.get("icon"));
        hoder.name.setText(map.get("name").toString());
        hoder.count.setText(map.get("conut").toString());
//        Log.i("map"+i, map.get("reigthicon")+"");
        if (map.get("reigthicon") == null) {
            hoder.rigthIcon.setVisibility(View.GONE);
        } else{
//            Log.i("map"+i,"set");
            hoder.rigthIcon.setImageResource((Integer) map.get("reigthicon"));
            hoder.rigthIcon.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private class Hoder {
        ImageView icon;
        TextView name;
        TextView count;
        ImageView rigthIcon;
    }

}
