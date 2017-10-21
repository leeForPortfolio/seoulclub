package com.example.lkoon.seoulclub;

import com.example.lkoon.seoulclub.model.Concern;
import com.example.lkoon.seoulclub.model.Concerns;
import com.example.lkoon.seoulclub.model.IdCheckResult;
import com.example.lkoon.seoulclub.model.Region;
import com.example.lkoon.seoulclub.model.User;
import com.google.gson.Gson;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.Map;

import okhttp3.JavaNetCookieJar;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by jwk on 2017. 9. 9..
 */

public class RetrofitManager {
    private static RetrofitManager instance;
    private RetrofitUrl url;
    private RetrofitManager(){
        final CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        url = retrofit.create(RetrofitUrl.class);
    }
    public static RetrofitManager getInstance(){
        if( instance == null ){
            instance = new RetrofitManager();
        }
        return instance;
    }
    public static void clearInstance(){
        instance = null;
    }

    public RetrofitUrl getUrl(){
        return url;
    }

    public interface RetrofitUrl{
        String BASE_URL = "http://192.168.0.8:8080";


        @GET("local/all")
        Call<Region> region();

        @GET("/user/list/concern")
        Call<List<Concern>> listConcern();

        @GET("/user/list/concern")
        Call<String> listConcern1();

        @POST("/user/idcheck")
        Call<IdCheckResult> idcheck(@Body User user);


        @POST("/user/join")
        @Multipart
//        Call<Map<String,String>> join(@Body User user, @Part MultipartBody.Part img);
        Call<Map<String,String>> join(@Part("id") RequestBody id, @Part("pwd")RequestBody pwd,@Part("name")RequestBody name, @Part("nickName")RequestBody nickName,@Part("birth")RequestBody birth,
                                      @Part("location")RequestBody location,@Part("concern")RequestBody concern, @Part("sex")RequestBody sex, @Part("introduce")RequestBody introduce,
                                      @Part MultipartBody.Part file);



    }
}
