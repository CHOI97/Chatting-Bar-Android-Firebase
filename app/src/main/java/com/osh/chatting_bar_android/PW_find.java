package com.osh.chatting_bar_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

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
                emailSender emailSender = new emailSender("osh000308@gmail.com", "dndswyvsevefbfqn"); //이메일 생성
                String code = emailSender.getEmailCode();
                if (name_input.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(),"이름을 잘못 입력했습니다", Toast.LENGTH_SHORT).show();
                } else if (email_input.getText().toString().isEmpty() || !isValidEmail(email_input.getText().toString())){
                    Toast.makeText(getApplicationContext(),"이메일을 잘못 입력했습니다", Toast.LENGTH_SHORT).show();
                } else {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                            .permitDiskReads()
                            .permitDiskWrites()
                            .permitNetwork().build());
                    try {
                        PW.setEnabled(false);
                        emailSender.sendMail("채팅마차 인증번호입니다.", code, email_input.getText().toString());
                        Toast.makeText(getApplicationContext(),"인증번호가 전송되었습니다", Toast.LENGTH_SHORT).show();
                    } catch (SendFailedException e) {
                        Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    } catch (MessagingException e) {
                        Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        PW.setEnabled(true);
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplicationContext(), IdentityPW.class);
                    intent.putExtra("code", code);
                    startActivity(intent);

                    finish();
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