package com.example.lkoon.seoulclub.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lkoon.seoulclub.R;

/**
 * Created by lbc on 2017-11-14.
 */

public class ClubFragmentChatting extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4_club,container,false);
    }
}
