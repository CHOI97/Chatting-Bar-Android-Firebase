package com.osh.chatting_bar_android.firebase.data;

public class Guest {
    public long uid;
    public String userName;
    public Boolean status;

    public Guest(long uid,String userName,Boolean status){
        this.uid = uid;
        this.userName = userName;
        this.status = status;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
