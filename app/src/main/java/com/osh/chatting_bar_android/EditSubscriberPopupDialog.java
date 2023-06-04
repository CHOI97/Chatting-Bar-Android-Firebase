package com.osh.chatting_bar_android;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.FollowInformation;
import com.osh.chatting_bar_android.data_model.FollowingResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSubscriberPopupDialog extends Dialog {
    private Button shutdownClick;
    private LinearLayout completeClick;
    private SubscriberRecyclerViewAdapter SubscriberRecyclerViewAdapter;
    private List<FollowInformation> subscriberList;
    Context context;
    @SuppressLint("MissingInflatedId")
    public EditSubscriberPopupDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        Call<FollowingResponse> call = RetrofitService.getApiTokenService().getFollowInfo();
        call.enqueue(new Callback<FollowingResponse>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<FollowingResponse> call, Response<FollowingResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("test", response.body().toString() + ", code: " + response.code());
                    subscriberList = response.body().getInformation();
                    InitSubscriberList();
                } else {
                    try {
                        Log.d("test", response.errorBody().string() + ", code: " + response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FollowingResponse> call, Throwable t) {
                Log.d("test", "실패: " + t.getMessage());

                Toast.makeText(context, "네트워크 문제로 구독자 조회에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
        setContentView(R.layout.popup_edit_subscirber);

        shutdownClick = findViewById(R.id.close_button);
        shutdownClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        completeClick = findViewById(R.id.complete_click);
        completeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    protected void InitSubscriberList(){
        RecyclerView recyclerView = findViewById(R.id.subscriber_recyclerView);
        SubscriberRecyclerViewAdapter = new SubscriberRecyclerViewAdapter(context, subscriberList);
        recyclerView.setAdapter(SubscriberRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}