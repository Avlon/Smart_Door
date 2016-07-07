package com.example.zhao.smart_doors.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.zhao.smart_doors.R;

/**
 * Created by zhao on 2016/4/12.
 */
public class WelcomeActivity extends AppCompatActivity implements Runnable{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Thread(this).start();

    }
    @Override
    public void run(){
        try{
            Thread.sleep(2000);
            Intent intent=new Intent(WelcomeActivity.this,LoginAcivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            Log.e("1",e.toString());
        }
    }
}
