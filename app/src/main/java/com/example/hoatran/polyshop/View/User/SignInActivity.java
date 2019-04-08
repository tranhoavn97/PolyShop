package com.example.hoatran.polyshop.View.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoatran.polyshop.Model.NguoiDung.ModelDangNhap;
import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.SanPham.MainActivity;

public class SignInActivity extends AppCompatActivity {
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDongY;
    ModelDangNhap modelDangNhap;
    TextView txtDk;
    public static int IdUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtMatKhau=findViewById(R.id.edtMatKhau);
        edtTaiKhoan=findViewById(R.id.edtTaiKhoan);
        btnDongY=findViewById(R.id.btnRegister);
        txtDk=findViewById(R.id.txtDk);
        txtDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });
        modelDangNhap=new ModelDangNhap();

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taikhoan=edtTaiKhoan.getText().toString();
                String matkhau=edtMatKhau.getText().toString();

                boolean check=modelDangNhap.CheckLogin(getBaseContext(),taikhoan,matkhau);
                if(check)
                {
                    IdUser=modelDangNhap.getpreferencesLogin(getBaseContext());

                    startActivity(new Intent(SignInActivity.this,MainActivity.class));

                }
                else
                {
                    Toast.makeText(SignInActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
