package com.example.zhao.smart_doors.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhao.smart_doors.R;

/**
 * Created by zhao on 2016/4/20.
 */
public class RegisterLockActivity extends AppCompatActivity{
    private TextView head_text;
    private EditText lock_pwa;
    private Button register_lock_but;
    private Bundle bundle;
    protected void onCreate(Bundle savedInstanceState){
        Intent intent=getIntent();
        bundle=new Bundle();
        bundle=intent.getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_clock);
        initView();
    }
    private void initView(){
        head_text=(TextView)findViewById(R.id.lock_text);

        lock_pwa=(EditText)findViewById(R.id.open_pwd);

        register_lock_but=(Button)findViewById(R.id.open_lock);

        head_text.setText("开锁密码注册");
        register_lock_but.setText("注册");

        register_lock_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("pwd",lock_pwa.getText().toString());
                Intent intent=new Intent(RegisterLockActivity.this,CameraActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
