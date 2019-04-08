package com.example.hoatran.polyshop.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hoatran.polyshop.Object.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DataOrder {

    SQLiteDatabase database;
    String TB="TAB";
    public DataOrder(Context context)
    {
        CreateDatabase createDatabase=new CreateDatabase(context);
        database=createDatabase.Open();
    }
    public long AddToCart(SanPham sanPham)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(CreateDatabase.TB_ORDER_PRODUCT_USER,sanPham.getIdUser());
        contentValues.put(CreateDatabase.TB_ORDER_PRODUCTID,sanPham.getIdOrder());
        contentValues.put(CreateDatabase.TB_ORDER_PRODUCTNAME,sanPham.getTenSp());
        contentValues.put(CreateDatabase.TB_ORDER_PRICE,sanPham.getGiaSp());
        contentValues.put(CreateDatabase.TB_ORDER_QUALITY,sanPham.getSoLuong());
        contentValues.put(CreateDatabase.TB_ORDER_IMAGE,sanPham.getHinhSp());
        long check=database.insertWithOnConflict(CreateDatabase.TB_ORDER,null,contentValues, database.CONFLICT_REPLACE);
        Log.d(TB, String.valueOf(check));
        return check;
    }

    public List<SanPham> getCart(int username){

        List<SanPham> list=new ArrayList<>();

        String TruyVan=" SELECT * FROM " + CreateDatabase.TB_ORDER + " WHERE " + CreateDatabase.TB_ORDER_PRODUCT_USER + " = '" +username + "'";
        Log.d(TB, "getCart: " + TruyVan);
        Cursor cursor=database.rawQuery(TruyVan,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            SanPham order=new SanPham();
            order.setMasp(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ORDER_ID)));
            order.setIdUser(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ORDER_PRODUCT_USER)));
            order.setIdOrder(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ORDER_PRODUCTID)));
            order.setTenSp(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ORDER_PRODUCTNAME)));
            order.setHinhSp(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ORDER_IMAGE)));
            order.setSoLuong(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ORDER_QUALITY)));
            order.setGiaSp(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ORDER_PRICE)));
            list.add(order);
            cursor.moveToNext();
        }
        Log.d("checkNao", String.valueOf(list));
        return list;
    }

    public boolean CleanCart(int username){

        database.delete(CreateDatabase.TB_ORDER,CreateDatabase.TB_ORDER_PRODUCT_USER + " = '" + username + "'",null);
        return true;
    }

    public boolean removeFromCart(int productId, int username) {
        long check;
        check=database.delete( CreateDatabase.TB_ORDER,CreateDatabase.TB_ORDER_ID + " = '" + productId + "' AND " + CreateDatabase.TB_ORDER_PRODUCT_USER + " = '" + username + "'",null);
        if(check!=0)
        {
            Log.d("tab", String.valueOf(check));
            return true;

        }
        else
        {
            return true;
        }
//        String Truyvan= "DELETE FROM " + CreateDatabase.TB_ORDER + " WHERE " + CreateDatabase.TB_ORDER_PRODUCT_USER + " = '"
//                +productId + "' AND " + CreateDatabase.TB_ORDER_ID + " = '" + username + "'";

    }
}
