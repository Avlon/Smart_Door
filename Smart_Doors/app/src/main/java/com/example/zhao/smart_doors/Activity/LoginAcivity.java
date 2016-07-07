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
import android.widget.TextView;

import com.example.zhao.smart_doors.Internet.LoginHttpThread;
import com.example.zhao.smart_doors.Internet.TestHttpThread;
import com.example.zhao.smart_doors.R;

/**
 * Created by zhao on 2016/4/12.
 */
public class LoginAcivity extends AppCompatActivity{
    //private TestHttpThread testHttpThread=new TestHttpThread();
    private Button int_login_but,blue_login_but;
    private TextView register,forget_pwa;
    private ImageButton rem_pwa;
    private EditText pwa,userId;
    private LoginHttpThread loginHttpThread;
    private Intent intent;
    private boolean isRem;
    public class  TestHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            Log.e("1", "get_date");
            if(msg.what==1){
                intent=new Intent(LoginAcivity.this,MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("init_date",(String)msg.obj);
                Log.e("1","login: "+(String)msg.obj);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else{
                Log.e("1","失败");
            }
            super.handleMessage(msg);
        }
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    public void initView(){
        pwa=(EditText)findViewById(R.id.login_pwa);
        userId=(EditText)findViewById(R.id.login_id);

        rem_pwa=(ImageButton)findViewById(R.id.login_rem_but);

        register=(TextView)findViewById(R.id.login_register);
        forget_pwa=(TextView)findViewById(R.id.login_forget_pwa);

        int_login_but=(Button)findViewById(R.id.internet_login_but);
        blue_login_but=(Button)findViewById(R.id.blue_login_but);

        loginHttpThread=new LoginHttpThread();

        isRem=false;

        final TestHandler testHandler=new TestHandler();

        rem_pwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRem) {
                    rem_pwa.setBackground(getResources().getDrawable(R.drawable.register_rem_yes));
                }
                else{
                    rem_pwa.setBackground(getResources().getDrawable(R.drawable.register_rem_no));
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(LoginAcivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        forget_pwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        int_login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginHttpThread.setDate(userId.getText().toString(),pwa.getText().toString());
                loginHttpThread.setTraget("http://192.168.200.102:8000/lock/home/user/");
                loginHttpThread.setHandler(testHandler);
                loginHttpThread.start();
                /*Intent intent=new Intent(LoginAcivity.this,MainActivity.class);
                startActivity(intent);*/
            }
        });

        blue_login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginAcivity.this,MainActivity.class);
                startActivity(intent);
                /*testHttpThread.setTraget("http://192.168.200.103:8000/lock/home/pwdlock/");
                testHttpThread.start();*/
            }
        });
    }
}
