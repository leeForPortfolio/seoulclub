package com.example.lkoon.seoulclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.model.ClubMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbc on 2017-11-16.
 */

public class MemberListAdapter extends RecyclerView.Adapter<MemberListVIewHolder> {
    List<ClubMember> clubMembers =new ArrayList<>();

    public MemberListAdapter(List<ClubMember> clubMembers) {
        this.clubMembers = clubMembers;
    }

    @Override
    public MemberListVIewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clubmember_item, parent, false);
        MemberListVIewHolder memberListVIewHolder = new MemberListVIewHolder(view);
        return memberListVIewHolder;
    }

    @Override
    public void onBindViewHolder(MemberListVIewHolder holder, int position) {
        holder.setData(clubMembers.get(position));
    }

    @Override
    public int getItemCount() {
        return clubMembers.size();
    }
}
