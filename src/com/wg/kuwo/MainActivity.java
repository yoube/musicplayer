package com.wg.kuwo;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.wg.kuwo.view.MyFragment;
import com.wg.kuwo.view.QukuFragment;
import com.wg.kuwo.view.ShipingFragment;
import com.wg.kuwo.view.TuijianFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_fragment_viewpager);

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new MyFragment());
        fragmentList.add(new TuijianFragment());
        fragmentList.add(new QukuFragment());
        fragmentList.add(new ShipingFragment());
        //…Ë÷√  ≈‰∆˜
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

        });
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            // µœ÷homeº¸
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
