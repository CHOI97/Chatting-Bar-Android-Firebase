package com.osh.chatting_bar_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.CategorieRequest;
import com.osh.chatting_bar_android.data_model.Categories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Topic_set extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private EditTagRecyclerViewAdapter EditTagRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_set);
        pref = User.getInstance().getPreferences();
        editor = pref.edit();
        InitBtn();
        InitTagList();
    }

    protected void InitBtn()
    {
        Button back = findViewById(R.id.Exit_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //관심주제 설정에서 뒤로나가면 로그인 화면 -> 로그아웃
                Call<BaseResponse> call = RetrofitService.getApiService().sign_out(new stringRequest(pref.getString("RefreshToken", "")));
                call.enqueue(new Callback<BaseResponse>() {
                    //콜백 받는 부분
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", response.body().toString() + ", code: " + response.code());
                            editor.clear();
                            editor.apply();
                            finish();
                        } else {
                            try {
                                Log.d("test", response.errorBody().string() + ", code: " + response.code());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.d("test", "실패: " + t.getMessage());

                        Toast.makeText(getApplicationContext(), "네트워크 문제로 로그아웃에 실패했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button enter = findViewById(R.id.Topic_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<BaseResponse> call = RetrofitService.getApiTokenService().setCategories(EditTagRecyclerViewAdapter.getUserTagList());
                call.enqueue(new Callback<BaseResponse>() {
                    //콜백 받는 부분
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", response.body().toString() + ", code: " + response.code());
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                            LoginActivity loginActivity = (LoginActivity)LoginActivity.loginActivity;
                            loginActivity.finish();

                            finish();
                        } else {
                            try {
                                Log.d("test", "태그 등록하기"+response.errorBody().string() + ", code: " + response.code());
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