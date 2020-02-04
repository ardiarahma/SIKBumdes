package com.ardiarahma.sik_bumdesa.networks;

import com.ardiarahma.sik_bumdesa.networks.models.Jurnal;
import com.ardiarahma.sik_bumdesa.networks.models.responses.BukuBesarResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DataAkunCreateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DataAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DataAkunUpdateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.DeleteKlasifikasiResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.EkuitasResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.JurnalAnotherCreateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.JurnalCreateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.JurnalResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunCreateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunOneResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.KlasifikasiAkunUpdateResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.LabaRugiResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.LoginResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalAddResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.GetAllAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalKlasifikasiResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalParentResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaAwalResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.NeracaResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.ParentAkunResponse;
import com.ardiarahma.sik_bumdesa.networks.models.responses.RegisterResponse;
import com.google.gson.annotations.Expose;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("klasifikasi-akun")
    Call<KlasifikasiAkunResponse> klasifikasi_akun(
            @Header("Authorization") String token,
            @Header("Accept") String accept,
            @Query("id_parent_akun") int id_parent_akun
    );

    @GET("klasifikasi-akun/detail/{id}")
    Call<KlasifikasiAkunOneResponse> klasifikasi_akun_detail(
            @Header("Authorization") String token,
            @Header("Accept") String accept,
            @Query("id_klasifikasi") int id_klasifikasi
    );

    @FormUrlEncoded
    @POST("klasifikasi-akun/store")
    Call<KlasifikasiAkunCreateResponse> create_klasifikasi_akun(
            @Header("Authorization") String token,
            @Field("id") String code,
            @Field("nama") String name,
            @Field("id_parent_akun") int id_parent_akun
    );

    @FormUrlEncoded
    @PUT("klasifikasi-akun/update/{id}")
    Call<KlasifikasiAkunUpdateResponse> update_klasifikasi_akun(
            @Header("Authorization") String token,
            @Path("id") int code_path,
            @Field("nama") String name,
            @Field("id_parent_akun") int id_parent_akun
    );

    @GET("klasifikasi-akun/delete/{id}")
    Call<DeleteKlasifikasiResponse> delete_klasifikasi(
            @Header("Authorization") String token,
            @Path("id") int id_klas
    );

    @GET("data-akun")
    Call<DataAkunResponse> data_akun(
            @Header("Authorization") String token,
            @Header("Accept") String accept,
            @Query("id_klasifikasi_akun") int id_klasifikasi_akun
    );

    @FormUrlEncoded
    @POST("data-akun/store")
    Call<DataAkunCreateResponse> create_akun(
            @Header("Authorization") String token,
            @Field("id") String code,
            @Field("id_klasifikasi_akun") int id_klasifikasi_akun,
            @Field("nama") String name,
            @Field("posisi_normal") String posisi
    );

    @GET("data-akun/delete/{id}")
    Call<DeleteKlasifikasiResponse> delete_akun(
            @Header("Authorization") String token,
            @Path("id") int id_akun
    );

    @FormUrlEncoded
    @PUT("data-akun/update/{id}")
    Call<DataAkunUpdateResponse> update_akun(
            @Header("Authorization") String token,
            @Path("id") int code_path,
            @Field("id_klasifikasi_akun") int id_klasifikasi_akun,
            @Field("nama") String name,
            @Field("posisi_normal") String posisi
    );

    @GET("neraca-awal/data-akun")
    Call<GetAllAkunResponse> getAllAkun(
            @Header("Authorization") String token
    );

    //===================== Neraca Awal =====================
    @GET("neraca-awal")
    Call<NeracaAwalResponse> neraca_awal(
            @Header("Authorization") String token,
            @Header("Accept") String accept,
            @Query("year") int tahun_param
    );

    @GET("neraca-awal/parent")
    Call<NeracaAwalParentResponse> neracaParent(
            @Header("Authorization") String token,
            @Query("year") int year
    );

    @GET("neraca-awal/klasifikasi_akun")
    Call<NeracaAwalKlasifikasiResponse> neracaKlasifikasi(
            @Header("Authorization") String token,
            @Query("id_parent_akun") int parentId
    );

    @GET("neraca-awal/show_akun")
    Call<NeracaAwalAkunResponse> neracaAkun(
            @Header("Authorization") String token,
            @Query("id_klasifikasi") int klasifikasiId,
            @Query("year") int year
    );

    @FormUrlEncoded
    @POST("neraca-awal/store")
    Call<NeracaAwalAddResponse> neracaAdd(
            @Header("Authorization") String token,
            @Field("id_data_akun") int akunId,
            @Field("tanggal") String date,
            @Field("jumlah") int total
    );

    //===================== Pengaturan User =====================

    //===================== Jurnal =====================

    @FormUrlEncoded
    @POST("jurnal/store")
    Call<JurnalCreateResponse> add_jurnal(
            @Header("Authorization") String token,
            @Field("tanggal") String date,
            @Field("id_data_akun") String akun_id_1,
            @Field("jumlah") int jumlahstr,
            @Field("posisi_normal") String status,
            @Field("no_kwitansi") String kwitansi,
            @Field("keterangan") String ket
    );

    @FormUrlEncoded
    @POST("jurnal/store_jurnal")
    Call<JurnalCreateResponse> add_another_jurnal(
            @Header("Authorization") String token,
            @Field("tanggal") String date,
            @Field("id_data_akun") int akun_id_1,
            @Field("jumlah") int jumlahstr,
            @Field("posisi_normal") String status,
            @Field("id_kwitansi") String kwitansi_id
    );

    @GET("jurnal")
    Call<JurnalResponse> getJurnal(
            @Header("Authorization") String token,
            @Query("tanggal") String date,
            @Query("month") String month,
            @Query("year") String year
    );

    //===================== Buku Besar =====================
    @GET("buku-besar")
    Call<BukuBesarResponse> getBukuBesar(
            @Header("Authorization") String token,
            @Query("month") int month,
            @Query("year") int year,
            @Query("id_data_akun") int account_id
    );

    //===================== Laba Rugi =====================
    @GET("laba-rugi")
    Call<LabaRugiResponse> labaRugi(
            @Header("Authorization") String token,
            @Query("month") int month,
            @Query("year") int year
    );

    //===================== Ekuitas ==========================
    @GET("perubahan-modal")
    Call<EkuitasResponse> ekuitas(
            @Header("Authorization") String token,
            @Query("month") int month,
            @Query("year") int year
    );

    //===================== Neraca ========================
    @GET("neraca")
    Call<NeracaResponse> neraca(
            @Header("Authorization") String token,
            @Query("month") int month,
            @Query("year") int year
    );


}
