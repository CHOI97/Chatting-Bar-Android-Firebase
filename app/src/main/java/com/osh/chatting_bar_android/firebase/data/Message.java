package com.osh.chatting_bar_android.firebase.data;

public class Message {
    public Message(long uid,String username,String message){
        this.uid = uid;
        this.username = username;
//        this.time = time;
        this.message = message;
//        this.type = type;
    }
    //Firebase 빈 생성자 추가 해야함
    public Message(){}
    public String username;
//    public long time;
    public String message;
    public long uid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public long getTime() {
//        return time;
//    }

//    public void setTime(long time) {
//        this.time = time;
//    }

    public String getMessage() {
        return message;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
