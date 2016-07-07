package com.example.zhao.smart_doors.Internet;

import android.util.Log;

/**
 * Created by zhao on 2016/4/18.
 */
public class RegisterHttpThread extends HttpThread{
    private String userId,pwa,lock_pwa;
    public void setDate(String userId,String pwa,String lock_pwa){
        this.pwa=pwa;
        this.userId=userId;
        this.lock_pwa=lock_pwa;
    }
    @Override
    public void HttpPost(){
        try{
            super.josn_str.put("userId",userId);
            super.josn_str.put("userpwd",pwa);
            super.josn_str.put("pwd",lock_pwa);
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
