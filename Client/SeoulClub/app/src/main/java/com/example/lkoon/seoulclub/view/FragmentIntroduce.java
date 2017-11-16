package com.example.lkoon.seoulclub.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.RetrofitManager;
import com.example.lkoon.seoulclub.adapter.IntroduceAdapter;
import com.example.lkoon.seoulclub.interfaces.OnCustomItemClickListener;
import com.example.lkoon.seoulclub.model.Club;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lkoon on 2017-09-30.
 */

public class FragmentIntroduce extends Fragment {
    FloatingActionButton writeIntroduce;
    private IntroduceAdapter introduceAdapter;
    private LinearLayoutManager linearLayoutManager;
    List<Club> clubInroduce;
    RecyclerView recyclerView;


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

            recyclerView = (RecyclerView) view.findViewById(R.id.introduce_recycler);
            linearLayoutManager = new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            introduceAdapter = new IntroduceAdapter(clubInroduce = new ArrayList<>());

            introduceAdapter.setOnCustomItemClickListener(new OnCustomItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(),clubInroduce.get(position)+"번째 클릭",Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.setAdapter(introduceAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        RetrofitManager.getInstance().getUrl().clubIntroduce().enqueue(new Callback<List<Club>>() {
            @Override
            public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
                clubInroduce.clear();
                clubInroduce.addAll(response.body());
                //Log.e("test!",response.body().get(0).getName());
                introduceAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Club>> call, Throwable t) {
            }
        });
    }
}
