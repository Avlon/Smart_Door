package com.example.zhao.smart_doors.Internet;

import android.util.Log;

/**
 * Created by zhao on 2016/4/19.
 */
public class TestHttpThread extends HttpThread{
    String pwd="123456";
    @Override
    public void HttpPost(){
        try{
            super.josn_str.put("pwd",pwd);
            super.HttpConnect();
        }catch (Exception e){
            Log.e("1",e.toString());
        }
    }
    @Override
    public void run(){
        super.run();
    }
}
