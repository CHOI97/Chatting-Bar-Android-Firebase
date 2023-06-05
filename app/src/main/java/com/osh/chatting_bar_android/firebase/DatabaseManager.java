package com.osh.chatting_bar_android.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseManager {
    private static DatabaseManager dbM = new DatabaseManager();
    private DatabaseReference mDatabase;

    public DatabaseManager(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public static DatabaseManager getInstance(){
        return dbM;
    }


}
