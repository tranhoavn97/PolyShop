package com.example.hoatran.polyshop.Model.DonHang;

import android.util.Log;

import com.example.hoatran.polyshop.Api.DownLoadJson;
import com.example.hoatran.polyshop.Api.Server;
import com.example.hoatran.polyshop.Object.DonHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDonHang {

    public Boolean ThemDonHang(DonHang donHang)
    {
        boolean check=false;
        String DuongDan= Server.ServerName;

        List<HashMap<String,String>> list = new ArrayList<>();
        //tạo tham số;
        HashMap<String,String> myFunction = new HashMap<>();
        myFunction.put("myFunction","ThemDonHang");

        HashMap<String,String> hsDiaChiGiao = new HashMap<>();
        hsDiaChiGiao.put("DiaChiGiao",donHang.getDiaChiGiao());

        HashMap<String,String> hsSDT = new HashMap<>();
        hsSDT.put("SDT",donHang.getSdt());

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("TenNguoiNhan",donHang.getTenNguoiNhan());

        HashMap<String,String> hsMaUser = new HashMap<>();
        hsMaUser.put("MaUser", String.valueOf(donHang.getMaUser()));

        list.add(myFunction);
        list.add(hsDiaChiGiao);
        list.add(hsSDT);
        list.add(hsTenNguoiNhan);
        list.add(hsMaUser);

        DownLoadJson downLoadJson = new DownLoadJson(DuongDan,list);
        downLoadJson.execute();
        try {
            String dataJSON=downLoadJson.get();
            Log.d("kt",dataJSON);
            // parse về jsonobjec
            JSONObject jsonObject=new JSONObject(dataJSON);
            String dataResult=jsonObject.getString("ketqua");
            if(dataResult.equals("true"))
            {
                check= true;
            }
            else
            {
                check= false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return check;
    }

    public List<DonHang> LoadDonHang(int MaNV)
    {
        List<DonHang> listdata=new ArrayList<>();
        //Products products=new Products();
        List<HashMap<String,String>> attrs=new ArrayList<>();
        String dataJSON="";
        //String duongdan= Server.ServerName;

        HashMap<String, String> hsFunction=new HashMap<>();
        hsFunction.put("myFunction","LayDSDonHangTheoMaNV");

        HashMap<String, String> hsMaloai=new HashMap<>();
        hsMaloai.put("MaUser", String.valueOf(MaNV));
        attrs.add(hsFunction);
        attrs.add(hsMaloai);
        DownLoadJson downLoadJson=new DownLoadJson(Server.ServerName,attrs);
        Log.d("tab",downLoadJson.toString());
        //end phương thức post

        downLoadJson.execute();

        try {
            dataJSON=downLoadJson.get();


            JSONObject jsonObject=new JSONObject(dataJSON);


            JSONArray jsonArrayDanhSachSanPham=jsonObject.getJSONArray("DanhSachDonDatHang");

            int count=jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {
                DonHang request=new DonHang();
                JSONObject object=jsonArrayDanhSachSanPham.getJSONObject(i);
                request.setId(object.getInt("MaDH"));
                request.setSdt(object.getString("SDT"));
                request.setTenNguoiNhan(object.getString("TenNguoiNhan"));
                request.setTrangThai(object.getString("TrangThai"));
                request.setNgayMua(object.getString("NgayMua"));
                request.setDiaChiGiao(object.getString("DiaChiGiao"));
                listdata.add(request);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listdata;
    }
}
