package com.ardiarahma.sik_bumdesa.networks;

import com.ardiarahma.sik_bumdesa.networks.models.responses.LoginResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.ParentAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("parent-akun")
    Call<ParentAkunResponse> parent_akun(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> register(
            @Field("nama") String name,
            @Field("alamat") String address,
            @Field("no_telepon") String phone,
            @Field("email") String email,
            @Field("password") String password
    );
}