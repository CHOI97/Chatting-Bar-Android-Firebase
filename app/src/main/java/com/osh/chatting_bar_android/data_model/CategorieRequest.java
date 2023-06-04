package com.osh.chatting_bar_android.data_model;

import java.util.EnumSet;
import java.util.Set;

public class CategorieRequest {
    private EnumSet<Categories> categories;

    public CategorieRequest(EnumSet<Categories> categories)
    {
        this.categories = categories;
    }
}
