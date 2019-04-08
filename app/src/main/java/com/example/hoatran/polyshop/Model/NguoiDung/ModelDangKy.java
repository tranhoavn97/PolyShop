package com.example.hoatran.polyshop.Model.NguoiDung;

import android.util.Log;

import com.example.hoatran.polyshop.Api.DownLoadJson;
import com.example.hoatran.polyshop.Api.Server;
import com.example.hoatran.polyshop.Object.NguoiDung;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {

    public Boolean RegisterMember(NguoiDung user)
    {
        boolean check=false;
        String DuongDan= Server.ServerName;

        List<HashMap<String,String>> list = new ArrayList<>();
        //tạo tham số;
        HashMap<String,String> myFunction = new HashMap<>();
        myFunction.put("myFunction","SignUp");

        HashMap<String,String> hsTaiKhoan = new HashMap<>();
        hsTaiKhoan.put("TaiKhoan",user.getTaiKhoan());
        HashMap<String,String> hsTenDayDu = new HashMap<>();
        hsTenDayDu.put("TenDayDu",user.getHoTenDayDu());
        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("MatKhau",user.getMatKhau());
        HashMap<String,String> hsSDT = new HashMap<>();
        hsSDT.put("SDT",user.getSDT());

        list.add(myFunction);
        list.add(hsTaiKhoan);
        list.add(hsTenDayDu);
        list.add(hsMatKhau);
        list.add(hsSDT);


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
}
