package com.example.hoatran.polyshop.Model.SanPham;

import android.util.Log;

import com.example.hoatran.polyshop.Api.DownLoadJson;
import com.example.hoatran.polyshop.Api.Server;
import com.example.hoatran.polyshop.Object.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {

    public SanPham LoadDetailProduct(int MaLoai)
    {
        //List<Products> listdata=new ArrayList<>();
        SanPham products=new SanPham();
        List<HashMap<String,String>> attrs=new ArrayList<>();
        String dataJSON="";
        //String duongdan= Server.ServerName;

        HashMap<String, String> hsFunction=new HashMap<>();
        hsFunction.put("myFunction","ChiTietSanPham");

        HashMap<String, String> hsMaloai=new HashMap<>();
        hsMaloai.put("masp", String.valueOf(MaLoai));
        attrs.add(hsFunction);
        attrs.add(hsMaloai);
        DownLoadJson downLoadJson=new DownLoadJson(Server.ServerName,attrs);
        Log.d("tab",downLoadJson.toString());
        //end phương thức post

        downLoadJson.execute();

        try {
            dataJSON=downLoadJson.get();


            JSONObject jsonObject=new JSONObject(dataJSON);


            JSONArray jsonArrayDanhSachSanPham=jsonObject.getJSONArray("DetailProduct");

            int count=jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {

                JSONObject object=jsonArrayDanhSachSanPham.getJSONObject(i);
                products.setGiaSp(object.getString("GiaTien"));
                products.setMasp(object.getInt("MaSp"));
                //products.setHinhlon(object.getString("HINHLON"));
                products.setHinhSp(object.getString("HinhSP"));
                //products.setSoluong(object.getInt("SOLUONG"));
                products.setMoTa(object.getString("MoTa"));
                products.setTenSp(object.getString("TenSP"));


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("check1",products+"");
        return products;
    }
}
