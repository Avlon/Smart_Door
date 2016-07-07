package com.example.zhao.smart_doors.Internet;

import android.util.Log;

/**
 * Created by zhao on 2016/4/13.
 */
public class LoginHttpThread extends HttpThread{
    private String userId,pwa;
    public void setDate(String userId,String pwa){
        this.pwa=pwa;
        this.userId=userId;
    }
    @Override
    public void HttpPost(){
        try {
            super.josn_str.put("userId",userId);
            //Log.e("1","1 "+userId.toString());
            super.josn_str.put("userpwd",pwa);
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
