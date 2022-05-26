package com.api.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.api.R;
import com.api.dto.TestDto;
import com.api.service.TestService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    private Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        getData = findViewById(R.id.btn_get);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestService.testService.getAllData().enqueue(new Callback<TestDto>() {
                    @Override
                    public void onResponse(Call<TestDto> call, Response<TestDto> response) {
                        Log.e("TestActivity","onReponse: code " + response.code());
                        ArrayList<TestDto.data> data = response.body().getData();
                        for(TestDto.data data1 : data){
                            Log.e("TestActivity","onResponse: email: "+data1.getEmail());

                        }
                    }

                    @Override
                    public void onFailure(Call<TestDto> call, Throwable t) {
                        Log.e("TestActivity","onFailure: "+t.getMessage());
                    }
                });
            }
        });
    }
}