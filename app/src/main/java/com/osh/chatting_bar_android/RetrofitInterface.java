package com.osh.chatting_bar_android;

import com.google.gson.annotations.SerializedName;
import com.osh.chatting_bar_android.data_model.*;

import java.util.EnumSet;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @Headers("Content-Type: application/json")
    @POST("/auth/sign-up")
    Call<BaseResponse> sign_up(@Body SignUpRequest signUpRequest);

    @Headers("Content-Type: application/json")
    @POST("/auth/sign-in")
    Call<SignInResponse> sign_in(@Body SignInRequest signInRequest);

//    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
//    @POST("/auth/sign-out")
//    Call<BaseResponse> sign_out(@Field("refreshToken") String token);
    @POST("/auth/sign-out")
    Call<BaseResponse> sign_out(@Body refreshTokenRequest refreshToken);

    @POST("/api/chatroom")
    Call<CreateRoomResponse> createRoom(@Body ChatRoomRequest chatRoomRequest);

//    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
//    @POST("/auth/refresh")
//    Call<SignInResponse> refresh(@Field("refreshToken") String token); //->@Field가 안돌아가유...
    @Headers("Content-Type: application/json")
    @POST("/auth/refresh")
    Call<SignInResponse> refresh(@Body refreshTokenRequest refreshToken);

    @GET("/api/users")
    Call<UserResponse> getUserInfo();

    @POST("/api/chatroom/enter")
    Call<BaseResponse> roomEnter(@Body LongRequest id);

    @GET("/api/chatroom/{roomId}")
    Call<OneCharRoomResponse> getRoomInfo(@Path("roomId") Long roomId);

    @PATCH("/api/chatroom/{roomId}")
    Call<BaseResponse> roomExit(@Path("roomId") Long roomId);

    @DELETE("/api/chatroom?roomId=&userId=")
    Call<BaseResponse> roomKick(@Query("roomId") Long roomId, @Query("userId") int userId);

    @PATCH("/api/chatroom?roomId=&userId=")
    Call<BaseResponse> roomIce(@Query("roomId") Long roomId, @Query("userId") int userId);

    @GET("/api/chatroom")
    Call<ChatRoomResponse> getAllRoom();

    @GET("/api/chatroom/latest")
    Call<ChatRoomResponse> getLatestRoom();

    @GET("/api/chatroom/recommend")
    Call<ChatRoomResponse> getRecommendRoom();

    @GET("/api/chatroom/follow")
    Call<ChatRoomResponse> getFollowRoom();

    @GET("/api/chatroom/search/{search}")
    Call<SearchResponse> getSearchRoom(@Path("search") String search);

    @POST("api/users/follow/{id}")
    Call<BaseResponse> followStart(@Path("id") String id);

    @PATCH("api/users/follow/{id}")
    Call<BaseResponse> followEnd(@Path("id") String id);

    @GET("api/users/following")
    Call<FollowingResponse> getFollowInfo();

    @GET("api/users/following/{id}")
    Call<FollowingResponse> getFollowerByID(@Path("id") String id);

//    @FormUrlEncoded
//    @PATCH("/api/users/categories")
//    Call<BaseResponse> setCategories(@Field(value = "Categories", encoded = true) String categories);

    //    @FormUrlEncoded
//    @PATCH("/api/users/categories")
//    Call<BaseResponse> setCategories(@Field("Categories")EnumSet<Categories> categories);
    @PATCH("/api/users/categories")
    Call<BaseResponse2> setCategories(@Body EnumSet<Categories> Categories);

//    @FormUrlEncoded
//    @POST("/api/users/requestVeri")
//    Call<BaseResponse> requestVeri(@Field("email") String email);
    @Headers("Content-Type: application/json")
    @POST("/auth/requestVeri")
    Call<BaseResponse2> requestVeri(@Body emailRequest stringRequest);

    @POST("/auth/verifyCode")
    Call<BaseResponse2> checkVeriCode(@Body CodeRequest codeRequest);

    @POST("/auth/changePassword")
    Call<BaseResponse2> changePW(@Body PWRequest pwRequest);

    @GET("/api/chatroom/records")
    Call<ChatRoomResponse> getRoomRecords();
}

//왜인지 @Field 안돌아가서 다 @Body로 하기위함....string은 사소해서 여기에 클래스 선언함
class refreshTokenRequest {
    private String refreshToken;

    public refreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

class emailRequest {
    private final String email;

    public emailRequest(String email) {
        this.email = email;
    }
}

//왜인지 @Field 안돌아가서 다 @Body로 하기위함....string은 사소해서 여기에 클래스 선언함
class LongRequest {
    private Long id;

    public LongRequest(Long id) {
        this.id = id;
    }
}
