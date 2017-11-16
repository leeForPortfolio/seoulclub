package com.example.lkoon.seoulclub.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //LinearLayout buttonHome, buttonMyClub, buttonRecommend, buttonSetting;
    int[] buttonIds = {
            R.id.homeButton,
            R.id.groupButton,
            R.id.myClubButton,
            R.id.settingButton
    };
    ViewPager viewPager;
    View.OnClickListener searchClickListener;
    View.OnClickListener buttonsClickListener;

    DrawerLayout drawer;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_group_black_24dp);
            //actionBar.setCustomView(R.layout.search_main);
            View v = getLayoutInflater().inflate(R.layout.search_main,null,false);
            editText = (EditText)v.findViewById(R.id.search_main_edittext);
            searchClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
            v.findViewById(R.id.search_main_button).setOnClickListener(searchClickListener);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                    getResources().getDimensionPixelSize(R.dimen.search_main_width),
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            layoutParams.gravity = Gravity.END;
            actionBar.setCustomView(v, layoutParams);
            actionBar.setDisplayShowCustomEnabled(true);
        }


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager)findViewById(R.id.main_viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),1);
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

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

//        buttonHome = (LinearLayout)findViewById(R.id.homeButton);
//        buttonMyClub = (LinearLayout)findViewById(R.id.myClubButton);
//        buttonRecommend = (LinearLayout)findViewById(R.id.groupButton);
//        buttonSetting = (LinearLayout)findViewById(R.id.settingButton);

//        buttonHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.mainFrame,new FragmentIntroduce()).commit();
//            }
//        });
//        buttonMyClub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.mainFrame,new FragmentRecomend()).commit();
//
//            }
//        });
//        buttonRecommend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.mainFrame,new FragmentMyClub()).commit();
//            }
//        });
//        buttonSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.mainFrame,new FragmentSetting()).commit();
//            }
//        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
