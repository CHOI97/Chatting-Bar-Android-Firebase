package com.osh.chatting_bar_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatLogRecyclerViewAdapter extends RecyclerView.Adapter<ChatLogRecyclerViewAdapter.MyViewHolder>{

    private List<String> hostList;
    private Context context;
    public ChatLogRecyclerViewAdapter(Context context, List<String>  hostList) {

        this.hostList = hostList;
        this.context = context;
    }
    @NonNull
    @Override
    public ChatLogRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            //열린 경우
            case 0:
                return new ChatLogRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_log_open_item, parent, false));

            //닫힌 경우
            case 1:
                return new ChatLogRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_log_closed_item, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position){
        if(position % 2 == 0){
            return 0;
        }
        else
            return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatLogRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(hostList.get(position));
    }

    @Override
    public int getItemCount() {
        return hostList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ChatLogHost_nickname_text);
        }
    }
}
