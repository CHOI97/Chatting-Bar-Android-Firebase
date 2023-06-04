package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowingResponse {
    @SerializedName("check")
    private boolean check;

    @SerializedName("information")
    private List<FollowInformation> information;

    public boolean getCheck() {
        return check;
    }

    public List<FollowInformation> getInformation() {
        return information;
    }

    @Override
    public String toString()
    {
        return "체크 : " + check + ", infomation: " + information.toString();
    }
}
