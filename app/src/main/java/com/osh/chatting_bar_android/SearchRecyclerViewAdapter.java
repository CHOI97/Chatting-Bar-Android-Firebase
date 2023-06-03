package com.osh.chatting_bar_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder>{
    private List<String> searchList;
    private Context context;
    public SearchRecyclerViewAdapter(Context context, List<String> searchList) {

        this.searchList = searchList;
        this.context = context;
    }
    @NonNull
    @Override
    public SearchRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recent_search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(searchList.get(position));
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recent_text);

            LinearLayout recentBtn = itemView.findViewById(R.id.recent_button);
            recentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.mainActivity.finish();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("search", textView.getText().toString());

                    ((SearchActivity)v.getContext()).updateSearchList(textView.getText().toString());

                    v.getContext().startActivity(intent);

                   ((Activity)v.getContext()).finish();
                }
            });
        }
    }
}
