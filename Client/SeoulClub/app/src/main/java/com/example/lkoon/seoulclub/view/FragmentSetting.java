package com.example.lkoon.seoulclub.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lkoon.seoulclub.R;

/**
 * Created by lkoon on 2017-09-30.
 */

public class FragmentSetting extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4_main,container,false);
    }
}
