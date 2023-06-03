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
import com.osh.chatting_bar_android.data_model.SignUpRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_up extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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

        Button sign_up = findViewById(R.id.sign_enter);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_input = findViewById(R.id.name_input);
                EditText nickname_input = findViewById(R.id.nickname_input);
                EditText email_input = findViewById(R.id.email_input);
                EditText pw_input = findViewById(R.id.PW_input);
                EditText pw_check_input = findViewById(R.id.PW_check_input);
                if (name_input.getText().toString().isEmpty()
                        || nickname_input.getText().toString().isEmpty()
                        || email_input.getText().toString().isEmpty()
                        || pw_input.getText().toString().isEmpty()
                        || pw_check_input.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"모든 항목을 입력해야 합니다", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email_input.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"올바르지 않은 이메일입니다", Toast.LENGTH_SHORT).show();
                } else if (pw_input.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 너무 짧습니다(최소 6자리)", Toast.LENGTH_SHORT).show();
                }
                else if (pw_input.getText().toString().equals(pw_check_input.getText().toString())) {
                    SignUpRequest signUpRequest = new SignUpRequest(nickname_input.getText().toString(),
                            email_input.getText().toString(), pw_input.getText().toString());
                    Call<BaseResponse> call = RetrofitService.getApiService().sign_up(signUpRequest);
                    call.enqueue(new Callback<BaseResponse>(){
                        //콜백 받는 부분
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d("test", response.body().toString() +", code: "+ response.code());
                                Intent intent = new Intent(getApplicationContext(), Topic_set.class);
                                startActivity(intent);

                                finish();
                            } else if (response.code() == 400)
                                Toast.makeText(getApplicationContext(), "이미 사용하는 이메일입니다", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(), "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            Log.d("test", "실패: "+ t.getMessage());

                            Toast.makeText(getApplicationContext(), "네트워크 문제로 회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(),"재입력한 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //이메일 체크 정규식
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