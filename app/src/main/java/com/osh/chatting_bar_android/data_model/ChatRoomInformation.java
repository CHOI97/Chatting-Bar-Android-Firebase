package com.osh.chatting_bar_android.data_model;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class ChatRoomInformation {
    @SerializedName("id")
    private Long id;

    public Long getId() {
        return id;
    }

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("desc")
    private String desc;

    public String getDesc() {
        return desc;
    }

    @SerializedName("hostId")
    private Long hostId;

    public Long getHostId() {
        return hostId;
    }

    @SerializedName("hostName")
    private String hostName;

    public String getHostName() {
        return hostName;
    }

    @SerializedName("participant")
    private String participant;

    public String getParticipant() {
        return participant;
    }

    @SerializedName("time")
    private String time;

    public String getTime() {
        return time;
    }

    @SerializedName("categories")
    private Set<Categories> categories;

    public Set<Categories> getCategories() {
        return categories;
    }

    @SerializedName("password")
    private String password;

    public String getPassword() {
        return password;
    }

    @SerializedName("status")
    private Status status;

    public Status getStatus() {
        return status;
    }

    @SerializedName("private")
    private Boolean private_;

    public Boolean getPrivate_() {
        return private_;
    }

    @SerializedName("full")
    private Boolean full;

    public Boolean getFull() {
        return full;
    }


    @Override
    public String toString()
    {
        return "id: " + id + "\nname: " + name + "\ndesc: " + desc+ "\nhostName: " + hostName + "\nparticipant" + participant + "\ntime" + time + "\ncategories" + categories+"\npassword" + password
                + "\nprivate: " + private_ + "\n full: " + full + "status: "+ status+ "\n------------------------------------------\n";
    }
}
