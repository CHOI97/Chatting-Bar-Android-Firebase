package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateRoomResponse {
    @SerializedName("check")
    private boolean check;

    @SerializedName("information")
    private CreateChatRoomInformation information;

//    @SerializedName("id")
//    private long id;


    public boolean getCheck() {
        return check;
    }

    public CreateChatRoomInformation getInformation() {
        return information;
    }

    @Override
    public String toString()
    {
        return "체크 : " + check + ", infomation: " + information.toString();
    }
}
