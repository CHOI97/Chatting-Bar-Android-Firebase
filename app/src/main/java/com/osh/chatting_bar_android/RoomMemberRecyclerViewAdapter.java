package com.osh.chatting_bar_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomMemberRecyclerViewAdapter extends RecyclerView.Adapter<RoomMemberRecyclerViewAdapter.MyViewHolder> {
    private List<String> MemberList;
    private Context context;
    private boolean isHost;

    public RoomMemberRecyclerViewAdapter(Context context, List<String> MemberList, boolean isHost) {
        this.isHost = isHost;
        this.MemberList = MemberList;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomMemberRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_member_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomMemberRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(MemberList.get(position));
    }

    @Override
    public int getItemCount() {
        return MemberList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.User_nickname_text);
            Button ban = itemView.findViewById(R.id.ban_button);
            Button chat_ban = itemView.findViewById(R.id.chatban_button);
            //방장이 아닌 경우 버튼 지우기
            if (!isHost) {
                chat_ban.setVisibility(View.INVISIBLE);
                ban.setVisibility(View.INVISIBLE);
            }

            //방장인 경우
            else {
                ban.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                chat_ban.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }
}
