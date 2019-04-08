package com.example.hoatran.polyshop.Object;

public class NguoiDung {
    int id;
    String HoTenDayDu,TaiKhoan,SDT,MatKhau;

    public NguoiDung() {
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTenDayDu() {
        return HoTenDayDu;
    }

    public void setHoTenDayDu(String hoTenDayDu) {
        HoTenDayDu = hoTenDayDu;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
