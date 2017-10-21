package com.example.lkoon.seoulclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lkoon.seoulclub.view.FragmentIntroduce;
import com.example.lkoon.seoulclub.view.FragmentMyClub;
import com.example.lkoon.seoulclub.view.FragmentRecomend;
import com.example.lkoon.seoulclub.view.FragmentSetting;

/**
 * Created by lkoon on 2017-10-02.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    private final static int count = 4;

    public PagerAdapter(FragmentManager fm){
        super(fm);
        fragments = new Fragment[count];
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments[position] != null)
            return fragments[position];
        switch (position){
            case 0:
                return fragments[0] = new FragmentIntroduce();
            case 1:
                return fragments[1] = new FragmentRecomend();
            case 2:
                return fragments[2] = new FragmentMyClub();
            case 3:
                return fragments[3] = new FragmentSetting();
        }
        return null;
    }

    public Fragment getFragment(int position){
        return getItem(position);
    }

    @Override
    public int getCount() {
        return count;
    }
}
