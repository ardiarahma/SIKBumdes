package com.ardiarahma.sik_bumdesa.networks;

import com.ardiarahma.sik_bumdesa.networks.models.responses.LoginResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.ParentAkunResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Windows 10 on 9/27/2019.
 */

public interface ApiService {

    //============= GENERAL ==============
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    //============= NAVIGATION DRAWER =============
    @GET("api/parent-akun")
    Call<ParentAkunResponse> parent_akun(
            @Header("Authorization") String token,
            @Header("Accept") String accept
    );


}
