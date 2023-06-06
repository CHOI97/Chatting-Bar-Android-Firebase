package com.osh.chatting_bar_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osh.chatting_bar_android.firebase.data.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.MyViewHolder>{

    private ArrayList<Message> messageList;
    private Context context;
    public MessagesRecyclerViewAdapter(Context context, ArrayList<Message> messageList) {
        this.messageList = messageList;
        this.context = context;
    }
    @NonNull
    @Override
    public MessagesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            //내 채팅의 경우
            case 0:
                return new MessagesRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_message_item, parent, false));

            //상대 채팅의 경우
            case 1:
                return new MessagesRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.anyone_message_item, parent, false));

            case 2:
                return new MessagesRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.system_message_item,parent, false));
        }
        return null;

    }

    @Override
    public int getItemViewType(int position){
        int viewtype = position % 3;
        if(viewtype == 2){
            return 0;
        }
        else if(viewtype == 1) {
            return 1;
        }
        else{
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(messageList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.message_text);
        }
    }
}
