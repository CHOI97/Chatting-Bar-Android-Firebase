package com.osh.chatting_bar_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.osh.chatting_bar_android.data_model.Categories;

import java.util.Arrays;
import java.util.List;

public class Topic_set extends AppCompatActivity {

    private EditTagRecyclerViewAdapter EditTagRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_set);

        InitBtn();
        InitTagList();
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

        Button enter = findViewById(R.id.Topic_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                LoginActivity loginActivity = (LoginActivity)LoginActivity.loginActivity;
                loginActivity.finish();

                finish();
            }
        });
        //다음에 하기
        Button next = findViewById(R.id.Topic_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                LoginActivity loginActivity = (LoginActivity)LoginActivity.loginActivity;
                loginActivity.finish();

                finish();
            }
        });
    }
    protected void InitTagList(){
        RecyclerView recyclerView = findViewById(R.id.topicSet_recyclerView);

        EditTagRecyclerViewAdapter = new EditTagRecyclerViewAdapter(this, Arrays.asList(Categories.values()));
        recyclerView.setAdapter(EditTagRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
    }
}