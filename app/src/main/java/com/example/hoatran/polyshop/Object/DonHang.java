package com.example.hoatran.polyshop.Object;

public class DonHang {
    int id,MaUser;
    String Sdt,DiaChiGiao,TrangThai,TenNguoiNhan,NgayMua;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaUser() {
        return MaUser;
    }

    public void setMaUser(int maUser) {
        MaUser = maUser;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getDiaChiGiao() {
        return DiaChiGiao;
    }

    public void setDiaChiGiao(String diaChiGiao) {
        DiaChiGiao = diaChiGiao;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getTenNguoiNhan() {
        return TenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        TenNguoiNhan = tenNguoiNhan;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }
}
