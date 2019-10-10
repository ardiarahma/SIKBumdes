package com.ardiarahma.sik_bumdesa.networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Windows 10 on 10/1/2019.
 */

public class RetrofitClient {

    private  static  final String BASE_URL = "http://10.33.79.218/bumdes-api/public/api/";

    private  static  RetrofitClient mInstance;
    private Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy'T'HH:mm")
            .create();

    private  RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }
}
