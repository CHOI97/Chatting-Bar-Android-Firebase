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

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//본인확인
public class IdentityPW extends AppCompatActivity {

    String email; //인증번호
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_pw);
        
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        InitBtn();
    }

    protected void InitBtn()
    {
        Button back = findViewById(R.id.Exit_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button enter = findViewById(R.id.number_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText number_input = findViewById(R.id.number_input);
                Call<BaseResponse2> call = RetrofitService.getApiService().checkVeriCode(new CodeRequest(email, number_input.getText().toString()));
                call.enqueue(new Callback<BaseResponse2>() {
                    //콜백 받는 부분
                    @Override
                    public void onResponse(Call<BaseResponse2> call, Response<BaseResponse2> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", response.body().toString() + ", code: " + response.code());
                            Intent intent = new Intent(getApplicationContext(), PW_result.class);
                            intent.putExtra("email", email);
                            startActivity(intent);

                            finish();
                        } else {
                            try {
                                Log.d("test", "인증번호 확인"+response.errorBody().string() + ", code: " + response.code());
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
//                Log.d("test","인증번호 체크: "+code.equals(number_input.getText().toString()));
//                if (!code.isEmpty() && code.equals(number_input.getText().toString())) {
//                    Intent intent = new Intent(getApplicationContext(), PW_result.class);
//                    startActivity(intent);
//
//                    finish();
//                }
            }
        });
    }
}