package com.osh.chatting_bar_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.BaseResponse2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import javax.mail.MessagingException;
//import javax.mail.SendFailedException;

public class PW_find extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_find);

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
        //인증
        Button PW = findViewById(R.id.PW_enter);
        PW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_input = findViewById(R.id.name_input);
                EditText email_input = findViewById(R.id.email_input);
                if (name_input.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(),"이름을 잘못 입력했습니다", Toast.LENGTH_SHORT).show();
                } else if (email_input.getText().toString().isEmpty() || !isValidEmail(email_input.getText().toString())){
                    Toast.makeText(getApplicationContext(),"이메일을 잘못 입력했습니다", Toast.LENGTH_SHORT).show();
                } else {
                    PW.setEnabled(false);
                    Call<BaseResponse2> call = RetrofitService.getApiService().requestVeri(new emailRequest(email_input.getText().toString()));
                    call.enqueue(new Callback<BaseResponse2>() {
                        //콜백 받는 부분
                        @Override
                        public void onResponse(Call<BaseResponse2> call, Response<BaseResponse2> response) {
                            if (response.isSuccessful()) {
                                Log.d("test", response.body().toString() + ", code: " + response.code());
                                Intent intent = new Intent(getApplicationContext(), IdentityPW.class);
                                intent.putExtra("email", email_input.getText().toString());
                                startActivity(intent);

                                finish();
                            } else {
                                try {
                                    Log.d("test", "인증번호 보내기"+response.errorBody().string() + ", code: " + response.code());
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
                }
            }
        });
    }

    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }
}