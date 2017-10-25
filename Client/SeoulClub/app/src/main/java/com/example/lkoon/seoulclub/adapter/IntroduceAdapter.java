package com.example.lkoon.seoulclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.interfaces.OnCustomItemClickListener;
import com.example.lkoon.seoulclub.interfaces.RegionViewCallback;
import com.example.lkoon.seoulclub.model.Club;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lkoon on 2017-10-01.
 */

public class IntroduceAdapter extends RecyclerView.Adapter<IntroduceViewHolder> implements RegionViewCallback{

    List<Club> clubs= new ArrayList<>();

    public IntroduceAdapter(List<Club> clubs) {
        this.clubs = clubs;
    }

    @Override
    public IntroduceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.introduce_club_item, parent, false);
        IntroduceViewHolder introduceViewHolder = new IntroduceViewHolder(view,this);
        return introduceViewHolder;
    }

    @Override
    public void onBindViewHolder(IntroduceViewHolder holder, int position) {
        holder.setData(clubs.get(position));
    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }

    private OnCustomItemClickListener onCustomItemClickListener;

    public void setOnCustomItemClickListener(OnCustomItemClickListener onCustomItemClickListener) {
        this.onCustomItemClickListener = onCustomItemClickListener;
    }
    @Override
    public OnCustomItemClickListener getOnCustomItemClickListener() {
        return onCustomItemClickListener;
    }
}
