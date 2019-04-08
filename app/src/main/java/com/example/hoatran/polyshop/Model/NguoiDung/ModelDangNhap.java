package com.example.hoatran.polyshop.Model.NguoiDung;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.hoatran.polyshop.Api.DownLoadJson;
import com.example.hoatran.polyshop.Api.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {

    public int getpreferencesLogin(Context context)
    {
        SharedPreferences preferencesLogin = context.getSharedPreferences("login",context.MODE_PRIVATE);
        int id=preferencesLogin.getInt("Id",0);
        return id;
    }
    public boolean CheckLogin(Context context,String TenDn, String Pwd)
    {
        boolean check=false;

        List<HashMap<String, String >> attrs=new ArrayList<>();

        HashMap<String, String> hsFunction=new HashMap<>();
        hsFunction.put("myFunction","SignIn");

        HashMap<String, String> hsTenDN=new HashMap<>();
        hsTenDN.put("TaiKhoan",TenDn);

        HashMap<String, String> hsPwd=new HashMap<>();
        hsPwd.put("MatKhau", Pwd);

        attrs.add(hsFunction);
        attrs.add(hsTenDN);
        attrs.add(hsPwd);


        DownLoadJson downLoadJson=new DownLoadJson(Server.ServerName,attrs);

        downLoadJson.execute();
        //parse json
        try {
            String data=downLoadJson.get();



            JSONObject jsonObject=new JSONObject(data);
//            Log.d("ok",jsonObject+"");
//            Log.d("ok",data+"");
            String jsResult=jsonObject.getString("ketqua");


            if(jsResult.equals("true"))
            {
                check=true;
                String tenNV=jsonObject.getString("TenDayDu");
                String sdt=jsonObject.getString("SDT");
                //String avatar=jsonObject.getString("avatar");
                int id=jsonObject.getInt("Id");
                SharedPreferences preferencesLogin = context.getSharedPreferences("login",context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencesLogin.edit();
                editor.putString("TenDayDu",tenNV);
                editor.putString("SDT",sdt);
                //editor.putString("avatar",avatar);
                editor.putInt("Id",id);
                editor.commit();
            }
            else
            {
                check=false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("ok",check+"");
        return check;
    }
}
