package com.example.lkoon.seoulclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.RetrofitManager;
import com.example.lkoon.seoulclub.interfaces.RegionViewCallback;
import com.example.lkoon.seoulclub.model.Club;

/**
 * Created by lkoon on 2017-10-01.
 */

public class IntroduceViewHolder extends RecyclerView.ViewHolder {
    ImageView ivIntroduce;
    TextView tvClubName, tvShortIntroduce, tvCurrenPeopleNumber,tvMaxPeople,tvRegion,tvConcern;

    public IntroduceViewHolder(View itemView, final RegionViewCallback callback)  {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback.getOnCustomItemClickListener() !=null) {
                    int position =getAdapterPosition();
                    callback.getOnCustomItemClickListener().onItemClick(position);
                }
            }
        });


        tvClubName= (TextView)itemView.findViewById(R.id.tvClubName);
        tvShortIntroduce = (TextView)itemView.findViewById(R.id.tvShortIntroduce);
        tvCurrenPeopleNumber = (TextView)itemView.findViewById(R.id.tvCurrenPeopleNumber);
        tvMaxPeople = (TextView)itemView.findViewById(R.id.tvMaxPeople);
        tvRegion = (TextView)itemView.findViewById(R.id.tvRegion);
        tvConcern = (TextView)itemView.findViewById(R.id.tvConcern);
        ivIntroduce = (ImageView)itemView.findViewById(R.id.ivIntroducePicture);
    }

    public void setData(Club club) {
        tvClubName.setText(club.getName());
        tvShortIntroduce.setText(club.getIntroduce());
        tvCurrenPeopleNumber.setText(""+club.getCurrentPeople());
        tvMaxPeople.setText(""+club.getMaxPeople());
        tvRegion.setText(club.getLocation());
        tvConcern.setText(club.getConcern());
        Log.e("url",RetrofitManager.RetrofitUrl.BASE_URL+club.getImgurl());
        Glide.with(itemView.getContext()).load(RetrofitManager.RetrofitUrl.BASE_URL+club.getImgurl()).fitCenter().into(ivIntroduce);
    }
}
