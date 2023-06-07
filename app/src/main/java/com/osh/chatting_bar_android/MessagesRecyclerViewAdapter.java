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

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Message> messageList;
    private Context context;
    public MessagesRecyclerViewAdapter(Context context, ArrayList<Message> messageList) {
        this.messageList = messageList;
        this.context = context;
    }
//    @NonNull
//    @Override
//    public MessagesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        switch(viewType){
//            //내 채팅의 경우
//            case 0:
//                return new MessagesRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_message_item, parent, false));
//
//            //상대 채팅의 경우
//            case 1:
//                return new MessagesRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.anyone_message_item, parent, false));
//
//            case 2:
//                return new MessagesRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.system_message_item,parent, false));
//        }
//        return null;
//
//    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType){
            //내 채팅의 경우
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_message_item, parent, false);
                return new MyViewHolder(view);

            //상대 채팅의 경우
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anyone_message_item, parent, false);
                return new AnotherViewHolder(view);

            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.system_message_item, parent, false);
                return new NotifyViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(messageList.get(position).getUid()==User.getInstance().getId()){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.message.setText(messageList.get(position).getMessage());
            myViewHolder.time.setText(messageList.get(position).getTime());

        }
        else if(messageList.get(position).getUid()==0){
            NotifyViewHolder notifyViewHolder = (NotifyViewHolder) holder;
            notifyViewHolder.message.setText(messageList.get(position).getMessage());
        }
        else{
            AnotherViewHolder anotherViewHolder = (AnotherViewHolder) holder;
            anotherViewHolder.message.setText(messageList.get(position).getMessage());
            anotherViewHolder.time.setText(messageList.get(position).getTime());
            anotherViewHolder.name.setText(messageList.get(position).getUsername());
        }

    }

    @Override
    public int getItemViewType(int position){
        if(messageList.get(position).getUid()==User.getInstance().getId()){
            return 0;
        }
        else if(messageList.get(position).getUid()==0){
            return 2;
        }
        else{
            return 1;
        }
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_text);
            time = itemView.findViewById(R.id.textTime_sample1);
        }
    }
    public class AnotherViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView name;
        TextView time;
        public AnotherViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_text);
            name = itemView.findViewById(R.id.anyoneText_sample1);
            time = itemView.findViewById(R.id.textTime_sample1);
        }
    }
    public class NotifyViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        public NotifyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_text);
        }
    }


}
