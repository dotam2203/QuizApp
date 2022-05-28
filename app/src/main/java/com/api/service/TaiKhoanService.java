package com.api.service;

import com.api.CallAPI;
import com.api.dto.TaiKhoanDto;
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
import retrofit2.http.Query;

public interface TaiKhoanService {
    CallAPI callApi = new CallAPI();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    TaiKhoanService taiKhoanService = new Retrofit.Builder()
            .baseUrl(callApi.url())
            .addConverterFactory(GsonConverterFactory.create(gson))//.create(gson)
            .build()
            .create(TaiKhoanService.class);

    @GET("taikhoan/danhsach")
    Call<List<TaiKhoanDto>> layDSTaiKhoan();
}
