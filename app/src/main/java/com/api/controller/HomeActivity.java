package com.api.controller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.api.R;
import com.api.dto.TaiKhoanDto;

public class HomeActivity extends AppCompatActivity {
    public static TaiKhoanDto taiKhoanDto = null;

    private LinearLayout llMH, llLS, llTK, llLT;
    private Button btnChon, btnY, btnN;
    private String selectMenu = "";
    private ImageButton imvLogout;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControl();
        setEvent();
    }
    private void setControl(){
        llMH = findViewById(R.id.llMonHoc);
        llLS = findViewById(R.id.llLichSu);
        llTK = findViewById(R.id.llTaiKhoan);
        llLT = findViewById(R.id.llLuyenThi);
        imvLogout = findViewById(R.id.imvLogout);
        btnY = findViewById(R.id.btnYes);
        btnN = findViewById(R.id.btnNo);
        tvName = findViewById(R.id.tvName);

        btnChon = findViewById(R.id.btnChon);
    }
    private void setEvent(){
        //truyền dữ liệu đăng nhập từ LoginActivity qua TaiKhoanActivity
        Bundle bundle = getIntent().getExtras();
        taiKhoanDto = (TaiKhoanDto) bundle.getSerializable("user_login");

        tvName.setText("Hi, " + taiKhoanDto.getTen());

        llMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectMenu = "Môn Học";
                llMH.setBackgroundResource(R.drawable.round_border_green20);

                llLS.setBackgroundResource(R.drawable.round_border_while30);
                llTK.setBackgroundResource(R.drawable.round_border_while30);
                llLT.setBackgroundResource(R.drawable.round_border_while30);
            }
        });
        llLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectMenu = "Lịch Sử Thi";
                llLS.setBackgroundResource(R.drawable.round_border_green20);

                llMH.setBackgroundResource(R.drawable.round_border_while30);
                llTK.setBackgroundResource(R.drawable.round_border_while30);
                llLT.setBackgroundResource(R.drawable.round_border_while30);
            }
        });
        llLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectMenu = "Luyện Thi";
                llLT.setBackgroundResource(R.drawable.round_border_green20);

                llLS.setBackgroundResource(R.drawable.round_border_while30);
                llTK.setBackgroundResource(R.drawable.round_border_while30);
                llMH.setBackgroundResource(R.drawable.round_border_while30);
            }
        });
        llTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectMenu = "Tài Khoản";
                llTK.setBackgroundResource(R.drawable.round_border_green20);

                llLS.setBackgroundResource(R.drawable.round_border_while30);
                llMH.setBackgroundResource(R.drawable.round_border_while30);
                llLT.setBackgroundResource(R.drawable.round_border_while30);
            }
        });

        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectMenu.isEmpty()){
                    Toast.makeText(HomeActivity.this,"Vui lòng chọn Topic", Toast.LENGTH_SHORT).show();
                }
                else if(selectMenu.equals("Môn Học")){
                    Intent intent = new Intent(HomeActivity.this,MonHocActivity.class);
                    intent.putExtra("selectedTopic",selectMenu);
                    startActivity(intent);
                }
                else if(selectMenu.equals("Lịch Sử Thi")){
                    Intent intent = new Intent(HomeActivity.this,LichSuThiActivity.class);
                    intent.putExtra("selectedTopic",selectMenu);
                    startActivity(intent);
                }
                else if(selectMenu.equals("Luyện Thi")){
                    Intent intent = new Intent(HomeActivity.this,LuyenThiActivity.class);
                    intent.putExtra("selectedTopic",selectMenu);
                    startActivity(intent);
                }
                else if(selectMenu.equals("Tài Khoản")){
                    //Lấy dữ liệu tiếp tục truyền qua TaiKhoanActivity;
                    Intent intent = new Intent(HomeActivity.this,TaiKhoanActivity.class);
                    intent.putExtra("selectedTopic",selectMenu);

                    Bundle send = new Bundle();
                    send.putSerializable("user_login",taiKhoanDto);
                    intent.putExtras(send);
                    startActivity(intent);
                }
            }
        });

        imvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiYN(Gravity.CENTER);

            }
        });
    }

    private void hienThiYN(int gravity) {
        //xử lý vị trí của dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_logout);

        Window window = dialog.getWindow();
        if (window == null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        //click ra bên ngoài để tắt dialog
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(false);
        } else {
            dialog.setCancelable(false);
        }


        btnY = dialog.findViewById(R.id.btnYes);
        btnN = dialog.findViewById(R.id.btnNo);


        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}