package com.example.hoatran.polyshop.Api;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

public class Server {


    public  static String localhost="192.168.43.230";

    public final static String ServerName="http://"+ localhost +"/PolyShop/function.php";

    public final static String Serverlink="http://"+ localhost +"/GGShop";

    public static final String UPLOAD_URL = "http://"+ localhost +"/GGShop/UploadAvatar.php";

    public static final String getTimeCurrent()
    {
        Locale locale=new Locale("vi","VN");

        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        Currency currency=Currency.getInstance(locale);

        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int domAdd=dom+1;
        String dayofweek=cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
        String currentDate=dayofweek + "- " +domAdd +"/" +month + "/" + year;

        return currentDate;
    }

}
