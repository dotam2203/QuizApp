package com.api.service;

import com.api.CallAPI;
import com.api.dto.ChiTietThiDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChiTietThiService {
    CallAPI callApi = new CallAPI();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ChiTietThiService chiTietThiService = new Retrofit.Builder()
            .baseUrl(callApi.url()) // API base url
            .addConverterFactory(GsonConverterFactory.create(gson)) //Factory phụ thuộc vào format json trả vê
            .build()
            .create(ChiTietThiService.class);

    @GET("chitietthi")
    Call<List<ChiTietThiDto>> layDSChiTietThi();

    @GET("chitietthi/{maMonHoc}")
    Call<List<ChiTietThiDto>> layDSChiTietThiTheoMon(@Path("maMonHoc") String maMonHoc);

    @GET("chitietthi/danhsach/{maTaiKhoan}")
    Call<List<ChiTietThiDto>> layDSChiTietThiTheoTaiKhoan(@Path("maTaiKhoan") String maTaiKhoan);

    @POST("chitietthi")
    Call<ChiTietThiDto> themChiTietThi(@Body ChiTietThiDto chiTietThiDto);
}
