package com.osh.chatting_bar_android;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.osh.chatting_bar_android.data_model.BaseResponse;
import com.osh.chatting_bar_android.data_model.Categories;
import com.osh.chatting_bar_android.data_model.ChatRoomRequest;
import com.osh.chatting_bar_android.data_model.ChatRoomResponse;
import com.osh.chatting_bar_android.data_model.CreateRoomResponse;
import com.osh.chatting_bar_android.firebase.DatabaseManager;
import com.osh.chatting_bar_android.firebase.data.ChatRoom;
import com.osh.chatting_bar_android.firebase.data.ChatRoomData;
import com.osh.chatting_bar_android.firebase.data.Guest;
import com.osh.chatting_bar_android.firebase.data.Message;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoomActivity extends AppCompatActivity {
    SharedPreferences pref;
    private String startTime;
    private int startH, startM;
    private String durationtime;
    private EditTagPopupDialog editTagPopupDialog;

    public DatabaseManager db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        db = new DatabaseManager();
        pref = User.getInstance().getPreferences();
        InitBtn();
    }


    protected void InitBtn(){
        //시작 시간 설정
        TextView start_time = findViewById(R.id.start_rect);
        start_time.setClickable(true);
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calenderInstance = Calendar.getInstance();
                int hr = calenderInstance.get(Calendar.HOUR_OF_DAY);
                int min = calenderInstance.get(Calendar.MINUTE);
                TimePickerDialog.OnTimeSetListener onTimeListner = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (view.isShown()) {
                            calenderInstance.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calenderInstance.set(Calendar.MINUTE, minute);
                            start_time.setText(hourOfDay + " : "+ minute);
                            startH = hourOfDay;
                            startM = minute;
                            startTime = hourOfDay + ":" + minute;
                        }
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateRoomActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        onTimeListner, hr, min, true);
                timePickerDialog.setTitle("시작시간");
                Objects.requireNonNull(timePickerDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
                timePickerDialog.show();
            }
        });

//운영 시간 설정
        TextView operating_time = findViewById(R.id.end_rect);
        operating_time.setClickable(true);
        operating_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calenderInstance = Calendar.getInstance();
                int hr = calenderInstance.get(Calendar.HOUR_OF_DAY);
                int min = calenderInstance.get(Calendar.MINUTE);
                TimePickerDialog.OnTimeSetListener onTimeListner = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (view.isShown()) {
                            calenderInstance.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calenderInstance.set(Calendar.MINUTE, minute);
                            operating_time.setText(hourOfDay + " : "+ minute);
                            int durationH = startH + hourOfDay;
                            int durationM = startM + minute;
                            durationtime = durationH + ":" + durationM;
                        }
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateRoomActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        onTimeListner, hr, min, true);
                timePickerDialog.setTitle("운영시간");
                Objects.requireNonNull(timePickerDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
                timePickerDialog.show();
            }
        });

        //태그 설정
        TextView tag_btn = findViewById(R.id.tagSet_rect);
        tag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTagPopupDialog = new EditTagPopupDialog(CreateRoomActivity.this);

                //아래 두 줄 라운드 외곽
                editTagPopupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                editTagPopupDialog.show();

            }
        });

        //종료 버튼
        Button back = findViewById(R.id.Exit_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
//        취소버튼
        Button not_create = findViewById(R.id.notCreate_button);
        not_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

        Button create = findViewById(R.id.Create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "방생성: "+pref.getString("AccessToken", ""));
                Set<Categories> tempSet = new HashSet<>();
                tempSet.add(Categories.DANCE);
                tempSet.add(Categories.GAME);
                tempSet.add(Categories.SPORT);
                EditText name = findViewById(R.id.title_input);
                Spinner party_spinner = findViewById(R.id.party_spinner);
                Switch PWSwitch = findViewById(R.id.PW_switch);
                EditText PW = findViewById(R.id.PW_input);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String now_ = formatter.format(new Date());
                formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm");
                ChatRoomRequest chatRoomRequest = null;
                try {
                    chatRoomRequest = new ChatRoomRequest(name.getText().toString(), name.getText().toString(), tempSet,
                            formatter.parse(now_ + startTime), formatter.parse(now_ + durationtime)
                            , Integer.parseInt(party_spinner.getSelectedItem().toString()), PWSwitch.isChecked(), PW.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Call<CreateRoomResponse> call = RetrofitService.getApiTokenService().createRoom(chatRoomRequest);
                call.enqueue(new Callback<CreateRoomResponse>() {
                    @Override
                    public void onResponse(Call<CreateRoomResponse> call, Response<CreateRoomResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", response.body().toString() +", code: "+ response.code());
                            ChatRoomData chatRoomData = new ChatRoomData(User.getInstance().getId(), name.getText().toString());
                            ArrayList<Guest> guest = new ArrayList<Guest>();
                            ArrayList<Message> message = new ArrayList<Message>();
                            ChatRoom chatRoom = new ChatRoom(message,chatRoomData,guest);
                            db.createRoom(response.body().getInformation().getId(),chatRoom);
                            Log.d("Firebase","id : "+response.body().getInformation().getId());
                            Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                            intent.putExtra("RoomID",response.body().getInformation().getId());

                            startActivity(intent);
                            finish();
                        } else {
                            try {
                                Log.d("test", "방생성 실패" + response.errorBody().string() + ", code: "+ response.code());
                            } catch (IOException e) {
                                Log.d("test", e.toString() + "!");
                            }
                            Toast.makeText(getApplicationContext(), "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateRoomResponse> call, Throwable t) {
                        Log.d("test", "실패: "+ t.getMessage());
                        Toast.makeText(getApplicationContext(), "네트워크 문제로 방생성에 실패했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
