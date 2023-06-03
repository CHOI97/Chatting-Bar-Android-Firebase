package com.osh.chatting_bar_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//본인확인
public class IdentityPW extends AppCompatActivity {

    String code; //인증번호
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_pw);
        
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        Log.d("test","인증번호 확인: "+code);

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
                Log.d("test","인증번호 체크: "+code.equals(number_input.getText().toString()));
                if (!code.isEmpty() && code.equals(number_input.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), PW_result.class);
                    startActivity(intent);

                    finish();
                }
            }
        });
    }
}