package com.example.hoatran.polyshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoatran.polyshop.Object.LoaiSanPham;
import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.SanPham.ListSanPham.ListSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class ViewHolderLoaiSP extends RecyclerView.ViewHolder{

    ImageView imgLoaiSp;
    TextView txtTenLoaiSP;
    CardView cvLoaiSP;
    public ViewHolderLoaiSP(@NonNull View itemView) {
        super(itemView);
        imgLoaiSp=itemView.findViewById(R.id.imgLoaiSp);
        txtTenLoaiSP=itemView.findViewById(R.id.txtLoaiSp);
        cvLoaiSP=itemView.findViewById(R.id.cvLoaiSP);
    }


}
public class AdapterLoaiSp extends RecyclerView.Adapter<ViewHolderLoaiSP> {

    List<LoaiSanPham> list=new ArrayList<>();
    Context context;

    public AdapterLoaiSp(List<LoaiSanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderLoaiSP onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.item_loai_sp,viewGroup,false);
        return new ViewHolderLoaiSP(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLoaiSP viewHolderLoaiSP, final int position) {
        viewHolderLoaiSP.txtTenLoaiSP.setText(list.get(position).getTenLoaiSp());
        Picasso.with(context).load(list.get(position).getHinhLoaiSP()).into(viewHolderLoaiSP.imgLoaiSp);

        viewHolderLoaiSP.cvLoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ListSanPhamActivity.class);
                intent.putExtra("MaLoaiSanPham",list.get(position).getMaLoaiSP());
                intent.putExtra("TenLoaiSanpham",list.get(position).getTenLoaiSp());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
