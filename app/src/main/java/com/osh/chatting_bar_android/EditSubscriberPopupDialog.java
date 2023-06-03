package com.osh.chatting_bar_android;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class EditSubscriberPopupDialog extends Dialog {
    private Button shutdownClick;
    private LinearLayout completeClick;
    private SubscriberRecyclerViewAdapter SubscriberRecyclerViewAdapter;
    Context context;
    @SuppressLint("MissingInflatedId")
    public EditSubscriberPopupDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.popup_edit_subscirber);
        InitSubscriberList();
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
        SubscriberRecyclerViewAdapter = new SubscriberRecyclerViewAdapter(context, getSubscriberList());
        recyclerView.setAdapter(SubscriberRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
    private List<String> getSubscriberList() {
        return Arrays.asList("배수호", "오시현", "백계환", "배종찬", "신초은");
    }
}