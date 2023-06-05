package com.osh.chatting_bar_android.firebase.data;

public class ChatRoomData {
    public long masterId;
    public String title;
    public ChatRoomData(long masterId,String title){
        this.masterId = masterId;
        this.title = title;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
