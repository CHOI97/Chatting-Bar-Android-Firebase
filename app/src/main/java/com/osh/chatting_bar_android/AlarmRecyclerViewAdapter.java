package com.osh.chatting_bar_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.MyViewHolder>{
    private List<String> subscriberList;
    private Context context;
    public AlarmRecyclerViewAdapter(Context context, List<String> subscriberList) {

        this.subscriberList = subscriberList;
        this.context = context;
    }
    @NonNull
    @Override
    public AlarmRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.subscirber_alarm_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(subscriberList.get(position)+" 님이");
    }

    @Override
    public int getItemCount() {
        return subscriberList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.AlarmHost_nickname_text);
//            아이템 클릭 이벤트 처리
//            LinearLayout recentBtn = itemView.findViewById(R.id.recent_button);
//            recentBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MainActivity.mainActivity.finish();
//                    Intent intent = new Intent(v.getContext(), MainActivity.class);
//                    intent.putExtra("search", textView.getText().toString());
//
//                    ((SearchActivity)v.getContext()).updateSearchList(textView.getText().toString());
//
//                    v.getContext().startActivity(intent);
//
//                   ((Activity)v.getContext()).finish();
//                }
//            });
        }
    }
}
