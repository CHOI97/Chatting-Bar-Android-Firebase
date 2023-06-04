package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.EnumSet;
import java.util.Set;

public class UserInformation {
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

    @SerializedName("profileImg")
    private String profileImg;

    public String getProfileImg() {
        return profileImg;
    }

    @SerializedName("categories")
    private EnumSet<Categories> categories;

    public EnumSet<Categories> getCategories() {
        return categories;
    }


    @Override
    public String toString()
    {
        return "id: " + id + "\nnickname: " + nickname + "\nemail: " + email + "\nprofileImg" + profileImg + "\ncategories" + categories;
    }
}
