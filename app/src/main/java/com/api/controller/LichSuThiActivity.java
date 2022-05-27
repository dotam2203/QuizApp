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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.api.R;

public class LichSuThiActivity extends AppCompatActivity {
    Button btnClick, btnThiLai, btnKetQua;
    ImageButton imbBackLS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setControl();
        setEvent();
    }

    private void setEvent() {
        imbBackLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

   /* private void message() {
        //sử dụng dialog hiển thị thông báo
        AlertDialog.Builder mess = new AlertDialog.Builder(LichSuThiActivity.this);
        //xác định tiêu đề
        mess.setTitle("Thông báo!");
        mess.setMessage("Bạn thật sự muốn nộp bài?");
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
    }*/

    private void setControl() {
        imbBackLS = findViewById(R.id.imbBackLS);
    }
    //xử lý vị trí của dialog
    /*private void callDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_result);

        Window window = dialog.getWindow();
        if(window == null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        //click ra bên ngoài để tắt dialog
        if(Gravity.CENTER == gravity){
            dialog.setCancelable(false);
        }
        else {
            dialog.setCancelable(false);
        }


        btnThiLai = dialog.findViewById(R.id.btnThiLai);
        btnKetQua = dialog.findViewById(R.id.btnKetQua);

        btnThiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                Intent intent = new Intent(LichSuThiActivity.this,LuyenThiActivity.class);
                //intent.putExtra("thilai",thilai);
                startActivity(intent);
            }
        });

        btnKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();//đóng dialog

            }
        });

        dialog.show();//quan trọng
    }*/
}
