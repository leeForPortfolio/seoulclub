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
 * Created by lbc on 2017-10-31.
 */

public class MyclubListAdapter extends RecyclerView.Adapter<MyclubListViewHolder> implements RegionViewCallback {
    List<Club> clubs =new ArrayList<>();

    public MyclubListAdapter(List<Club> clubs) {
        this.clubs = clubs;
    }

    @Override
    public MyclubListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myclub_item, parent, false);
        MyclubListViewHolder myclubListViewHolder = new MyclubListViewHolder(view,this);
        return myclubListViewHolder;
    }

    @Override
    public void onBindViewHolder(MyclubListViewHolder holder, int position) {
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
