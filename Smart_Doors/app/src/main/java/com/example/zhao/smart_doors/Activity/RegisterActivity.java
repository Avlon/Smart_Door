package com.example.zhao.smart_doors.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zhao.smart_doors.Internet.LoginHttpThread;
import com.example.zhao.smart_doors.R;

/**
 * Created by zhao on 2016/4/12.
 */
public class RegisterActivity extends AppCompatActivity{
    private Button register;
    private ImageButton rem_pwa_but;
    private EditText pwa,userId;
    private boolean isRem;
    private LoginHttpThread loginHttpThread;
    private Intent intent;
    public class  TestHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            Log.e("1", "get_date");
            if(msg.what==1){
                /*intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);*/
            }
            else{
                Log.e("1","失败");
            }
            super.handleMessage(msg);
        }
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    private void initView(){
        register=(Button)findViewById(R.id.register_but);

        pwa=(EditText)findViewById(R.id.register_pwa);
        userId=(EditText)findViewById(R.id.register_id);

        rem_pwa_but=(ImageButton)findViewById(R.id.rem_pwa_but);

        isRem=false;

        loginHttpThread=new LoginHttpThread();

        rem_pwa_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRem) {
                    rem_pwa_but.setBackground(getResources().getDrawable(R.drawable.register_rem_yes));
                }
                else{
                    rem_pwa_but.setBackground(getResources().getDrawable(R.drawable.register_rem_no));
                }
            }
        });
        final TestHandler testHandler=new TestHandler();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,RegisterLockActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("userId",userId.getText().toString());
                bundle.putString("userpwd",pwa.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                /*loginHttpThread.setDate(lock_text,pwa.getText().toString());
                loginHttpThread.setTraget("http://192.168.1.114:8000/lock/home/newuser");
                loginHttpThread.setHandler(testHandler);
                loginHttpThread.start();*/
            }
        });
    }
}
