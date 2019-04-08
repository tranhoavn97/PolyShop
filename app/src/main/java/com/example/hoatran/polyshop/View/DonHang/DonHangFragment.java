package com.example.hoatran.polyshop.View.DonHang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoatran.polyshop.Adapters.AdapterDonHang;
import com.example.hoatran.polyshop.Model.DonHang.ModelDonHang;
import com.example.hoatran.polyshop.Object.DonHang;
import com.example.hoatran.polyshop.R;
import com.example.hoatran.polyshop.View.User.SignInActivity;

import java.util.ArrayList;
import java.util.List;


public class DonHangFragment extends Fragment {
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_don_hang, container, false);
        recyclerView=view.findViewById(R.id.rvDH);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<DonHang> list=new ArrayList<>();
        list=new ModelDonHang().LoadDonHang(SignInActivity.IdUser);
        AdapterDonHang adapterDonHang=new AdapterDonHang(list,getContext());
        recyclerView.setAdapter(adapterDonHang);
        adapterDonHang.notifyDataSetChanged();
        return view;
    }
}
