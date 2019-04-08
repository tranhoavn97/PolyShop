package com.example.hoatran.polyshop.View.SanPham.ChiTietSanPham;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.hoatran.polyshop.Database.DataOrder;
import com.example.hoatran.polyshop.Object.SanPham;
import com.example.hoatran.polyshop.Presenter.SanPham.PresenterLogicChiTietSanPham;
import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.GioHang.GioHangActivity;
import com.example.hoatran.polyshop.View.User.SignInActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham {
    Toolbar toolbar;
    ImageView imgMatHang;
    TextView txtTenMatHang,txtGiaTien,txtMoTa;
    Button btnMua;
    ElegantNumberButton btnSoLuong;
    TextView txtCount;
    List<SanPham> list;
    int masp;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    SanPham muaSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        list=new ArrayList<>();
        initView();
        Intent intent=getIntent();
        masp=intent.getIntExtra("Masp",0);

        presenterLogicChiTietSanPham=new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.DataChiTietSanPham(masp);
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemSanPham();
            }
        });
    }

    private void ThemSanPham() {

        String gia=muaSanPham.getGiaSp();
        int soluong= Integer.parseInt(btnSoLuong.getNumber());
        String TenSp=muaSanPham.getTenSp();
        int masp=muaSanPham.getMasp();
        String hinhsp=muaSanPham.getHinhSp();

        SanPham sanPham=new SanPham();
        sanPham.setGiaSp(gia);
        sanPham.setSoLuong(soluong);
        sanPham.setTenSp(TenSp);
        sanPham.setIdOrder(masp);
        sanPham.setHinhSp(hinhsp);
        sanPham.setIdUser(SignInActivity.IdUser);
        long check=new DataOrder(getApplicationContext()).AddToCart(sanPham);
        if(check>0)
        {
            supportInvalidateOptionsMenu();
            Toast.makeText(this, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        toolbar=findViewById(R.id.toolbar);
        imgMatHang=findViewById(R.id.imgMatHang);
        txtGiaTien=findViewById(R.id.txtGiaTien);
        txtTenMatHang=findViewById(R.id.txtTenMatHang);
        txtMoTa=findViewById(R.id.txtMoTa);
        btnMua=findViewById(R.id.btnMua);
        btnSoLuong=findViewById(R.id.btnSoLuong);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if(getSupportActionBar() !=null)
        {
            Drawable drawable= ResourcesCompat.getDrawable(this.getResources(), R.drawable.ic_arrow_back_white_24dp, null);

            //custom color
            //drawable.setColorFilter(ResourcesCompat.getColor(this.getResources(), R.color.colorwhite, null), PorterDuff.Mode.SRC_IN);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(drawable);

        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ChiTietSanPham(SanPham sanPham) {
        muaSanPham=sanPham;
        masp=sanPham.getMasp();
        txtMoTa.setText(sanPham.getMoTa());
        txtTenMatHang.setText(sanPham.getTenSp());
        long Price= Long.valueOf(sanPham.getGiaSp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaTien.setText(decimalFormat.format(Price)+" Đ");
       
        Picasso.with(getApplicationContext()).load(sanPham.getHinhSp()).into(imgMatHang);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_product, menu);
        MenuItem iCart = menu.findItem(R.id.nav_DP_Cart);
        View itemview = MenuItemCompat.getActionView(iCart);
        txtCount= itemview.findViewById(R.id.txtCount);


        list=new DataOrder(getApplicationContext()).getCart(SignInActivity.IdUser);
        String count= String.valueOf(list.size());
        txtCount.setText(count);

        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class));
            }
        });

        return true;
    }
}
