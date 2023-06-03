package com.osh.chatting_bar_android;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

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
                dismiss();
            }
        });
    }
    protected void InitTagList(){
        RecyclerView recyclerView = findViewById(R.id.tag_recyclerView);

        EditTagRecyclerViewAdapter = new EditTagRecyclerViewAdapter(context, getTagList());
        recyclerView.setAdapter(EditTagRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
    }
    private List<String> getTagList() {
        return Arrays.asList("home_deco", "volunteering", "백계환", "배종찬", "신초은","배수호", "오시현", "백계환", "배종찬", "신초은","배수호", "오시현", "백계환", "배종찬", "신초은","배수호", "오시현","백계환", "배종찬", "신초은","배수호", "오시현", "백계환", "배종찬", "신초은");

    }
}