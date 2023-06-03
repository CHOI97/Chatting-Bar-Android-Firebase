package com.osh.chatting_bar_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {
    private static final String FileName = "searchList.txt";
    private SearchRecyclerViewAdapter searchRecyclerViewAdapter;

    private ArrayList<String> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        //검색 기록 불러오기
        searchList = getSearchList();
        ArrayList<String> searchListReverse = (ArrayList<String>)searchList.clone();
        Collections.reverse(searchListReverse);

        EditText editText = findViewById(R.id.editSearch);
        ImageButton search_btn = findViewById(R.id.searching_button);
        Button exit_btn = findViewById(R.id.Exit_button);
        RecyclerView recyclerView = findViewById(R.id.recent_search_view);
        searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(this, searchListReverse);

        recyclerView.setAdapter(searchRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //검색
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();

                if(str.length() == 0){
                    Toast.makeText(getApplicationContext(),"검색어를 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else {
                    updateSearchList(editText.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("search", editText.getText().toString());
                    startActivity(intent);

                    finish();
                }
            }
        });

        //닫기
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

    }

    public void updateSearchList(String str)
    {
        if (searchList.contains(str)) //중복이 있으면 지움
            searchList.remove(str);
        searchList.add(str);
        FileSave(searchList);
    }

    private ArrayList<String> getSearchList()
    {
        ArrayList<String> result;
        try {
            FileInputStream fis = openFileInput(FileName);
            StringBuffer sb = new StringBuffer();
            byte dataBuffer[] = new byte[1024];
            int n = 0 ;

            // -1 파일 끝을 의미
            while((n=fis.read(dataBuffer)) != -1 ){
                sb.append(new String(dataBuffer));
            }
            result = new ArrayList<>(Arrays.asList(sb.toString().split(",")));
            result.remove(result.size() - 1); //맨뒤가 ,로 끝나서 ""이 들어가 지워줌

            fis.close();
        } catch (Exception e) {
            Log.d("test", "파일 에러");
            return new ArrayList<>();
        }
        return result;
    }

    private void FileSave(List<String> strlist)
    {
        try
        {
            FileOutputStream fos = openFileOutput(FileName, Context.MODE_PRIVATE);
            String str = "";
            for (String s: strlist){
                str += s + ",";
            }
            fos.write(str.getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            return;
        }
    }
}
