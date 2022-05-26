package com.api.dto;

import java.io.Serializable;

public class CauHoiDto implements Serializable {
    //@SerializedName("idCH"); //change name in database
    private int idCH;
    private String noiDung;
    private String a;
    private String b;
    private String c;
    private String d;
    private String dapAn;
    private String luaChon;
    private String maTaiKhoan;
    private String maMonHoc;

    public CauHoiDto(int idCH, String noiDung, String a, String b, String c, String d, String dapAn, String luaChon, String maTaiKhoan, String maMonHoc) {
        this.idCH = idCH;
        this.noiDung = noiDung;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapAn = dapAn;
        this.luaChon = luaChon;
        this.maTaiKhoan = maTaiKhoan;
        this.maMonHoc = maMonHoc;
    }

    public int getIdCH() {
        return idCH;
    }

    public void setIdCH(int idCH) {
        this.idCH = idCH;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getLuaChon() {
        return luaChon;
    }

    public void setLuaChon(String luaChon) {
        this.luaChon = luaChon;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }
}
