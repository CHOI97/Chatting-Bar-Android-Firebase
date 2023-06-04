package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

public class FollowInformation {
    @SerializedName("id")
    private Long id;

    public Long getId() {
        return id;
    }

    @SerializedName("nickname")
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    @Override
    public String toString()
    {
        return "id : " + id + ", nickname: " + nickname + ", email" + email;
    }
}
