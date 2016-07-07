package com.example.zhao.smart_doors.Internet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.zhao.smart_doors.Activity.LoginAcivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhao on 2016/4/18.
 */
public class GetHttpThread extends Thread{
    private String traget="http://192.168.200.1:8080/?action=snapshot";
    private URL traget_url;
    private Handler handler;
    private int code;
    private Bitmap bitmap;
    @Override
    public void run(){
        HttpGet();
        if(code==200){
            Message msg=handler.obtainMessage();
            msg.what=code;
            msg.obj=bitmap;
            handler.sendMessage(msg);
        }
        else{
            Message msg=handler.obtainMessage();
            msg.what=0;
            handler.sendMessage(msg);
        }
    }
    public void setHandler(Handler handler){
        this.handler=handler;
    }
    private void HttpGet(){
        try{
            traget_url=new URL(traget);
            HttpURLConnection urlcon=(HttpURLConnection)traget_url.openConnection();
            urlcon.setRequestMethod("GET");
            urlcon.setConnectTimeout(1000);
            urlcon.setUseCaches(false);
            code=urlcon.getResponseCode();
            if(code==200){
                bitmap= BitmapFactory.decodeStream(urlcon.getInputStream());
            }
        }catch (Exception e){
            Log.e("1",e.toString());
        }
    }
}
