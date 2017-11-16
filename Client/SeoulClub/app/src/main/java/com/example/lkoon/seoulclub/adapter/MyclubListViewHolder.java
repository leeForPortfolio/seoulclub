package com.example.lkoon.seoulclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.interfaces.RegionViewCallback;
import com.example.lkoon.seoulclub.model.Club;

import java.util.Locale;

/**
 * Created by lbc on 2017-10-31.
 */

public class MyclubListViewHolder extends RecyclerView.ViewHolder {
    TextView tvClubName, tvCurrentNumber;
    Button btnwWithdraw;

    public MyclubListViewHolder(View itemView, final RegionViewCallback callback) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback.getOnCustomItemClickListener() != null) {
                    int position = getAdapterPosition();
                    callback.getOnCustomItemClickListener().onItemClick(position);
                }
            }
        });
        tvClubName = (TextView) itemView.findViewById(R.id.tvClubName);
        tvCurrentNumber = (TextView) itemView.findViewById(R.id.tvCurrentNumber);
    }

    public void setData(Club club) {
        tvClubName.setText(club.getName());
        tvCurrentNumber.setText(
                String.format(Locale.KOREA,"%d",club.getCurrentPeople())
        );
    }
}
