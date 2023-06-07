package com.osh.chatting_bar_android;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.BaseResponse2;
import com.osh.chatting_bar_android.data_model.CategorieRequest;
import com.osh.chatting_bar_android.data_model.Categories;
import com.osh.chatting_bar_android.data_model.UserResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTagPopupDialog extends Dialog {
    private Button shutdownClick;
    private LinearLayout completeClick;
    private EditTagRecyclerViewAdapter EditTagRecyclerViewAdapter;
    private Context context;

    @SuppressLint("MissingInflatedId")
    public EditTagPopupDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.popup_edit_tag);

        InitTagList();
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

                Call<BaseResponse2> call = RetrofitService.getApiTokenService().setCategories(EditTagRecyclerViewAdapter.getUserTagList());
                call.enqueue(new Callback<BaseResponse2>() {
                    //콜백 받는 부분
                    @Override
                    public void onResponse(Call<BaseResponse2> call, Response<BaseResponse2> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", response.body().toString() + ", code: " + response.code());
                            User.getInstance().setCategories(EditTagRecyclerViewAdapter.getUserTagList());
                        } else {
                            try {
                                Log.d("test", "태그 등록하기"+response.errorBody().string() + ", code: " + response.code());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(context, "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse2> call, Throwable t) {
                        Log.d("test", "실패: " + t.getMessage());

                        Toast.makeText(context, "네트워크 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                dismiss();
            }
        });
    }
    protected void InitTagList(){
        RecyclerView recyclerView = findViewById(R.id.tag_recyclerView);

        EditTagRecyclerViewAdapter = new EditTagRecyclerViewAdapter(context, Arrays.asList(Categories.values()));
        recyclerView.setAdapter(EditTagRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
    }
}