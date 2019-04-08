package com.example.hoatran.polyshop.Presenter.SanPham;

import com.example.hoatran.polyshop.Model.SanPham.ModelChiTietSanPham;
import com.example.hoatran.polyshop.Object.SanPham;
import com.example.hoatran.polyshop.View.SanPham.ChiTietSanPham.ViewChiTietSanPham;

public class PresenterLogicChiTietSanPham implements IpresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham=new ModelChiTietSanPham();
    }

    @Override
    public void DataChiTietSanPham(int masp) {
        SanPham sanPham=modelChiTietSanPham.LoadDetailProduct(masp);
        if(sanPham!=null)
        {
            viewChiTietSanPham.ChiTietSanPham(sanPham);
        }
    }
}
