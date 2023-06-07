package com.osh.chatting_bar_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osh.chatting_bar_android.data_model.ChatRoomInformation;
import com.osh.chatting_bar_android.data_model.ChatRoomResponse;
import com.osh.chatting_bar_android.data_model.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatLogActivity extends AppCompatActivity {
    private ChatLogRecyclerViewAdapter ChatLogRecyclerViewAdapter;
    private List<ChatRoomInformation> chatList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_log);

        chatList = new ArrayList<>();
        Call<ChatRoomResponse> call = RetrofitService.getApiTokenService().getRoomRecords();
        call.enqueue(new Callback<ChatRoomResponse>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<ChatRoomResponse> call, Response<ChatRoomResponse> response) {
                if (response.isSuccessful()) {
//                    Log.d("test", "최신순\n"+response.body().toString() + ", code: " + response.code());
                    for (ChatRoomInformation info :response.body().getInformation()) {
                        if (info.getStatus() == Status.DELETE)
                            chatList = response.body().getInformation();
                    }
                    InitChatLog();
                } else {
                    try {
                        Log.d("test", "기록"+response.errorBody().string() + ", code: " + response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ChatRoomResponse> call, Throwable t) {
                Log.d("test", "실패: " + t.getMessage());

                Toast.makeText(getApplicationContext(), "네트워크 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
            }
        });

        InitBtn();

    }

    protected void InitChatLog(){
        RecyclerView recyclerView = findViewById(R.id.ChatLog_recyclerView);

        ChatLogRecyclerViewAdapter = new ChatLogRecyclerViewAdapter(this, chatList);
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
}
