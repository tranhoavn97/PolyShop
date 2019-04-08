package com.example.hoatran.polyshop.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.hoatran.polyshop.Object.SanPham;
import com.example.hoatran.polyshop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;



public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {
    Context context;
    List<SanPham> sanPhamList;
    private static  ClickListener clickListener;
    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }
    public class ViewHolderGioHang extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView txtNameOrder,txtPriceOrder;
        ImageView imgOrder;
        ElegantNumberButton number_btn;
        CardView cardView;


        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);
            txtNameOrder=itemView.findViewById(R.id.txtNameOrder);
            txtPriceOrder=itemView.findViewById(R.id.txtPriceOrder);
            imgOrder=itemView.findViewById(R.id.imgOrder);
            number_btn=itemView.findViewById(R.id.number_btn);
            cardView=itemView.findViewById(R.id.cvCart);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }


        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(),view);
            return false;
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        AdapterGioHang.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);

    }
    @NonNull
    @Override
    public ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parents, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_gio_hang,parents,false);
        return new ViewHolderGioHang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGioHang holder, final int position) {
        holder.number_btn.setNumber(sanPhamList.get(position).getSoLuong()+"");
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        long Price= Long.parseLong(sanPhamList.get(position).getGiaSp());
        holder.txtPriceOrder.setText(decimalFormat.format(Price)+" Đ");
        holder.txtNameOrder.setText(sanPhamList.get(position).getTenSp());
        Picasso.with(context).load(sanPhamList.get(position).getHinhSp()).into(holder.imgOrder);

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
