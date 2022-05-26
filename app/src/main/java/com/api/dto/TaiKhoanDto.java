package com.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDto implements Serializable {
    private String maTaiKhoan;
    private String ho;
    private String ten;
    private String email;
    private String matKhau;
    private String loai;

    private List<CauHoiDto> cauHoi;
    private List<ChiTietThiDto> chiTietThi;

    public TaiKhoanDto(String maTaiKhoan, String ho, String ten, String email, String matKhau, String loai, ArrayList<CauHoiDto> cauHoi, ArrayList<ChiTietThiDto> chiTietThi) {
        this.maTaiKhoan = maTaiKhoan;
        this.ho = ho;
        this.ten = ten;
        this.email = email;
        this.matKhau = matKhau;
        this.loai = loai;
        this.cauHoi = cauHoi;
        this.chiTietThi = chiTietThi;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public List<CauHoiDto> getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(ArrayList<CauHoiDto> cauHoi) {
        this.cauHoi = cauHoi;
    }

    public List<ChiTietThiDto> getChiTietThi() {
        return chiTietThi;
    }

    public void setChiTietThi(ArrayList<ChiTietThiDto> chiTietThi) {
        this.chiTietThi = chiTietThi;
    }

    @Override
    public String toString() {
        return "TaiKhoanEntity{" +
                "maTaiKhoan='" + maTaiKhoan + '\'' +
                ", ho='" + ho + '\'' +
                ", ten='" + ten + '\'' +
                ", email='" + email + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", loai='" + loai + '\'' +
                ", cauHoi=" + cauHoi +
                ", chiTietThi=" + chiTietThi +
                '}';
    }
}
