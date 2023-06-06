package com.osh.chatting_bar_android.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.osh.chatting_bar_android.firebase.data.ChatRoom;

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

    // 방 조회하기
    public void inquiryRoom(long roomId,ChatCallback<ChatRoom> callback){
        mDatabaseRef.child("chat").child(Long.toString(roomId)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("firebase","DataSnapshot");
                ChatRoom chatroom = snapshot.getValue(ChatRoom.class);
                callback.onCallback(chatroom);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase",error.toString());
            }
        });
    }
    // 메세지 보내기
    public void sendMessage(){

    }

}
