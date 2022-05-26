package com.api.service;

import com.api.CallAPI;
import com.api.dto.CauHoiDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CauHoiService {
    CallAPI callApi = new CallAPI();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    CauHoiService cauHoiService = new Retrofit.Builder()
            .baseUrl(callApi.url()) // API base url
            .addConverterFactory(GsonConverterFactory.create(gson)) //Factory phụ thuộc vào format json trả vê
            .build()
            .create(CauHoiService.class);
    @GET("cauhoi")
    Call<List<CauHoiDto>> layDSCauHoi();

    @GET("cauhoi/danhsach/{maMonHoc}")
    Call<List<CauHoiDto>> layDSCauHoiTheoMon(@Path("maMonHoc") String maMonHoc, @Query("loai") String loai);

    @GET("cauhoi/{idCH}")
    Call<List<CauHoiDto>> layCauHoi(@Path("idCH") Integer idCH);


}
