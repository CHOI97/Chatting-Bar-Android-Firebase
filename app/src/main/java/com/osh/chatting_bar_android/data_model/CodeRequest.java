package com.osh.chatting_bar_android.data_model;

import java.util.EnumSet;

public class CodeRequest {
    private String email;

    private String code;

    public CodeRequest(String email, String code)
    {
        this.email = email;
        this.code = code;
    }
}
