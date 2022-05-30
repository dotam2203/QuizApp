package com.api.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.api.R;
import com.api.dto.TaiKhoanDto;

public class AccountFragmentActivity extends Fragment {
    public static final String USER_LOGIN = "user_login";
    TaiKhoanDto taiKhoanDto;
    TextView tvUsername, tvPassword, tvEmail, tvName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        setControl(view);
        setEvent();
        return view;
    }

    private void setControl(View view) {
        tvUsername = view.findViewById(R.id.tvUsername);
        tvPassword = view.findViewById(R.id.tvPassword);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
    }

    private void setEvent() {
        Bundle receive = getActivity().getIntent().getExtras();
        if(receive != null){
            taiKhoanDto = (TaiKhoanDto) receive.getSerializable(USER_LOGIN);
            tvUsername.setText(taiKhoanDto.getMaTaiKhoan());
            tvPassword.setText(taiKhoanDto.getMatKhau());
            tvEmail.setText(taiKhoanDto.getEmail());
            tvName.setText(taiKhoanDto.getHo() + " " +taiKhoanDto.getTen());
        }
    }
}
