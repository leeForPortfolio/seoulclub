package com.example.lkoon.seoulclub.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.adapter.PagerAdapter;

import butterknife.ButterKnife;

/**
 * Created by lbc on 2017-11-14.
 */

public class ClubMainAcitivity extends AppCompatActivity {
    int[] buttonIds = {
            R.id.clubIntroduceButton,
            R.id.clubBoardButton,
            R.id.clubPictureButton,
            R.id.clubChattingButton
    };
    ViewPager viewPager;
    View.OnClickListener buttonsClickListener;

    int cno ;
    String introduce ;
    int currentPeople ;
    String imageUrl;
    String clubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_main);

        viewPager = (ViewPager)findViewById(R.id.club_viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(pagerAdapter);

        buttonsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 0;
                for(int i=0;i<buttonIds.length;i++){
                    if(buttonIds[i] == v.getId()){
                        index = i;
                        break;
                    }
                }

                viewPager.setCurrentItem(index);
            }
        };

        for(int id : buttonIds)
            findViewById(id).setOnClickListener(buttonsClickListener);

        Intent intent = getIntent();
        cno = intent.getExtras().getInt("cno");
        introduce = intent.getExtras().getString("clubIntroduce");
        currentPeople = intent.getExtras().getInt("clubCurrentPeople");
        imageUrl = intent.getExtras().getString("clubImageUrl");
        clubName = intent.getExtras().getString("clubName");
    }
}
