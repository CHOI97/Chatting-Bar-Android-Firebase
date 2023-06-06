package com.osh.chatting_bar_android.data_model;

public class PWRequest {
    private String email;

    private String newPassword;

    public PWRequest(String email, String newPassword)
    {
        this.email = email;
        this.newPassword = newPassword;
    }
}
