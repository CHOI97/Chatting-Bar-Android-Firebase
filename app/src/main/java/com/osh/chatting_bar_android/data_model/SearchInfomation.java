package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchInfomation {
    @SerializedName("searchWord")
    private String searchWord;

    public String getSearchWord() {
        return searchWord;
    }

    @SerializedName("data")
    private List<ChatRoomInformation> information;

    public List<ChatRoomInformation> getInformation() {
        return information;
    }

    @Override
    public String toString()
    {
        return "검색어: " + searchWord + "information: " + information;
    }
}
