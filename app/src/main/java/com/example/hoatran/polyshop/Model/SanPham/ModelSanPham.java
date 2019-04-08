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

public class ModelSanPham {

    public List<SanPham> LoadListProduct(int MaLoai)
    {
        List<SanPham> listdata=new ArrayList<>();

        List<HashMap<String,String>> attrs=new ArrayList<>();
        String dataJSON="";
        //String duongdan= Server.ServerName;

        HashMap<String, String> hsFunction=new HashMap<>();
        hsFunction.put("myFunction","LoadSanPhamTheoMaLoai");

        HashMap<String, String> hsMaloai=new HashMap<>();
        hsMaloai.put("MaLoaiSp", String.valueOf(MaLoai));
        attrs.add(hsFunction);
        attrs.add(hsMaloai);
        DownLoadJson downLoadJson=new DownLoadJson(Server.ServerName,attrs);
        Log.d("tab",downLoadJson.toString());
        //end phương thức post

        downLoadJson.execute();

        try {
            dataJSON=downLoadJson.get();
            Log.d("kt",dataJSON+"");

            JSONObject jsonObject=new JSONObject(dataJSON);


            JSONArray jsonArrayDanhSachSanPham=jsonObject.getJSONArray("DSSanPham");

            int count=jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {
                SanPham products=new SanPham();
                JSONObject object=jsonArrayDanhSachSanPham.getJSONObject(i);
                products.setGiaSp(object.getString("GiaTien"));
                products.setMasp(object.getInt("MaSp"));
                products.setHinhSp(object.getString("HinhSP"));


                products.setTenSp(object.getString("TenSP"));
                listdata.add(products);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("check1",listdata.size()+"");
        return listdata;
    }

}
