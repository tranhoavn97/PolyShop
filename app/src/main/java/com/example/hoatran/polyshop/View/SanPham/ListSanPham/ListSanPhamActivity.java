package com.example.hoatran.polyshop.View.SanPham.ListSanPham;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hoatran.polyshop.Adapters.AdapterListSanPham;
import com.example.hoatran.polyshop.Object.SanPham;
import com.example.hoatran.polyshop.Presenter.SanPham.PresenterLogicSanPham;
import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.SanPham.MainActivity;

import java.util.List;

public class ListSanPhamActivity extends AppCompatActivity implements ViewSanPham {
    AdapterListSanPham adapterListSanPham;
    PresenterLogicSanPham presenterLogicSanPham;
    Toolbar toolbar;
    RecyclerView recyclerView;
    String TenLoaiSanpham;
    int MaLoaiSanPham;
    TextView txtTenLoaiSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san_pham);
        recyclerView=findViewById(R.id.rvSanPham);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar=findViewById(R.id.toolbar);
        txtTenLoaiSP=findViewById(R.id.txtTenLoaiSP);
        Intent intent=getIntent();
        if(intent!=null)
        {
            TenLoaiSanpham=intent.getStringExtra("TenLoaiSanpham");
            MaLoaiSanPham=intent.getIntExtra("MaLoaiSanPham",0);
        }
        presenterLogicSanPham=new PresenterLogicSanPham(this);
        txtTenLoaiSP.setText(TenLoaiSanpham);
        presenterLogicSanPham.ListSanPham(MaLoaiSanPham);
        setUpToolbar();

    }

    @Override
    public void GetDataSanPham(List<SanPham> list) {
        adapterListSanPham=new AdapterListSanPham(getApplicationContext(),list);
        recyclerView.setAdapter(adapterListSanPham);
        adapterListSanPham.notifyDataSetChanged();
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
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            startActivity(new Intent(ListSanPhamActivity.this,MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
