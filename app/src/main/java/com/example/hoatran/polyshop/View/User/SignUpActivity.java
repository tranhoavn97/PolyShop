package com.example.hoatran.polyshop.View.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoatran.polyshop.Object.NguoiDung;
import com.example.hoatran.polyshop.Presenter.NguoiDung.IpresenterLogicDangKy;
import com.example.hoatran.polyshop.R;

public class SignUpActivity extends AppCompatActivity implements ViewDangKy {
    EditText edtTaiKhoai,edtMatKhau,edtSDT,edtTenDayDu;
    Button btnDongY;
    IpresenterLogicDangKy ipresenterLogicDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtMatKhau=findViewById(R.id.edtMatKhau);
        edtSDT=findViewById(R.id.edtSdt);
        edtTenDayDu=findViewById(R.id.edtHoTen);
        edtTaiKhoai=findViewById(R.id.edtTaiKhoan);
        btnDongY=findViewById(R.id.btnRegister);
        ipresenterLogicDangKy=new IpresenterLogicDangKy(this);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiDung nguoiDung=new NguoiDung();
                nguoiDung.setHoTenDayDu(edtTenDayDu.getText().toString());
                nguoiDung.setMatKhau(edtMatKhau.getText().toString());
                nguoiDung.setSDT(edtSDT.getText().toString());
                nguoiDung.setTaiKhoan(edtTaiKhoai.getText().toString());

                ipresenterLogicDangKy.ThucHienDangKy(nguoiDung);
            }
        });


    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(this, "Đăng ký Thành Công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
    }
}
