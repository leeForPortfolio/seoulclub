package com.example.lkoon.seoulclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lkoon.seoulclub.view.ClubFragmentBoard;
import com.example.lkoon.seoulclub.view.ClubFragmentChatting;
import com.example.lkoon.seoulclub.view.ClubFragmentInfo;
import com.example.lkoon.seoulclub.view.ClubFragmentPicture;
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
    private int info;

    public PagerAdapter(FragmentManager fm,int info) {
        super(fm);
        fragments = new Fragment[count];
        this.info =info;
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments[position] != null)
            return fragments[position];
        if (info==1) {
            switch (position) {
                case 0:
                    return fragments[0] = new FragmentIntroduce();
                case 1:
                    return fragments[1] = new FragmentRecomend();
                case 2:
                    return fragments[2] = new FragmentMyClub();
                case 3:
                    return fragments[3] = new FragmentSetting();
            }
        }
        else if (info==2) {
            switch (position) {
                case 0:
                    return fragments[0] = new ClubFragmentInfo();
                case 1:
                    return fragments[1] = new ClubFragmentBoard();
                case 2:
                    return fragments[2] = new ClubFragmentPicture();
                case 3:
                    return fragments[3] = new ClubFragmentChatting();
            }
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
