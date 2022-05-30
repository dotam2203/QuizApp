package com.api.controller;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.api.R;
import com.api.adapter.ChiTietThiAdapter;
import com.api.dto.ChiTietThiDto;
import com.api.dto.MonHocDto;
import com.api.dto.TaiKhoanDto;
import com.api.service.ChiTietThiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSuThiActivity extends AppCompatActivity implements ChiTietThiAdapter.ItemClickCTT{
    public static final String USER_LOGIN = "user_login";
    public final List<ChiTietThiDto> chiTietThiDtoList = new ArrayList<>();
    public ChiTietThiAdapter chiTietThiAdapter;
    TaiKhoanDto taiKhoanDto;
    List<ChiTietThiDto> filter;

    ImageButton imbBackLS;
    RecyclerView rvDSCTT;
    SearchView searchCTT;
    ProgressBar pbLoad;
    LinearLayoutManager layoutManager;
    TextView tvMaMT, tvTenMT, tvNgayThi, tvDiem, tvMaTK, tvHoTen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //nhận dữ liệu đăng nhập từ LoginActivity qua
        Bundle bundle = getIntent().getExtras();
        taiKhoanDto = (TaiKhoanDto) bundle.getSerializable(USER_LOGIN);

        setControl();

        layoutManager = new LinearLayoutManager(this);
        rvDSCTT.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration inDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvDSCTT.addItemDecoration(inDecoration);
        chiTietThiAdapter = new ChiTietThiAdapter(chiTietThiDtoList,this);
        rvDSCTT.setAdapter(chiTietThiAdapter);

        setGetAllSubject();
        setEvent();
    }
    private void setGetAllSubject() {
        pbLoad.setVisibility(View.VISIBLE);
        ChiTietThiService.chiTietThiService.layDSChiTietThiTheoTaiKhoan(taiKhoanDto.getMaTaiKhoan()).enqueue(new Callback<List<ChiTietThiDto>>() {
            @Override
            public void onResponse(Call<List<ChiTietThiDto>> call, Response<List<ChiTietThiDto>> response) {
                //Toast.makeText(LichSuThiActivity.this, "Get History Successful!", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful() && response.body() != null){
                    chiTietThiDtoList.addAll(response.body());
                    chiTietThiAdapter.notifyDataSetChanged();
                    pbLoad.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<List<ChiTietThiDto>> call, Throwable t) {
                pbLoad.setVisibility(View.GONE);
                Toast.makeText(LichSuThiActivity.this, "Get History Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setEvent() {
        imbBackLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchCTT.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getFilter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                getFilter(newText);
                return false;
            }
        });
    }
    private void getFilter(String s) {
        filter = new ArrayList<>();
        for(ChiTietThiDto m : chiTietThiDtoList){
            if(m.getNgayThi().toLowerCase().contains(s.toLowerCase())|| m.getTenMonHoc().toLowerCase().contains(s.toLowerCase())){
                filter.add(m);
            }
        }
        chiTietThiAdapter.setFilterList(filter);
        if(filter.isEmpty()){
            Toast.makeText(this, "Không tìm thấy môn thi!", Toast.LENGTH_SHORT).show();
        }
    }
    private void setControl() {
        imbBackLS = findViewById(R.id.imbBackLS);
        rvDSCTT = findViewById(R.id.rvDSCTT);
        searchCTT = findViewById(R.id.searchCTT);
        pbLoad = findViewById(R.id.pbLoad);
    }

    @Override
    public void onItemClick(ChiTietThiDto chiTietThiDto) {
        callDialog(Gravity.CENTER,chiTietThiDto);
    }
    //xử lý vị trí của dialog
    private void callDialog(int gravity,ChiTietThiDto chiTietThiDto){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_dialog_chitietthi);

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
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }

        tvMaMT = dialog.findViewById(R.id.tvMaMT);
        tvTenMT = dialog.findViewById(R.id.tvTenMT);
        tvNgayThi = dialog.findViewById(R.id.tvNgayThi);
        tvDiem = dialog.findViewById(R.id.tvDiem);
        tvMaTK = dialog.findViewById(R.id.tvMaTK);
        tvHoTen = dialog.findViewById(R.id.tvHoTen);

        tvMaMT.setText(chiTietThiDto.getMaMonHoc());
        tvTenMT.setText(chiTietThiDto.getTenMonHoc());
        tvNgayThi.setText(chiTietThiDto.getNgayThi());
        tvDiem.setText(String.valueOf(chiTietThiDto.getDiem()));
        tvMaTK.setText(chiTietThiDto.getMaTaiKhoan());
        tvHoTen.setText(chiTietThiDto.getHoTen());

        dialog.show();//quan trọng
    }
}
