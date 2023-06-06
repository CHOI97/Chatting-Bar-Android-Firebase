package com.osh.chatting_bar_android.firebase.data;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    public ArrayList<Message> chatList;
    public ChatRoomData chatRoomData;
    public ArrayList<Guest> guestList;

    public ChatRoom(ArrayList<Message> chatList, ChatRoomData chatRoomData, ArrayList<Guest> guestList){
        this.chatList = chatList;
        this.chatRoomData = chatRoomData;
        this.guestList = guestList;
    }

    public ArrayList<Message> getChatList() {
        return chatList;
    }

    public void setChatList(ArrayList<Message> chatList) {
        this.chatList = chatList;
    }

    public ChatRoomData getChatRoomData() {
        return chatRoomData;
    }

    public void setChatRoomData(ChatRoomData chatRoomData) {
        this.chatRoomData = chatRoomData;
    }

    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(ArrayList<Guest> guestList) {
        this.guestList = guestList;
    }
}
