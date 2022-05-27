package com.api.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.api.R;
import com.api.dto.CauHoiDto;
import com.api.dto.MonHocDto;
import com.api.service.CauHoiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LuyenThiActivity extends AppCompatActivity{
    private MonHocDto monHocDto = null;
    private List<CauHoiDto> cauHoiDtoList = new ArrayList<>(0);

    Bundle bundle;
    private ProgressBar pbLoad;
    private ImageButton imbBackLT;
    private TextView tvMonThi, tvIdCH, tvCauHoi, tvKetQua, tvDiem;
    private AppCompatButton btnThiLai, btnKetQua, btnHuy, btnThiTiep, btnA, btnB, btnC, btnD, btnNextCH;

    int viTri = 0, tongCH = 10;
    int diem = 0;
    String luaChon = "";
    String dapAn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getControl();
        pbLoad.setVisibility(View.VISIBLE);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            monHocDto = (MonHocDto) bundle.getSerializable("mon_thi");
            tvMonThi.setText(monHocDto.getTenMonHoc());
        }
        CauHoiService.cauHoiService.layDSCauHoiTheoMon(monHocDto.getMaMonHoc(), "a").enqueue(new Callback<List<CauHoiDto>>() {
            @Override
            public void onResponse(Call<List<CauHoiDto>> call, Response<List<CauHoiDto>> response) {
                List<CauHoiDto> cauHoiDtoList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null)
                    cauHoiDtoList = response.body();
                Log.d("CauHoiSuccess", cauHoiDtoList.get(viTri).getNoiDung());
                Toast.makeText(LuyenThiActivity.this, "Get Quiz Successful!" + cauHoiDtoList.get(viTri).getDapAn(), Toast.LENGTH_SHORT).show();

                loadCauHoiMoi(cauHoiDtoList);
                pbLoad.setVisibility(View.GONE);
                getEvent(cauHoiDtoList);
            }
            @Override
            public void onFailure(Call<List<CauHoiDto>> call, Throwable t) {
                Toast.makeText(LuyenThiActivity.this, "Get Quiz Failed!\n"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getEvent(List<CauHoiDto> cauHoiDtoList) {
        imbBackLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LuyenThiActivity.this, MonHocActivity.class));
                hienThiYN(Gravity.CENTER);
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "A";
                btnA.setBackgroundResource(R.drawable.round_answer_green10);
                btnA.setTextColor(Color.parseColor("#FFFFFF"));
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnB.setTextColor(Color.parseColor("#05152C"));
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setTextColor(Color.parseColor("#05152C"));
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setTextColor(Color.parseColor("#05152C"));
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "B";
                btnB.setBackgroundResource(R.drawable.round_answer_green10);
                btnB.setTextColor(Color.parseColor("#FFFFFF"));
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnA.setTextColor(Color.parseColor("#05152C"));
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setTextColor(Color.parseColor("#05152C"));
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setTextColor(Color.parseColor("#05152C"));
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "C";
                btnC.setBackgroundResource(R.drawable.round_answer_green10);
                btnC.setTextColor(Color.parseColor("#FFFFFF"));
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnA.setTextColor(Color.parseColor("#05152C"));
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnB.setTextColor(Color.parseColor("#05152C"));
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setTextColor(Color.parseColor("#05152C"));
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "D";
                btnD.setBackgroundResource(R.drawable.round_answer_green10);
                btnD.setTextColor(Color.parseColor("#FFFFFF"));//#05152C
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnA.setTextColor(Color.parseColor("#05152C"));
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnB.setTextColor(Color.parseColor("#05152C"));
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setTextColor(Color.parseColor("#05152C"));
            }
        });
        btnNextCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnA.setTextColor(Color.parseColor("#05152C"));
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnB.setTextColor(Color.parseColor("#05152C"));
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setTextColor(Color.parseColor("#05152C"));
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setTextColor(Color.parseColor("#05152C"));
                if(luaChon.isEmpty()){
                    Toast.makeText(LuyenThiActivity.this, "Vui lòng chọn đáp án!", Toast.LENGTH_SHORT).show();
                }
                else{
                    dapAn = cauHoiDtoList.get(viTri).getDapAn();
                    if(luaChon.equals("A")){
                        soSanhKQ(cauHoiDtoList,luaChon,dapAn);
                    }
                    else if(luaChon.equals("B")){
                        soSanhKQ(cauHoiDtoList,luaChon,dapAn);
                    }
                    else if(luaChon.equals("C")){
                        soSanhKQ(cauHoiDtoList,luaChon,dapAn);
                    }
                    else if(luaChon.equals("D")){
                        soSanhKQ(cauHoiDtoList,luaChon,dapAn);
                    }
                    viTri++;
                    loadCauHoiMoi(cauHoiDtoList);
                }
            }
        });
    }
    private void hienThiYN(int gravity) {
        //xử lý vị trí của dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_yn);

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


        btnThiTiep = dialog.findViewById(R.id.btnThiTiep);
        btnHuy = dialog.findViewById(R.id.btnHuy);


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LuyenThiActivity.this, MonHocActivity.class));
                finish();
                dialog.dismiss();
            }
        });
        btnThiTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void soSanhKQ(List<CauHoiDto> cauHoiDtoList,String luachon, String dapan){
        dapan = cauHoiDtoList.get(viTri).getDapAn();
        if(luachon.equals(dapan))
            diem++;
        else
            cauHoiDtoList.get(viTri).setLuaChon(luachon);
        Toast.makeText(LuyenThiActivity.this, "Điểm hiện tại: "+diem, Toast.LENGTH_SHORT).show();
    }
    private void loadCauHoiMoi(List<CauHoiDto> cauHoiDtoList) {
        if(viTri == tongCH - 1){
            btnNextCH.setText("Submit");
        }
        if(viTri == tongCH){
            hienThiKetQua(Gravity.CENTER,cauHoiDtoList);
            return;
        }

        int idCH = viTri + 1;
        tvIdCH.setText("Câu hỏi: "+idCH+"/"+tongCH);
        tvCauHoi.setText(cauHoiDtoList.get(viTri).getNoiDung());
        btnA.setText(cauHoiDtoList.get(viTri).getA());
        btnB.setText(cauHoiDtoList.get(viTri).getB());
        btnC.setText(cauHoiDtoList.get(viTri).getC());
        btnD.setText(cauHoiDtoList.get(viTri).getD());
    }

    private void thiLai(List<CauHoiDto> cauHoiDtoList) {
        diem = 0;
        viTri = 0;
        loadCauHoiMoi(cauHoiDtoList);
    }

    private void getControl() {
        pbLoad = findViewById(R.id.pbLoad);
        tvMonThi = findViewById(R.id.tvMonThi);
        imbBackLT = findViewById(R.id.imbBackLT);
        tvIdCH = findViewById(R.id.tvIdCH);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnNextCH = findViewById(R.id.btnNextCH);
    }
    //xử lý vị trí của dialog
    private void hienThiKetQua(int gravity, List<CauHoiDto> cauHoiDtoList) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_result);

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
        tvKetQua = dialog.findViewById(R.id.tvKetQua);
        tvDiem = dialog.findViewById(R.id.tvDiem);
        tvKetQua.setText("Đúng "+diem + "/" + tongCH+" câu hỏi");
        tvDiem.setText(diem + " điểm");
        //Toast.makeText(this, "Điểm = "+ dem, Toast.LENGTH_SHORT).show();

        btnThiLai = dialog.findViewById(R.id.btnThiLai);
        btnKetQua = dialog.findViewById(R.id.btnKetQua);

        btnThiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thiLai(cauHoiDtoList);
                dialog.dismiss();
            }
        });

        btnKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LuyenThiActivity.this, LichSuThiActivity.class));
                dialog.dismiss();
            }
        });
        dialog.show();//quan trọng
    }
}
