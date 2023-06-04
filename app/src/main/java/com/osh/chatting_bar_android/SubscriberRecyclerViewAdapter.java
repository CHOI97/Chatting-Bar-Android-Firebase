package com.osh.chatting_bar_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.FollowInformation;
import com.osh.chatting_bar_android.data_model.FollowingResponse;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriberRecyclerViewAdapter extends RecyclerView.Adapter<SubscriberRecyclerViewAdapter.MyViewHolder>{
    private List<FollowInformation> subscriberList;
    private Context context;
    public SubscriberRecyclerViewAdapter(Context context, List<FollowInformation> subscriberList) {

        this.subscriberList = subscriberList;
        this.context = context;
    }
    @NonNull
    @Override
    public SubscriberRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_subscriber_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriberRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(subscriberList.get(position).getNickname());
        Call<FollowingResponse> call = RetrofitService.getApiTokenService().getFollowerByID(subscriberList.get(position).getId().toString());
        call.enqueue(new Callback<FollowingResponse>() {
            //콜백 받는 부분
            @Override
            public void onResponse(Call<FollowingResponse> call, Response<FollowingResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("test", response.body().toString() + ", code: " + response.code());
                    holder.count.setText(response.body().getInformation().size() + "명");
                } else {
                    try {
                        Log.d("test", response.errorBody().string() + ", code: " + response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FollowingResponse> call, Throwable t) {
                Log.d("test", "실패: " + t.getMessage());

                Toast.makeText(context, "네트워크 문제로 방 조회에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
        //아이템 클릭 이벤트 처리 구독 취소 -> 버튼이 취소됨으로 바뀜 다시 누르면 구독
        int index = position;
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.btn.getText().toString().equals("취소")) {
                    Call<BaseResponse> call = RetrofitService.getApiTokenService().followEnd(subscriberList.get(index).getId().toString());
                    call.enqueue(new Callback<BaseResponse>() {
                        //콜백 받는 부분
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d("test", response.body().toString() + ", code: " + response.code());
                                holder.btn.setText("구독");
                                Toast.makeText(context, "구독을 취소했습니다", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    Log.d("test", response.errorBody().string() + ", code: " + response.code());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(context, "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            Log.d("test", "실패: " + t.getMessage());

                            Toast.makeText(context, "네트워크 문제로 방 조회에 실패했습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Call<BaseResponse> call = RetrofitService.getApiTokenService().followStart(subscriberList.get(index).getId().toString());
                    call.enqueue(new Callback<BaseResponse>() {
                        //콜백 받는 부분
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if (response.isSuccessful()) {
                                Log.d("test", response.body().toString() + ", code: " + response.code());
                                holder.btn.setText("취소");
                                Toast.makeText(context, "구독을 했습니다", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    Log.d("test", response.errorBody().string() + ", code: " + response.code());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(context, "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            Log.d("test", "실패: " + t.getMessage());

                            Toast.makeText(context, "네트워크 문제로 방 조회에 실패했습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subscriberList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView count;
        Button btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.subscriber_nickname_text);
            count = itemView.findViewById(R.id.subscriber_count);
            btn = itemView.findViewById(R.id.imageButton);


        }
    }
}
