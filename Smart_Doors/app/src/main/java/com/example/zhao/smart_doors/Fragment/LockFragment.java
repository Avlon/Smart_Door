package com.example.zhao.smart_doors.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhao.smart_doors.Activity.MainActivity;
import com.example.zhao.smart_doors.Internet.OpenHttpThread;
import com.example.zhao.smart_doors.R;

/**
 * Created by zhao on 2016/4/18.
 */
public class LockFragment extends Fragment{
    private Button open_lock;
    private EditText pwd;
    private OpenHttpThread openHttpThread=new OpenHttpThread();
    public class  TestHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            Log.e("1", "get_date");
            if(msg.what==1){
                //Toast.makeText(LockFragment.this,"开锁成功",Toast.LENGTH_SHORT).show();
                /*intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);*/
            }
            else{
                Log.e("1","失败");
            }
            super.handleMessage(msg);
        }
    }
    private TestHandler testHandler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_clock, container, false);

        pwd=(EditText)view.findViewById(R.id.open_pwd);

        open_lock=(Button)view.findViewById(R.id.open_lock);

        testHandler=new TestHandler();

        openHttpThread.setTraget("http://192.168.200.102:8000/lock/home/pwdlock/");
        openHttpThread.setHandler(testHandler);
        open_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHttpThread.setDate(pwd.getText().toString());
                openHttpThread.start();
            }
        });

        return view;
    }
}
