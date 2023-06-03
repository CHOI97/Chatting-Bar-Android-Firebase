package com.osh.chatting_bar_android.data_model;

import java.util.Date;
import java.util.Set;

public class ChatRoomRequest {
//    {
//        "name": "채팅방2",
//        "desc": "채팅방1의 설명입니다.",
//        "categories": ["CAR", "SPORT", "GAME"],
//        "openTime": "2023-05-21T15:00:00",
//        "closeTime": "2023-05-21T18:00:00",
//        "maxParticipant": "16",
//        "isPrivate": "False"
//    }
    private String name;
    private String desc;
    private Set<Categories> categories;
    private Date openTime;
    private Date closeTime;
    private Integer maxParticipant;
    private Boolean isPrivate;
    private String password;

    public ChatRoomRequest(String name, String desc, Set<Categories> categories, Date openTime,
                           Date closeTime, Integer maxParticipant, Boolean isPrivate, String password) {
        this.name = name;
        this.desc = desc;
        this.categories = categories;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.maxParticipant = maxParticipant;
        this.isPrivate = isPrivate;
        this.password = password;
    }
}
