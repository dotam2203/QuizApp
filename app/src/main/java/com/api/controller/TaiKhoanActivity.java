package com.api.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.api.R;
import com.api.dto.TaiKhoanDto;

public class TaiKhoanActivity extends AppCompatActivity {
    TaiKhoanDto taiKhoanDto;
    TextView tvUsername, tvPassword, tvEmail, tvName;
    ImageButton imbBackTK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setControl();
        setEvent();
    }

    private void setControl() {
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        tvEmail = findViewById(R.id.tvEmail);
        tvName = findViewById(R.id.tvName);
        imbBackTK = findViewById(R.id.imbBackTK);

        imbBackTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(TaiKhoanActivity.this, HomeActivity.class);
                startActivity(intent);*/
                finish();
            }
        });
    }

    private void setEvent() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            taiKhoanDto = (TaiKhoanDto) bundle.getSerializable("user_login");
            tvUsername.setText(taiKhoanDto.getMaTaiKhoan());
            tvPassword.setText(taiKhoanDto.getMatKhau());
            tvEmail.setText(taiKhoanDto.getEmail());
            tvName.setText(taiKhoanDto.getHo() + " " +taiKhoanDto.getTen());
        }

    }
}
