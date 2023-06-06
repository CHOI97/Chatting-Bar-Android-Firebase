package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

public class CreateChatRoomInformation {
    @SerializedName("id")
    private long id;
    @SerializedName("message")
    private ResponseMessage messeage;

    public long getId(){
        return id;
    }
    public ResponseMessage getMesseage() {
        return messeage;
    }

    @Override
    public String toString()
    {
        return "id : "+id+"message : " + messeage;
    }
}
class ResponseMessage{
    @SerializedName("message")
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

