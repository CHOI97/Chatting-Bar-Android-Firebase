package com.osh.chatting_bar_android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.annotations.SerializedName;
import com.osh.chatting_bar_android.data_model.Categories;
import com.osh.chatting_bar_android.data_model.UserInformation;

import java.util.EnumSet;

public class User {
    private static User instance;
    public static User getInstance()
    {
        if (instance == null)
        {
            synchronized(User.class)
            {
                instance = new User();
            }
        }
        return instance;
    }
    public static User getInstance(Context context)
    {
        if (instance == null)
        {
            synchronized(User.class)
            {
                instance = new User();
                instance.init(context);
            }
        }
        return instance;
    }

    private void init(Context context_)
    {
        context = context_;
        preferences = context.getSharedPreferences("user", context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    private SharedPreferences preferences;
    private Context context;

    public void setInformation(UserInformation info)
    {
        id = info.getId();
        nickname = info.getNickname();
        email = info.getEmail();
        profileImg = info.getProfileImg();
        categories = info.getCategories();
    }

    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    private String nickname;
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }

    private String email;
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    private String profileImg;
    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
    public String getProfileImg() {
        return profileImg;
    }


    private EnumSet<Categories> categories;
    public void setCategories(EnumSet<Categories> categories) {
        this.categories = categories;
    }
    public EnumSet<Categories> getCategories() {
        return categories;
    }

    public void logout() {
        id = null;
        nickname = null;
        email = null;
        profileImg = null;
        categories = null;
    }
}
