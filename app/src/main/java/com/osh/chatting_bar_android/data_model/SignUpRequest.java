package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
//    {
//        "nickname": "계환",
//            "email": "gyehwan@gmail.com",
//            "password": "1234"
//    }
    private String nickname;
    private String email;
    private String password;

    public SignUpRequest(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
