package com.osh.chatting_bar_android.firebase.data;

public class Message {
    public Message(long uid,String username, long time,String message){
        this.uid = uid;
        this.username = username;
        this.time = time;
        this.message = message;
    }
    public String username;
    public long time;
    public String message;
    public long uid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
