package com.example.hoatran.polyshop.Api;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownLoadJson extends AsyncTask<String,Void,String> {
    String duongdan;
    List<HashMap<String,String>> attrs;
    StringBuilder data;
    public DownLoadJson(String duongdan) {
        this.duongdan = duongdan;
    }

    public DownLoadJson(String duongdan, List<HashMap<String, String>> attrs) {
        this.duongdan = duongdan;
        this.attrs = attrs;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(duongdan);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if(attrs.size() !=0)
            {
                methodPost(httpURLConnection);
            }
            else
            {
                methodGet(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("data",data.toString());

        return data.toString();
    }
    private String methodGet(HttpURLConnection httpURLConnection)
    {
        String data1 = "";
        InputStream inputStream= null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            data = new StringBuilder();
            String line="";
            while ((line = bufferedReader.readLine()) !=null)
            {
                data.append(line);
            }
            data1=data.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data1;
    }

    private String methodPost(HttpURLConnection httpURLConnection)
    {
        String data1="";
        String key="";
        String value="";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            Uri.Builder builder=new Uri.Builder();
            int count=attrs.size();

            for (int i = 0; i <count ; i++) {
                // duyet hasmap
                for (Map.Entry<String,String> values : attrs.get(i).entrySet())
                {
                    key=values.getKey();
                    value=values.getValue();
                }
                builder.appendQueryParameter(key,value);
            }
            String query=builder.build().getEncodedQuery();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter streamWriter =new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);
            writer.write(query);
            writer.flush();
            // xử lý thành công đóng các luồng lại
            writer.close();
            streamWriter.close();
            outputStream.close();

            data1=methodGet(httpURLConnection);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data1;
    }
}
