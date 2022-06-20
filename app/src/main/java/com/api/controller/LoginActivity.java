package com.api.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.api.R;
import com.api.data.SingleAccount;
import com.api.data.SingleDemo;
import com.api.dto.TaiKhoanDto;
import com.api.service.TaiKhoanService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String USER_LOGIN = "user_login";
    private EditText txtUserLogin;
    private EditText txtPassLogin;
    private Button btnLogin;
    private Context context;
    private ProgressBar pbLoad;;

    private List<TaiKhoanDto> listTK;
    private TaiKhoanDto taiKhoanDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SingleDemo.getInstance().setSharedPreferences(this);
        context = this;
        setControl();
        pbLoad.setVisibility(View.VISIBLE);
        getListAccount();
        btnLogin.setOnClickListener(this);
    }

    private boolean getCheckAccount() {
        String user = SingleDemo.getInstance().getUser("user","");
        String pass = SingleDemo.getInstance().getPass("pass","");
        if(user.isEmpty() || pass.isEmpty()){
            pbLoad.setVisibility(View.GONE);
            txtUserLogin.setEnabled(true);
            txtPassLogin.setEnabled(true);
            return false;
        }
        else {
            txtUserLogin.setEnabled(false);
            txtPassLogin.setEnabled(false);
            txtUserLogin.setText(user);
            txtPassLogin.setText(pass);
            pbLoad.setVisibility(View.VISIBLE);
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        int luachon = view.getId();
        switch (luachon){
            case R.id.btnLogin:
                clickLogin();
        }
    }

    private void setControl(){
        txtUserLogin = findViewById(R.id.txtUserLogin);
        txtPassLogin = findViewById(R.id.txtPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        pbLoad = findViewById(R.id.pbLoad);
        txtUserLogin.setText("SV01");
        txtPassLogin.setText("123");
        listTK = new ArrayList<>();
    }

    private void getListAccount() {
        TaiKhoanService.taiKhoanService.layDSTaiKhoan().enqueue(new Callback<List<TaiKhoanDto>>() {
            @Override
            public void onResponse(Call<List<TaiKhoanDto>> call, Response<List<TaiKhoanDto>> response) {
                listTK = response.body();
                if(getCheckAccount()){
                    clickLogin();
                }
                else if(!getCheckAccount()){
                    pbLoad.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Tài khoản chưa được lưu.\nVui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            @Override
            public void onFailure(Call<List<TaiKhoanDto>> call, Throwable t) {
                pbLoad.setVisibility(View.GONE);
            }
        });
    }

    private void clickLogin() {
        pbLoad.setVisibility(View.VISIBLE);
        String strUserLogin = txtUserLogin.getText().toString().trim();
        String strPassLogin = txtPassLogin.getText().toString().trim();

        if (listTK == null || listTK.isEmpty())
            return;//kiểm tra danh sách trống thì trả về null;

        boolean isAccount = false;//kiểm tra có tồn tại sinh viên trong danh sách hay không
        //check user & pass
        if(strUserLogin.length() > 0){
            strUserLogin = strUserLogin.toUpperCase();
            txtUserLogin.setText(strUserLogin);
        }
        if(TextUtils.isEmpty(strUserLogin)){
            pbLoad.setVisibility(View.GONE);
            txtUserLogin.requestFocus();
            txtUserLogin.setError(context.getResources().getString(R.string.error_user_login));
            //isAccount = true;
        }
        if(TextUtils.isEmpty(strPassLogin)){
            pbLoad.setVisibility(View.GONE);
            txtPassLogin.requestFocus();
            txtPassLogin.setError(context.getResources().getString(R.string.error_pass_login));
            //isAccount = true;
        }

        for(TaiKhoanDto tk : listTK){
            if(strUserLogin.equals(tk.getMaTaiKhoan().toUpperCase()) && strPassLogin.equals(tk.getMatKhau()) && tk.getLoai().equals("SV")){
                isAccount = true;
                taiKhoanDto = tk;
                setDataLocal(taiKhoanDto);
                break;
            }
        }
        if(isAccount){
            // goto HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            //truyền dữ liệu user qua HomeActivity
            Bundle bundle = new Bundle();
            bundle.putSerializable(USER_LOGIN,taiKhoanDto);
            intent.putExtras(bundle);
            SingleAccount.INSTANCE.setTaiKhoanDto(taiKhoanDto);

            this.startActivity(intent);
        }
        else{
            pbLoad.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this,"Username or Password Invalid!",Toast.LENGTH_SHORT).show();
        }
    }
    private void setDataLocal(TaiKhoanDto taiKhoanDto) {
        SingleDemo.setUser("user",taiKhoanDto.getMaTaiKhoan());
        SingleDemo.setPass("pass",taiKhoanDto.getMatKhau());
    }
}