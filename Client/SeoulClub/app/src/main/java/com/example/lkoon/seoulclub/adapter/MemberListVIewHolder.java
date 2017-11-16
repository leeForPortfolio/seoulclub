package com.example.lkoon.seoulclub.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.model.ClubMember;

/**
 * Created by lbc on 2017-11-16.
 */

public class MemberListVIewHolder extends RecyclerView.ViewHolder {
    TextView tvClubmemberId, tvClubmemberName, tvClubMemberAuthority;
    public MemberListVIewHolder(View itemView) {
        super(itemView);
        tvClubmemberId = (TextView) itemView.findViewById(R.id.tvClubmemberId);
        tvClubmemberName = (TextView) itemView.findViewById(R.id.tvClubmemberName);
        tvClubMemberAuthority = (TextView) itemView.findViewById(R.id.tvClubmemberAuthority);
    }

    public void setData(ClubMember clubMember) {
        tvClubmemberId.setText(clubMember.getId());
        tvClubmemberName.setText(clubMember.getName());
        if (clubMember.getAuthority()==1) {
            tvClubMemberAuthority.setText("운영자");
        }
        else {
            tvClubMemberAuthority.setText("일반회원");
        }
    }

}
