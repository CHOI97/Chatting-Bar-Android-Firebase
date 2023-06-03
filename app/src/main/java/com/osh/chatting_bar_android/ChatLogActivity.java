package com.osh.chatting_bar_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ChatLogActivity extends AppCompatActivity {
    private ChatLogRecyclerViewAdapter ChatLogRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_log);

        InitBtn();
        InitChatLog();
    }

    protected void InitChatLog(){
        RecyclerView recyclerView = findViewById(R.id.ChatLog_recyclerView);

        ChatLogRecyclerViewAdapter = new ChatLogRecyclerViewAdapter(this, getLogList());
        recyclerView.setAdapter(ChatLogRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    protected void InitBtn()
    {
        //채팅 기록
        Button chatlist_btn = findViewById(R.id.chattingList_button);
        chatlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatLogActivity.class);
                startActivity(intent);

                finish();
            }
        });
        //홈
        Button home_btn = findViewById(R.id.home_button);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
        //검색
        Button search_btn = findViewById(R.id.search_button);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

                finish();
            }
        });
        //설정
        Button setting_btn = findViewById(R.id.setting_button);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }
    private List<String> getLogList() {
        return Arrays.asList("배수호", "오시현", "백계환", "배종찬", "신초은");

    }
}
