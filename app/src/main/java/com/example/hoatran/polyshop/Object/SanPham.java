package com.example.hoatran.polyshop.Object;

public class SanPham {
    int Masp,MaLoaisp,MaTH,SoLuong,IdOrder,idUser;
    String TenSp,GiaSp,HinhSp,MoTa;
    byte[] image;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(int idOrder) {
        IdOrder = idOrder;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getMasp() {
        return Masp;
    }

    public void setMasp(int masp) {
        Masp = masp;
    }

    public int getMaLoaisp() {
        return MaLoaisp;
    }

    public void setMaLoaisp(int maLoaisp) {
        MaLoaisp = maLoaisp;
    }

    public int getMaTH() {
        return MaTH;
    }

    public void setMaTH(int maTH) {
        MaTH = maTH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String tenSp) {
        TenSp = tenSp;
    }

    public String getGiaSp() {
        return GiaSp;
    }

    public void setGiaSp(String giaSp) {
        GiaSp = giaSp;
    }

    public String getHinhSp() {
        return HinhSp;
    }

    public void setHinhSp(String hinhSp) {
        HinhSp = hinhSp;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }
}
