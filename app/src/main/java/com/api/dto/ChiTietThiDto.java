package com.api.dto;

import java.io.Serializable;

public class ChiTietThiDto implements Serializable {
    private int idCTT;
    private String ngayThi;
    private double diem;
    private String maTaiKhoan;
    private String hoTen;
    private String maMonHoc;
    private String tenMonHoc;

    public ChiTietThiDto() {
    }

    public ChiTietThiDto(int idCTT, String ngayThi, double diem, String maTaiKhoan, String hoTen, String maMonHoc, String tenMonHoc) {
        this.idCTT = idCTT;
        this.ngayThi = ngayThi;
        this.diem = diem;
        this.maTaiKhoan = maTaiKhoan;
        this.hoTen = hoTen;
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
    }

    public int getIdCTT() {
        return idCTT;
    }

    public void setIdCTT(int idCTT) {
        this.idCTT = idCTT;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }
}
