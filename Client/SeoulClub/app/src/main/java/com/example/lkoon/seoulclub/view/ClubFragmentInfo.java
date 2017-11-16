package com.example.lkoon.seoulclub.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.RetrofitManager;
import com.example.lkoon.seoulclub.adapter.IntroduceAdapter;
import com.example.lkoon.seoulclub.adapter.MemberListAdapter;
import com.example.lkoon.seoulclub.adapter.MyclubListAdapter;
import com.example.lkoon.seoulclub.interfaces.OnCustomItemClickListener;
import com.example.lkoon.seoulclub.model.Club;
import com.example.lkoon.seoulclub.model.ClubMember;
import com.example.lkoon.seoulclub.model.IdCheckResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lbc on 2017-11-14.
 */

public class ClubFragmentInfo extends Fragment {
    TextView tvItroduce;
    ImageView ivClub;

    RecyclerView clubmemberRecycler;
    LinearLayoutManager linearLayoutManager;
    MemberListAdapter memberListAdapter;
    List<ClubMember> clubMembers;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_club,container,false);
//                ((ClubMainAcitivity)getActivity())
        tvItroduce = (TextView) view.findViewById(R.id.clubTvInfoIntroduce);
        ivClub = (ImageView) view.findViewById(R.id.clubIvInfo);

        Glide.with(getActivity()).load(RetrofitManager.RetrofitUrl.BASE_URL+((ClubMainAcitivity)getActivity()).imageUrl).fitCenter().into(ivClub);


        tvItroduce.setText("소개 : "+((ClubMainAcitivity)getActivity()).introduce);


        clubmemberRecycler = (RecyclerView) view.findViewById(R.id.recyclerMemberInfo);
        linearLayoutManager = new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false);
        clubmemberRecycler.setLayoutManager(linearLayoutManager);
        memberListAdapter = new MemberListAdapter(clubMembers = new ArrayList<>());


        clubmemberRecycler.setAdapter(memberListAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        RetrofitManager.getInstance().getUrl().memberList(((ClubMainAcitivity)getActivity()).cno).enqueue(new Callback<List<ClubMember>>() {
            @Override
            public void onResponse(Call<List<ClubMember>> call, Response<List<ClubMember>> response) {
                clubMembers.clear();
                clubMembers.addAll(response.body());

                //Log.e("test!",response.body().get(0).getName());
                memberListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ClubMember>> call, Throwable t) {

            }
        });
    }
}
