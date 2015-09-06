package com.wg.kuwo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wg.kuwo.R;

/**
 * Created by EXP on 2015/7/24.
 */
public class TuijianFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_tuijian_layout,null);
        
        return view;
    }
}
