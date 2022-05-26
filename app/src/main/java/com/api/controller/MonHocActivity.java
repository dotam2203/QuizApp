package com.api.controller;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.api.R;
import com.api.adapter.MonHocAdapter;
import com.api.dto.MonHocDto;
import com.api.service.MonHocService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonHocActivity extends AppCompatActivity implements MonHocAdapter.ItemClickMH {

    public final List<MonHocDto> monHocDtoList = new ArrayList<>();
    public MonHocAdapter monHocAdapter;
    public MonHocDto monHocDto = null;

    private ImageButton imbBackMH;
    private RecyclerView rvDSMH;
    private ProgressBar pbLoad;
    private LinearLayoutManager layoutManager;

    private TextView tvMonBDThi;
    private Button btnDongY, btnThoat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        imbBackMH = findViewById(R.id.imbBackMH);
        rvDSMH = findViewById(R.id.rvDSMonHoc);
        pbLoad = findViewById(R.id.pbLoad);


        layoutManager = new LinearLayoutManager(this);
        rvDSMH.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration inDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvDSMH.addItemDecoration(inDecoration);
        monHocAdapter = new MonHocAdapter(monHocDtoList, this);
        rvDSMH.setAdapter(monHocAdapter);
        setGetAllSubject();
        setEvent();
    }

    private void setEvent() {
        imbBackMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonHocActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setGetAllSubject() {
        pbLoad.setVisibility(View.VISIBLE);
        MonHocService.monHocService.layDSMonHoc().enqueue(new Callback<List<MonHocDto>>() {
            @Override
            public void onResponse(Call<List<MonHocDto>> call, Response<List<MonHocDto>> response) {
                Toast.makeText(MonHocActivity.this, "Get Subject Successful!", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null) {
                    monHocDtoList.addAll(response.body());
                    monHocAdapter.notifyDataSetChanged();
                    pbLoad.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<MonHocDto>> call, Throwable t) {
                pbLoad.setVisibility(View.GONE);
                Toast.makeText(MonHocActivity.this, "Get Subject Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(MonHocDto monHocDto) {
        if (monHocDto.getCauHoi().size() < 10) {
            AlertDialog.Builder tbLoi = new AlertDialog.Builder(MonHocActivity.this);
            tbLoi.setTitle("Thông báo");
            tbLoi.setIcon(R.drawable.ic_quiz);
            tbLoi.setMessage("Môn học này không đủ câu hỏi." + "\nChưa thể thi!");
            tbLoi.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = tbLoi.create();
            alertDialog.show();
        } else {
            callDialog(Gravity.CENTER, monHocDto);
            //message();
        }

    }

    private void message() {
        //sử dụng dialog hiển thị thông báo
        AlertDialog.Builder mess = new AlertDialog.Builder(MonHocActivity.this);
        //xác định tiêu đề
        mess.setTitle("Thông báo!");
        mess.setMessage("Bạn thật sự muốn thi môn" + monHocDto.getTenMonHoc());
        //đồng ý
        mess.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        //không đồng ý
        mess.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        //tạo dialog
        AlertDialog alertDialog = mess.create();
        //hiển thị
        alertDialog.show();
    }

    //xử lý vị trí của dialog
    private void callDialog(int gravity, MonHocDto monhoc) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_quiz);

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

        btnDongY = dialog.findViewById(R.id.btnDongY);
        btnThoat = dialog.findViewById(R.id.btnThoat);
        tvMonBDThi = dialog.findViewById(R.id.tvMonBDThi);
        //lấy dữ liệu môn thi
        tvMonBDThi.setText("Môn: " + monhoc.getTenMonHoc());

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //dialog.dismiss();
                    Intent intent = new Intent(MonHocActivity.this, LuyenThiActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mon_thi", monhoc);
                    intent.putExtras(bundle);

                    startActivity(intent);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();//đóng dialog

            }
        });

        dialog.show();//quan trọng
    }
}
