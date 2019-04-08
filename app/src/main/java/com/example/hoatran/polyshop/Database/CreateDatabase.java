package com.example.hoatran.polyshop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String TB_ORDER="ORDERCART";

    public static final String TB_ORDER_ID="ID";
    public static final String TB_ORDER_PRODUCTID="PRODUCTID";
    public static final String TB_ORDER_PRODUCTNAME="PRODUCTNAME";
    public static final String TB_ORDER_PRODUCT_USER="USER";
    public static final String TB_ORDER_QUALITY="QUALITY";
    public static final String TB_ORDER_PRICE="PRICE";
    public static final String TB_ORDER_IMAGE="IMAGE";

    public CreateDatabase(Context context) {
        super(context, "DB1", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_Order = "CREATE TABLE " + TB_ORDER + " ( " + TB_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ORDER_PRODUCT_USER + " TEXT,"
                + TB_ORDER_PRODUCTNAME + " TEXT, " + TB_ORDER_PRODUCTID + " TEXT, " + TB_ORDER_QUALITY + " TEXT, "
                + TB_ORDER_PRICE + " TEXT, " + TB_ORDER_IMAGE + " TEXT ) ";
        db.execSQL(tb_Order);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase Open(){
        return this.getWritableDatabase();
    }
}
