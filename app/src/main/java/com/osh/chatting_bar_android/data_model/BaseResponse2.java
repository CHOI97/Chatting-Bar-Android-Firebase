package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse2 {
    @SerializedName("check")
    private boolean check;

    @SerializedName("information")
    private String information;

    public boolean getCheck() {
        return check;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public String toString()
    {
        return "체크 : " + check + ", infomation: " + information.toString();
    }
}
