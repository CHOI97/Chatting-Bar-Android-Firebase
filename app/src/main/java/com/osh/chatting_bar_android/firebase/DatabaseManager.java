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
import com.osh.chatting_bar_android.firebase.data.ChatRoomData;
import com.osh.chatting_bar_android.firebase.data.Message;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DatabaseManager {
    private static DatabaseManager dbM = new DatabaseManager();
    private DatabaseReference mDatabaseRef;
    public DatabaseManager(){
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    }
    public static DatabaseManager getInstance(){
        return dbM;
    }

    public void createRoom(long roomId, ChatRoom chatRoom){
        mDatabaseRef.child("chat").child("roomId").child(Long.toString(roomId)).setValue(chatRoom);
        Log.d("createRoom","roomId : "+ roomId);
    }

    // 방 조회하기
    public void inquiryRoom(long roomId,ChatCallback<ChatRoomData> callback){
        mDatabaseRef.child("chat").child("roomId").child(Long.toString(roomId)).child("chatRoomData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("firebase","DataSnapshot");
                ChatRoomData chatRoomData = snapshot.getValue(ChatRoomData.class);
                Log.d("firebase Datasnapshot",chatRoomData.toString());
                callback.onCallback(chatRoomData);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase",error.toString());
            }
        });
    }
    // 메세지 보내기
    public void sendMessage(long roomId, Message message){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String messageidx = dateFormat.format(date);
//        String messageIndex = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());
        mDatabaseRef.child("chat").child("roomId").child(Long.toString(roomId)).child("chatList").child(messageidx).setValue(message);
    }

}
