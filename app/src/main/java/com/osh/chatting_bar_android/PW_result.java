package com.osh.chatting_bar_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.BaseResponse2;
import com.osh.chatting_bar_android.data_model.CodeRequest;
import com.osh.chatting_bar_android.data_model.PWRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PW_result extends AppCompatActivity {

    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_result);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        InitBtn();
    }

    protected void InitBtn()
    {
        //뒤로가기
        Button back = findViewById(R.id.Exit_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //비번 변경
        Button login = findViewById(R.id.login_enter);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pw_input = findViewById(R.id.PW_input);
                EditText pw_check_input = findViewById(R.id.PW_check_input);
                if (pw_input.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"재설정할 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (pw_check_input.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"비밀번호를 재입력하세요", Toast.LENGTH_SHORT).show();
                } else if (pw_input.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(),"비밀번호가 너무 짧습니다(최소 6자리)", Toast.LENGTH_SHORT).show();
                } else if (pw_input.getText().toString().equals(pw_check_input.getText().toString())) {
                    Call<BaseResponse2> call = RetrofitService.getApiService().changePW(new PWRequest(email, pw_input.getText().toString()));
                    call.enqueue(new Callback<BaseResponse2>() {
                        //콜백 받는 부분
                        @Override
                        public void onResponse(Call<BaseResponse2> call, Response<BaseResponse2> response) {
                            if (response.isSuccessful()) {
                                Log.d("test", response.body().toString() + ", code: " + response.code());
                                finish();
                            } else {
                                try {
                                    Log.d("test", "비번 변경"+response.errorBody().string() + ", code: " + response.code());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse2> call, Throwable t) {
                            Log.d("test", "실패: " + t.getMessage());

                            Toast.makeText(getApplicationContext(), "네트워크 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"재입력한 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}