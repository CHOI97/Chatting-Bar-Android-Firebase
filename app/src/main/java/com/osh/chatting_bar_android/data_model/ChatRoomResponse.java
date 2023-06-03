package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatRoomResponse {
    @SerializedName("check")
    private boolean check;

    @SerializedName("information")
    private List<ChatRoomInformation> information;

    public boolean getCheck() {
        return check;
    }

    public List<ChatRoomInformation> getInformation() {
        return information;
    }

    @Override
    public String toString()
    {
        return "체크 : " + check + ", infomation: " + information.toString();
    }
}
