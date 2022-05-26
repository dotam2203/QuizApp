package com.api.service;

import android.widget.Button;

import com.api.dto.TestDto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface TestService {
    String url = "https://reqres.in/api/";
    TestService testService = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TestService.class);
    @GET("users?page=2")
    Call<TestDto> getAllData();
}
