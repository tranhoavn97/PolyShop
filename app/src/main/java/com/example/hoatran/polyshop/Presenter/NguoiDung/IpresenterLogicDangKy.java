package com.example.hoatran.polyshop.Presenter.NguoiDung;

import com.example.hoatran.polyshop.Model.NguoiDung.ModelDangKy;
import com.example.hoatran.polyshop.Object.NguoiDung;
import com.example.hoatran.polyshop.View.User.ViewDangKy;

public class IpresenterLogicDangKy implements IPresenterDangKy {

    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public IpresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangKy=new ModelDangKy();
    }


    @Override
    public void ThucHienDangKy(NguoiDung nguoiDung) {
        boolean check=modelDangKy.RegisterMember(nguoiDung);
        if(check)
        {
            viewDangKy.DangKyThanhCong();
        }
        else
        {
            viewDangKy.DangKyThatBai();
        }
    }
}
