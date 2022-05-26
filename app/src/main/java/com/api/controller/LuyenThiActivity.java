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
    private ImageButton imbNopBai, imbBackLT;
    private TextView tvMonThi, tvIdCH, tvCauHoi, tvKetQua, tvDiem;
    private AppCompatButton btnNopBai, btnHuy, btnThiLai, btnKetQua, btnY, btnN, btnA, btnB, btnC, btnD, btnNextCH;

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
                startActivity(new Intent(LuyenThiActivity.this, MonHocActivity.class));
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "A";
                btnA.setBackgroundResource(R.drawable.round_answer_green10);
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "B";
                btnB.setBackgroundResource(R.drawable.round_answer_green10);
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "C";
                btnC.setBackgroundResource(R.drawable.round_answer_green10);
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luaChon = "D";
                btnD.setBackgroundResource(R.drawable.round_answer_green10);
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
            }
        });
        btnNextCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnA.setBackgroundResource(R.drawable.round_answer_gray10);
                btnB.setBackgroundResource(R.drawable.round_answer_gray10);
                btnC.setBackgroundResource(R.drawable.round_answer_gray10);
                btnD.setBackgroundResource(R.drawable.round_answer_gray10);
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

    private void hienThiKQ(List<CauHoiDto> cauHoiDtoList) {
        new AlertDialog.Builder(this)
                .setTitle("Chúc mừng bạn hoàn thành bài thi!")
                .setMessage("ĐIỂM THI CỦA BẠN LÀ: "+diem+" đạt "+diem+"/"+tongCH+" câu hỏi.")
                .setPositiveButton("Restart",((dialogInterface, i) -> thiLai(cauHoiDtoList)))
                .setCancelable(false)
                .show();
    }

    private void thiLai(List<CauHoiDto> cauHoiDtoList) {
        diem = 0;
        viTri = 0;
        loadCauHoiMoi(cauHoiDtoList);
    }

    private void getControl() {
        pbLoad = findViewById(R.id.pbLoad);
        tvMonThi = findViewById(R.id.tvMonThi);
        imbNopBai = findViewById(R.id.imbNopBai);
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
    /*private void hienThiYN(int gravity) {
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


        btnY = dialog.findViewById(R.id.btnYes);
        btnN = dialog.findViewById(R.id.btnNo);


        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LuyenThiActivity.this, MonHocActivity.class);
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
    }*/
    //xử lý vị trí của dialog
    /*private void hienThiThongBao(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_message);

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


        btnNopBai = dialog.findViewById(R.id.btnNopBai);
        btnHuy = dialog.findViewById(R.id.btnHuy);


        btnNopBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienThiKetQua(Gravity.CENTER);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }*/
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
            }
        });

        dialog.show();//quan trọng
    }

}
