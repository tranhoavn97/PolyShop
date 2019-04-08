package com.example.hoatran.polyshop.Presenter.SanPham;

import com.example.hoatran.polyshop.Model.SanPham.ModelSanPham;
import com.example.hoatran.polyshop.Object.SanPham;
import com.example.hoatran.polyshop.View.SanPham.ListSanPham.ViewSanPham;

import java.util.List;

public class PresenterLogicSanPham implements IprensenterSanPham {

    ModelSanPham modelSanPham;
    ViewSanPham viewSanPham;

    public PresenterLogicSanPham(ViewSanPham viewSanPham) {
        this.viewSanPham = viewSanPham;
        modelSanPham=new ModelSanPham();
    }

    @Override
    public void ListSanPham(int MaLoai) {
        List<SanPham> list=modelSanPham.LoadListProduct(MaLoai);
        if(list.size()>0)
        {
            viewSanPham.GetDataSanPham(list);
        }
    }
}
