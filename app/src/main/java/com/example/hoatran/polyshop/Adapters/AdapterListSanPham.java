package com.example.hoatran.polyshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoatran.polyshop.Database.DataOrder;
import com.example.hoatran.polyshop.Object.SanPham;
import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.SanPham.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.hoatran.polyshop.View.User.SignInActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class ViewHolderListSanPham extends RecyclerView.ViewHolder{

    TextView txtTenSanPham,txtGiaTien;
    ImageView HinhSanPham;
    CardView cv;
    ImageButton btnQuickAdd;
    public ViewHolderListSanPham(@NonNull View itemView) {
        super(itemView);
        txtTenSanPham=itemView.findViewById(R.id.txtTenSanPham);
        txtGiaTien=itemView.findViewById(R.id.txtGiaSanPham);
        HinhSanPham=itemView.findViewById(R.id.imgSanPham);
        cv=itemView.findViewById(R.id.cv);
        btnQuickAdd=itemView.findViewById(R.id.btnQuickAdd);
    }
}
public class AdapterListSanPham extends RecyclerView.Adapter<ViewHolderListSanPham> {

    Context context;
    List<SanPham> list=new ArrayList<>();

    public AdapterListSanPham(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderListSanPham onCreateViewHolder(@NonNull ViewGroup parents, int position) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_layout_san_pham,parents,false);
        return new ViewHolderListSanPham(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListSanPham holder, final int position) {
        long Price=Long.valueOf(list.get(position).getGiaSp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtGiaTien.setText(decimalFormat.format(Price)+" Đ");
        holder.txtTenSanPham.setText(list.get(position).getTenSp());
        Picasso.with(context).load(list.get(position).getHinhSp()).into(holder.HinhSanPham);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("Masp",list.get(position).getMasp());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.btnQuickAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String gia=list.get(position).getGiaSp();
                int soluong= 1;
                String TenSp=list.get(position).getTenSp();
                int masp=list.get(position).getMasp();
                String hinhsp=list.get(position).getHinhSp();

                SanPham sanPham=new SanPham();
                sanPham.setGiaSp(gia);
                sanPham.setSoLuong(soluong);
                sanPham.setTenSp(TenSp);
                sanPham.setIdOrder(masp);
                sanPham.setHinhSp(hinhsp);
                sanPham.setIdUser(SignInActivity.IdUser);
                long check=new DataOrder(context).AddToCart(sanPham);
                if(check>0)
                {

                    Toast.makeText(context, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
