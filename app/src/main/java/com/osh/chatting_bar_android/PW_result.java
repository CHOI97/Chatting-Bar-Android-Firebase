package com.osh.chatting_bar_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PW_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_result);

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
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"재입력한 비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}