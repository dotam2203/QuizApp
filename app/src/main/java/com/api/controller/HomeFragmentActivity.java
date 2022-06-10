package com.api.controller;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.api.R;
import com.api.data.SingleAccount;
import com.api.data.SingleDemo;
import com.api.dto.TaiKhoanDto;

public class HomeFragmentActivity extends Fragment {
    private static final String USER_LOGIN = "user_login";
    TaiKhoanDto taiKhoanDto;
    LinearLayout llLT, llLS;
    Button btnY, btnN;
    ImageButton imvLogout;
    TextView tvName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        llLT = view.findViewById(R.id.llLuyenThi);
        llLS = view.findViewById(R.id.llLichSu);
        imvLogout = view.findViewById(R.id.imvLogout);
        btnY = view.findViewById(R.id.btnYes);
        btnN = view.findViewById(R.id.btnNo);
        tvName = view.findViewById(R.id.tvName);
//        sharedPreferences = getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        //nhận dữ liệu đăng nhập từ LoginActivity qua
        Bundle bundle = getActivity().getIntent().getExtras();
//        taiKhoanDto = (TaiKhoanDto) bundle.getSerializable(USER_LOGIN);
        taiKhoanDto = SingleAccount.INSTANCE.getTaiKhoanDto();

        setEvent();
        return view;
    }
    private void setEvent(){
        tvName.setText("Hi, " + taiKhoanDto.getTen());
        llLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLT.setBackgroundResource(R.drawable.round_border_green20);
                llLS.setBackgroundResource(R.drawable.round_border_while30);
                //truyền dữ liệu tk qua MonHocActivity
                Intent intent = new Intent(getContext(),MonHocActivity.class);
                Bundle send = new Bundle();
                send.putSerializable(USER_LOGIN,taiKhoanDto);
                intent.putExtras(send);
                startActivity(intent);

            }
        });
        llLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLS.setBackgroundResource(R.drawable.round_border_green20);
                llLT.setBackgroundResource(R.drawable.round_border_while30);
                //truyền dữ liệu tk qua ChiTietThiActivity
                Intent intent = new Intent(getContext(), LichSuThiActivity.class);
                Bundle send = new Bundle();
                send.putSerializable(USER_LOGIN,taiKhoanDto);
                intent.putExtras(send);
                startActivity(intent);
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
        final Dialog dialog = new Dialog(getContext());
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
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                deleteDataLocal();
                getActivity().finish();
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
    void deleteDataLocal(){
        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();*/
        SingleDemo.deleteAcc();
    }
}