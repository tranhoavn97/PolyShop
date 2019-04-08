package com.example.hoatran.polyshop.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoatran.polyshop.Object.DonHang;
import com.example.hoatran.polyshop.R;

import java.util.ArrayList;
import java.util.List;

class ViewHolderDonHang extends RecyclerView.ViewHolder{

    TextView order_id,order_datetime,order_status,order_phone,order_address,txtTenNguoiNhan;

    public ViewHolderDonHang(@NonNull View itemView) {
        super(itemView);
        order_address=itemView.findViewById(R.id.order_address);
        order_datetime=itemView.findViewById(R.id.order_datetime);
        order_id=itemView.findViewById(R.id.order_id);
        order_phone=itemView.findViewById(R.id.order_phone);
        order_status=itemView.findViewById(R.id.order_status);
        txtTenNguoiNhan=itemView.findViewById(R.id.txtTenNguoiNhan);
    }
}
public class AdapterDonHang extends RecyclerView.Adapter<ViewHolderDonHang> {

    List<DonHang> list=new ArrayList<>();
    Context context;

    public AdapterDonHang(List<DonHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDonHang onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_don_hang,viewGroup,false);
        return new ViewHolderDonHang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDonHang holder, int position) {
        holder.txtTenNguoiNhan.setText(list.get(position).getTenNguoiNhan());
        holder.order_status.setText(list.get(position).getTrangThai());
        holder.order_phone.setText(list.get(position).getSdt());
        holder.order_datetime.setText(list.get(position).getNgayMua());
        holder.order_address.setText(list.get(position).getDiaChiGiao());
        holder.order_id.setText(list.get(position).getId()+"");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
