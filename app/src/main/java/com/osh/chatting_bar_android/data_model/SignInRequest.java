package com.osh.chatting_bar_android.data_model;

public class SignInRequest {
//    {
//        "email": "gyehwan@gmail.com",
//            "password": "1234"
//    }
    private String email;
    private String password;

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
