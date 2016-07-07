package com.example.zhao.smart_doors.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhao.smart_doors.Internet.GetHttpThread;
import com.example.zhao.smart_doors.R;

/**
 * Created by zhao on 2016/4/18.
 */
public class MonitorFragment extends Fragment{
    private boolean isOpen=false;
    private ImageView monitor;
    private ImageButton button;
    private Handler handler;
    private GetHttpThread getHttpThread;
    private class WorkThread extends Thread{
        private void delay(int a){
            for(int i=0;i<a;i++){
                for(int j=0;j<10000;j++){

                }
            }
        }
        private volatile boolean isWorl=false;
        @Override
        public void run(){
            while(!isWorl){
                try{
                    delay(33);
                    getHttpThread.run();
                }catch (Exception e){
                    Log.e("1",e.toString());
                    Log.e("1","error");
                }
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity__main_monitor, container, false);

        monitor=(ImageView)view.findViewById(R.id.monitor);
        button=(ImageButton)view.findViewById(R.id.open_monitor);

        handler=new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==200){
                    monitor.setImageBitmap((Bitmap)msg.obj);
                }
                else{
                    Log.e("1","can not");
                }
            }
        };
        getHttpThread=new GetHttpThread();
        getHttpThread.setHandler(handler);

        final WorkThread workThread=new WorkThread();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isOpen) {
                        workThread.isWorl = true;
                        isOpen=false;
                    } else {
                        workThread.isWorl = false;
                        isOpen=true;
                        workThread.start();
                    }
                }catch (Exception e){
                    Log.e("1",e.toString());
                }
            }
        });
        return view;
    }

}
