package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("check")
    private boolean check;

    @SerializedName("information")
    private Information information;

    public boolean getCheck() {
        return check;
    }

    public Information getInformation() {
        return information;
    }

    @Override
    public String toString()
    {
        return "체크 : " + check + ", infomation: " + information.toString();
    }
}

class Information {
    @SerializedName("message")
    private String messeage;

    public String getMesseage() {
        return messeage;
    }

    @Override
    public String toString()
    {
        return messeage;
    }
}
