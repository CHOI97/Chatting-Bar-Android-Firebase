package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OneCharRoomResponse {
    @SerializedName("check")
    private boolean check;

    @SerializedName("information")
    private ChatRoomInformation information;

    public boolean getCheck() {
        return check;
    }

    public ChatRoomInformation getInformation() {
        return information;
    }

    @Override
    public String toString()
    {
        return "체크 : " + check + ", infomation: " + information.toString();
    }
}
