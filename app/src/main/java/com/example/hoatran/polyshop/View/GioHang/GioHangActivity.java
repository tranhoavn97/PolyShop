package com.example.hoatran.polyshop.View.GioHang;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoatran.polyshop.Adapters.AdapterGioHang;
import com.example.hoatran.polyshop.Database.DataOrder;
import com.example.hoatran.polyshop.Model.DonHang.ModelDonHang;
import com.example.hoatran.polyshop.Object.DonHang;
import com.example.hoatran.polyshop.Object.SanPham;
import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.SanPham.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.hoatran.polyshop.View.SanPham.MainActivity;
import com.example.hoatran.polyshop.View.User.SignInActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    Button btnDatHang,btnMuaHang;
    EditText edtTenNguoiNhan,edtSdt,edtDiaChiNhan;
    List<SanPham> list;
    TextView giohangtrong;
    TextView txtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        toolbar=findViewById(R.id.tlGioHang);
        recyclerView=findViewById(R.id.rv_GioHang);
        btnDatHang=findViewById(R.id.btnCheckOut);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        btnMuaHang=findViewById(R.id.btnMuaHang);
        giohangtrong=findViewById(R.id.giohangtrong);
        txtTotal=findViewById(R.id.txtTotal);
        setUpToolbar();

        ResetAdapter();

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog();
            }
        });
        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GioHangActivity.this,MainActivity.class));
            }
        });



    }

    private void ResetAdapter() {
        list=new ArrayList<>();

        list=new DataOrder(getApplicationContext()).getCart(SignInActivity.IdUser);
        if(list.size()<=0)
        {
            btnMuaHang.setVisibility(View.VISIBLE);
            btnDatHang.setVisibility(View.INVISIBLE);

            giohangtrong.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else {
            btnMuaHang.setVisibility(View.INVISIBLE);
            btnDatHang.setVisibility(View.VISIBLE);

            giohangtrong.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        final AdapterGioHang adapterGioHang=new AdapterGioHang(getApplicationContext(),list);
        recyclerView.setAdapter(adapterGioHang);
        long TongTien=0;
        for (int i = 0; i <list.size() ; i++) {
            TongTien+=Long.valueOf(list.get(i).getGiaSp())*Long.valueOf(list.get(i).getSoLuong());
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(TongTien)+" Đ");

        adapterGioHang.setOnItemClickListener(new AdapterGioHang.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent=new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("Masp",list.get(position).getIdOrder());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(final int position, View v) {
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(GioHangActivity.this);
                mBuilder.setTitle("Xác nhận");
                mBuilder.setIcon(R.drawable.ic_warning_black_24dp);
                mBuilder.setMessage("Bạn có muốn xóa mặt hàng này không?");

                mBuilder.create();

                mBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean check=new DataOrder(getApplicationContext()).removeFromCart(list.get(position).getMasp(),SignInActivity.IdUser);
                        if(check)
                        {
                            Toast.makeText(getApplicationContext(), "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();

                            ResetAdapter();

                        }
                    }
                });
                mBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.show();

            }
        });
        adapterGioHang.notifyDataSetChanged();

    }

    private void ShowDialog() {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(GioHangActivity.this);
        mBuilder.setTitle("Thông Tin Khách Hàng");
        mBuilder.setIcon(R.drawable.ic_info_black_24dp);
        mBuilder.setMessage("Vui Lòng nhập đầy đủ thông tin!");
        View itemview=getLayoutInflater().inflate(R.layout.layout_them_don_hang,null);
        edtDiaChiNhan=itemview.findViewById(R.id.edtDiaChiNhan);
        edtTenNguoiNhan=itemview.findViewById(R.id.edtTenNguoiNhan);
        edtSdt=itemview.findViewById(R.id.edtSdt);
        mBuilder.setView(itemview);
        mBuilder.create();

        mBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DonHang donHang=new DonHang();
                donHang.setDiaChiGiao(edtDiaChiNhan.getText().toString());
                donHang.setTenNguoiNhan(edtTenNguoiNhan.getText().toString());
                donHang.setSdt(edtSdt.getText().toString());
                donHang.setMaUser(SignInActivity.IdUser);
                //Toast.makeText(GioHangActivity.this, ""+SignInActivity.IdUser, Toast.LENGTH_SHORT).show();
                boolean kiemtra=new ModelDonHang().ThemDonHang(donHang);
                if(kiemtra)
                {
                    Toast.makeText(GioHangActivity.this, "Đặt Hàng Thành Công", Toast.LENGTH_SHORT).show();
                    new DataOrder(getApplicationContext()).CleanCart(SignInActivity.IdUser);
                    ResetAdapter();
                }
                else {
                    Toast.makeText(GioHangActivity.this, "lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.show();
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
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
