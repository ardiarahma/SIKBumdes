package com.ardiarahma.sik_bumdesa.networks;

import com.ardiarahma.sik_bumdesa.networks.models.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Windows 10 on 9/27/2019.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
