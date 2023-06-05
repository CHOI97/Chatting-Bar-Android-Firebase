package com.osh.chatting_bar_android.firebase.data;

import java.util.List;

public class ChatRoom {
    public List<Message> chatList;
    public ChatRoom chatRoomData;
    public List<Guest> guestList;

    public ChatRoom(List<Message> chatList,ChatRoom chatRoomData,List<Guest> guestList){
        this.chatList = chatList;
        this.chatRoomData = chatRoomData;
        this.guestList = guestList;
    }

    public List<Message> getChatList() {
        return chatList;
    }

    public void setChatList(List<Message> chatList) {
        this.chatList = chatList;
    }

    public ChatRoom getChatRoomData() {
        return chatRoomData;
    }

    public void setChatRoomData(ChatRoom chatRoomData) {
        this.chatRoomData = chatRoomData;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }
}
