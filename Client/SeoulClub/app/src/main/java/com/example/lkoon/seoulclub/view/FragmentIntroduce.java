package com.example.lkoon.seoulclub.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lkoon.seoulclub.R;

/**
 * Created by lkoon on 2017-09-30.
 */

public class FragmentIntroduce extends Fragment {
    FloatingActionButton writeIntroduce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        try {
            view = inflater.inflate(R.layout.fragment1_main, container, false);

            writeIntroduce = (FloatingActionButton) view.findViewById(R.id.btnWrite);
            writeIntroduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WriteIntroduceActivity.class);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;

    }
}
