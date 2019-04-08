package com.example.hoatran.polyshop.View.SanPham;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.DonHang.DonHangFragment;
import com.example.hoatran.polyshop.View.GioHang.GioHangActivity;
import com.example.hoatran.polyshop.View.SanPham.LoaiSanPham.LoaiSanPhamFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager;
    Toolbar mToolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView txtFulllname,txt_toolbar_til,txtAn;

    CircleImageView img_Avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar=findViewById(R.id.mToolbar);
        txt_toolbar_til=findViewById(R.id.txt_toolbar_til);
        mToolbar.setTitle("");
        drawerLayout=findViewById(R.id.Drawerlayout);
        setUpActionBar();
        fragmentManager = getSupportFragmentManager();
        navigationView=findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction ban = fragmentManager.beginTransaction();//
        LoaiSanPhamFragment hienLoaiSp = new LoaiSanPhamFragment();
        ban.replace(R.id.content, hienLoaiSp);
        ban.commit();
    }


    @SuppressLint("RestrictedApi")
    private void setUpActionBar() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, mToolbar, R.string.mo, R.string.dong) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);//ba lằng;
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //navigationView.setItemIconTintList(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.colorwhite));
        } else {
            drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorwhite));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.nav_SanPham:
                FragmentTransaction tranHienThiThucDon = fragmentManager.beginTransaction();//
                LoaiSanPhamFragment hienThiThucdon = new LoaiSanPhamFragment();
                tranHienThiThucDon.replace(R.id.content, hienThiThucdon);
                tranHienThiThucDon.commit();
                txt_toolbar_til.setText("Trang chủ");

                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_Order:
                FragmentTransaction tranHienThiThucDon1 = fragmentManager.beginTransaction();//
                DonHangFragment hienThiThucdon1 = new DonHangFragment();
                tranHienThiThucDon1.replace(R.id.content, hienThiThucdon1);
                tranHienThiThucDon1.commit();
                txt_toolbar_til.setText("Đơn hàng");

                item.setChecked(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_Cart:
                startActivity(new Intent(MainActivity.this,GioHangActivity.class));
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }

}
