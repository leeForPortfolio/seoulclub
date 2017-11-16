package com.example.lkoon.seoulclub.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.RetrofitManager;
import com.example.lkoon.seoulclub.adapter.IntroduceAdapter;
import com.example.lkoon.seoulclub.adapter.MyclubListAdapter;
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

public class FragmentMyClub extends Fragment {
    Button btnCreateClub;
    RecyclerView myclubRecyclerview;
    LinearLayoutManager linearLayoutManager;
    MyclubListAdapter myclubListAdapter;
    List<Club> clubInroduce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        try {
            view = inflater.inflate(R.layout.fragment3_main, container, false);

            btnCreateClub = (Button) view.findViewById(R.id.btnCreateClub);
            btnCreateClub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CreateClub.class);
                    startActivity(intent);
                }
            });

            myclubRecyclerview = (RecyclerView) view.findViewById(R.id.myclubRecyclerview);
            linearLayoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
            myclubRecyclerview.setLayoutManager(linearLayoutManager);
            myclubListAdapter = new MyclubListAdapter(clubInroduce = new ArrayList<>());

            myclubListAdapter.setOnCustomItemClickListener(new OnCustomItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), clubInroduce.get(position) + "번째 클릭", Toast.LENGTH_SHORT).show();
                    Log.e("cno값 = ",clubInroduce.get(position).getCno()+"  "+clubInroduce.get(position).getName());
                    Intent intent = new Intent(getActivity(),ClubMainAcitivity.class);
                    intent.putExtra("cno", clubInroduce.get(position).getCno());
                    intent.putExtra("clubIntroduce",clubInroduce.get(position).getIntroduce());
                    intent.putExtra("clubCurrentPeople",clubInroduce.get(position).getCurrentPeople());
                    intent.putExtra("clubImageUrl",clubInroduce.get(position).getImgurl());
                    intent.putExtra("clubName",clubInroduce.get(position).getName());

                    startActivity(intent);

                }
            });
            myclubRecyclerview.setAdapter(myclubListAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RetrofitManager.getInstance().getUrl().myClub().enqueue(new Callback<List<Club>>() {
            @Override
            public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
                clubInroduce.clear();
                clubInroduce.addAll(response.body());

                //Log.e("test!",response.body().get(0).getName());
                myclubListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Club>> call, Throwable t) {

            }
        });
    }
}
