package com.api.service;

import com.api.CallAPI;
import com.api.dto.MonHocDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MonHocService {
    CallAPI callApi = new CallAPI();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    MonHocService monHocService = new Retrofit.Builder()
            .baseUrl(callApi.url()) // API base url
            .addConverterFactory(GsonConverterFactory.create(gson)) //Factory phụ thuộc vào format json trả vê
            .build()
            .create(MonHocService.class);

    @GET("monhoc")
    Call<List<MonHocDto>> layDSMonHoc();

    @GET("monhoc/{maMonHoc}")
    Call<MonHocDto> layMonHoc(@Path("maMonHoc") String maMonHoc);

    @POST("monhoc")
    Call<MonHocDto> themMonHoc(@Body MonHocDto monHocDto);

    @PUT("monhoc")
    Call<MonHocDto> suaMonHoc(@Body MonHocDto monHocDto);

    @DELETE("{maMonHoc}")
    Call<Void> xoaMonHoc(@Path("maMonHoc") String maMonHoc);
}
