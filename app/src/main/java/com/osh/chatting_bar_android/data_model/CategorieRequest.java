package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.EnumSet;
import java.util.Set;

public class CategorieRequest {
    private Set<Categories> categories;

    public CategorieRequest(Set<Categories> categories)
    {
        this.categories = categories;
    }
}
