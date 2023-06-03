package com.osh.chatting_bar_android;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osh.chatting_bar_android.data_model.SignInResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "http://www.chatbar.kro.kr:8080";

    public static RetrofitInterface getApiService(){return getInstance().create(RetrofitInterface.class);}

    public static RetrofitInterface getApiTokenService(){return getTokenInstance().create(RetrofitInterface.class);}

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

//        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static Retrofit getTokenInstance(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().header("Content-Type", "application/json").header("Authorization", "Bearer "+User.getInstance().getPreferences().getString("AccessToken", "")).build();
                        Response response = chain.proceed(request);
                        if (response.code() == 401) {
                            response.close();
                            Log.d("test", "재발급 이전\n" + User.getInstance().getPreferences().getString("AccessToken", "") + "\n" +
                                    User.getInstance().getPreferences().getString("RefreshToken", ""));
                            SignInResponse tokenResponse = getApiService().refresh(new stringRequest(User.getInstance().getPreferences().getString("RefreshToken", ""))).execute().body();
                            String token = tokenResponse.getInformation().getAccessToken();
                            String Rtoken = tokenResponse.getInformation().getRefreshToken();
                            //로컬에 저장
                            User.getInstance().getPreferences().edit().putString("AccessToken", token).apply();
                            User.getInstance().getPreferences().edit().putString("RefreshToken", Rtoken).apply();
                            Log.d("test", "재발급 이후\n" + User.getInstance().getPreferences().getString("AccessToken", "") + "\n" +
                                    User.getInstance().getPreferences().getString("RefreshToken", ""));
                            Request newRequest = chain.request().newBuilder().header("Content-Type", "application/json").header("Authorization", "Bearer "+token).build();
                            Response newResponse = chain.proceed(newRequest);
                            Log.d("test", "intercept: " + newResponse.code());
                            return newResponse;
                        }
                        return response;
                    }
                }).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

//        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
