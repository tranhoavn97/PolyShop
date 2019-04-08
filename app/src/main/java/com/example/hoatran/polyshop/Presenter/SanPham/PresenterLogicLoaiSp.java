package com.example.hoatran.polyshop.Presenter.SanPham;

import com.example.hoatran.polyshop.Model.SanPham.ModelLoaiSanPham;
import com.example.hoatran.polyshop.Object.LoaiSanPham;
import com.example.hoatran.polyshop.View.SanPham.LoaiSanPham.ViewLoaiSp;

import java.util.List;

public class PresenterLogicLoaiSp implements IpresenterLoaiSp {
    ViewLoaiSp viewLoaiSp;
    ModelLoaiSanPham modelLoaiSanPham;

    public PresenterLogicLoaiSp(ViewLoaiSp viewLoaiSp) {
        this.viewLoaiSp = viewLoaiSp;
        modelLoaiSanPham=new ModelLoaiSanPham();
    }


    @Override
    public void GetLoaisp() {
        List<LoaiSanPham> list=modelLoaiSanPham.LoaiLoaiSp();
        if(list.size()>0)
        {
            viewLoaiSp.LoadLoaiSp(list);
        }
    }
}
