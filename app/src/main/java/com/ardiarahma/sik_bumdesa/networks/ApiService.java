package com.ardiarahma.sik_bumdesa.networks;

import com.ardiarahma.sik_bumdesa.networks.models.responses.LoginResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.ParentAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //===================== General =====================
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
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

    //===================== Data Akun =====================
    @GET("parent-akun")
    Call<ParentAkunResponse> parent_akun(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );

    //===================== Neraca Awal =====================
    @GET("neraca-awal")
    Call<NeracaAwalResponse> neraca_awal(
            @Header("Authorization") String token,
            @Header("Accept") String accept,
            @Query("month") int bulan,
            @Query("year") int tahun
    );

    //===================== Pengaturan User =====================

    //===================== Jurnal =====================

    //===================== Buku Besar =====================
}
