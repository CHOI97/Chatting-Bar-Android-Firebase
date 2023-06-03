package com.osh.chatting_bar_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.ChatRoomInformation;
import com.osh.chatting_bar_android.data_model.OneCharRoomResponse;
import com.osh.chatting_bar_android.data_model.UserResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomActivity extends AppCompatActivity {
    private MessagesRecyclerViewAdapter MessagesRecyclerViewAdapter;

    ChatRoomInformation information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent intent = getIntent();
        if (intent.getLongExtra("RoomID", 0) != 0) {
            Call<OneCharRoomResponse> call = RetrofitService.getApiTokenService().getRoomInfo(intent.getLongExtra("RoomID", 0));
            call.enqueue(new Callback<OneCharRoomResponse>() {
                //콜백 받는 부분
                @Override
                public void onResponse(Call<OneCharRoomResponse> call, Response<OneCharRoomResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("test", response.body().toString() + ", code: " + response.code());
                        information = response.body().getInformation();
                    } else {
                        try {
                            Log.d("test", "방 나가기"+response.errorBody().string() + ", code: " + response.code());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<OneCharRoomResponse> call, Throwable t) {
                    Log.d("test", "실패: " + t.getMessage());

                    Toast.makeText(getApplicationContext(), "네트워크 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            });
        }

        InitBtn();
        InitChatting();
    }

    protected void InitChatting(){
        RecyclerView recyclerView = findViewById(R.id.chatting_recyclerView);

        MessagesRecyclerViewAdapter = new MessagesRecyclerViewAdapter(this, getMessageList());
        recyclerView.setAdapter(MessagesRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    protected void InitBtn(){
        //닫기
        Button exit_button = findViewById(R.id.Exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<BaseResponse> call = RetrofitService.getApiTokenService().roomExit(information.getId());
                call.enqueue(new Callback<BaseResponse>() {
                    //콜백 받는 부분
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", response.body().toString() + ", code: " + response.code());
                            MainActivity.mainActivity.finish();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                            finish();
                        } else {
                            try {
                                Log.d("test", "방 나가기"+response.errorBody().string() + ", code: " + response.code());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.d("test", "실패: " + t.getMessage());

                        Toast.makeText(getApplicationContext(), "네트워크 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //방 제목 변경
        TextView RoomTitle = findViewById(R.id.room_name);
        RoomTitle.setText("test");

        Button alarm_btn = findViewById(R.id.menu_button);
        NavigationView navigationView = findViewById(R.id.roomMenu_drawerLayout);
        navigationView.setVisibility(View.INVISIBLE);

        alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                InitMemberList();
                navigationView.setVisibility(View.VISIBLE);
            }
        });

        //전송버튼
        Button send_button = findViewById(R.id.send_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.chat_input);
                String str = editText.getText().toString();

                if(str.length() == 0){
                    Toast.makeText(getApplicationContext(),"내용을 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else {

                }
            }
        });
    }
    private List<String> getMessageList() {
        return Arrays.asList("테스트 메세지 123", "테스트 메세지 456", "테스트 메세지 789", "장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지", "장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지장문 테스트 메세지");

    }

}
