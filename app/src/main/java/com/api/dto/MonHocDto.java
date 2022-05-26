package com.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MonHocDto implements Serializable {
    private String maMonHoc;
    private String tenMonHoc;

    private List<CauHoiDto> cauHoi;
    private List<ChiTietThiDto> chiTietThi;

    public MonHocDto(String maMonHoc, String tenMonHoc, List<CauHoiDto> cauHoi, List<ChiTietThiDto> chiTietThi) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.cauHoi = cauHoi;
        this.chiTietThi = chiTietThi;
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

    public List<CauHoiDto> getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(List<CauHoiDto> cauHoi) {
        this.cauHoi = cauHoi;
    }

    public List<ChiTietThiDto> getChiTietThi() {
        return chiTietThi;
    }

    public void setChiTietThi(List<ChiTietThiDto> chiTietThi) {
        this.chiTietThi = chiTietThi;
    }

}
