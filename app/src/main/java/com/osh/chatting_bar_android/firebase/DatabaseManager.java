package com.osh.chatting_bar_android.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseManager {
    private static DatabaseManager dbM = new DatabaseManager();
    private DatabaseReference mDatabaseRef;
    public DatabaseManager(){
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    }
    public static DatabaseManager getInstance(){
        return dbM;
    }

    public void createRoom(long roomId){
        mDatabaseRef.child("chat").child("roomId").setValue(Long.toString(roomId));
    }

    // 메세지 보내기
    public void sendMessage(){

    }

}
