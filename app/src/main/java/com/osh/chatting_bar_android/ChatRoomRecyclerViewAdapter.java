package com.osh.chatting_bar_android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osh.chatting_bar_android.data_model.ChatRoomInformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatRoomRecyclerViewAdapter extends RecyclerView.Adapter<ChatRoomRecyclerViewAdapter.MyViewHolder>{

    private List<ChatRoomInformation> hostList;
    private Context context;
    private RoomDetailInfoPopupDialog RoomDetailInfoPopupDialog;
    public ChatRoomRecyclerViewAdapter(Context context, List<ChatRoomInformation>  hostList) {

        this.hostList = hostList;
        this.context = context;
    }
    @NonNull
    @Override
    public ChatRoomRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatRoomRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.chatroom_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.host.setText(hostList.get(position).getHostName());
        holder.desc.setText(hostList.get(position).getDesc());
        holder.participant_num.setText(hostList.get(position).getParticipant());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat formatterToString = new SimpleDateFormat("HH:mm");
        String time = "";
        try {
            Date date = formatter.parse(hostList.get(position).getTime().split(" ~ ")[0]);
            time += formatterToString.format(date) + "~";
            date = formatter.parse(hostList.get(position).getTime().split(" ~ ")[1]);
            time += formatterToString.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        2023-06-03T15:00 ~ 2023-06-03T18:00
        holder.time.setText(time);
        if (!hostList.get(position).getPrivate_())
            holder.private_.setVisibility(View.INVISIBLE);

        int index = position; //position을 onClick안에서 사용하면 고정된 position사용한다고 머라함...
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //v.getContext()는 이 아이템의 context임.
                //Toast.makeText(v.getContext(), "클릭 이벤트 입니다. 누른 방은 " + hostList.get(index).getId(), Toast.LENGTH_SHORT).show();
                //여기에 팝업
                RoomDetailInfoPopupDialog = new RoomDetailInfoPopupDialog(context, hostList.get(index));

                //아래 두 줄 라운드 외곽
                RoomDetailInfoPopupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                RoomDetailInfoPopupDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hostList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView host;
        TextView participant_num;
        TextView time;

        ImageView private_;

        LinearLayout btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            host = itemView.findViewById(R.id.hostNickname_text);
            desc = itemView.findViewById(R.id.title_text);
            participant_num = itemView.findViewById(R.id.title_text2);
            time = itemView.findViewById(R.id.operating_time);
            private_ = itemView.findViewById(R.id.imageView2);

            btn = itemView.findViewById(R.id.room_enter);

        }
    }
}
