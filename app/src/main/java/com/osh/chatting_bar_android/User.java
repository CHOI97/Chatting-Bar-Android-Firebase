package com.osh.chatting_bar_android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
}
