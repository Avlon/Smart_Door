package com.example.zhao.smart_doors.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhao.smart_doors.Internet.RegisterHttpThread;
import com.example.zhao.smart_doors.R;

import java.util.List;

/**
 * Created by zhao on 2016/4/19.
 */
public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    private Button camera_but;
    private Camera camera;
    private boolean isPreview=false;
    private SurfaceView sv=null;
    private SurfaceHolder sh=null;
    private Bundle bundle;
    private RegisterHttpThread registerHttpThread=new RegisterHttpThread();
    public class  TestHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            Log.e("1", "get_date");
            if(msg.what==1){
                Intent intent=new Intent(CameraActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(CameraActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                Log.e("1", "失败");
            }
            super.handleMessage(msg);
        }
    }
    private TestHandler testHandler=new TestHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        registerHttpThread.setDate(bundle.getString("userId"),bundle.getString("userpwd"),bundle.getString("pwd"));
        initView();
    }
    private void initView() {
        camera_but = (Button) findViewById(R.id.camera_but);

        camera_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerHttpThread.setTraget("http://192.168.200.102:8000/lock/home/newuser/");
                registerHttpThread.setHandler(testHandler);
                registerHttpThread.start();
            }
        });
        initSurface();
    }
    private void initSurface(){
        sv=(SurfaceView)findViewById(R.id.surface);
        sh= sv.getHolder();
        sh.addCallback(CameraActivity.this);
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Log.e("1", "inCreated");
        camera= android.hardware.Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        try{
            camera.setPreviewDisplay(sh);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height){
        Log.e("1", "inChanged");
        initCamrea();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        Log.e("1", "inDestroy");
        if(camera!=null){
            camera.setPreviewCallback(null);
            camera.stopPreview();
            isPreview=false;
            camera.release();
            camera=null;
        }
    }
    public void initCamrea(){
        if(isPreview){
            Log.e("1","stop");
            camera.stopPreview();
        }
        if(camera!=null){
            Log.e("1","start");
            try{
                android.hardware.Camera.Parameters parameters=camera.getParameters();
                parameters.setPictureFormat(PixelFormat.JPEG);
                parameters.setPreviewFormat(PixelFormat.YCbCr_420_SP);
                List<Camera.Size> pictureSizes = camera.getParameters().getSupportedPictureSizes();
                List<Camera.Size> previewSizes = camera.getParameters().getSupportedPreviewSizes();
                List<Integer> previewFormats =camera.getParameters().getSupportedPreviewFormats();
                List<Integer> previewFrameRates = camera.getParameters().getSupportedPreviewFrameRates();
                Camera.Size psize = null;
                for (int i = 0; i < pictureSizes.size(); i++) {
                    psize = pictureSizes.get(i);
                    //  Log.e("1","1hi"+psize.height+"1wight"+psize.width);
                }
                for (int i = 0; i < previewSizes.size(); i++) {
                    psize = previewSizes.get(i);
                    // Log.e("1","2hi"+psize.height+"2wight"+psize.width);
                }
                Integer pf = null;
                for (int i = 0; i < previewFormats.size(); i++) {
                    pf = previewFormats.get(i);
                }
                try {
                    parameters.setPictureSize(640, 480); //指定拍照图片的大小
                    parameters.setPreviewSize(psize.width, psize.height); // 指定preview的大小
                }catch (Exception e){
                    Log.e("1",e.toString());
                }
                if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                    parameters.set("orientation", "portrait"); //
                    parameters.set("rotation", 90); // 镜头角度转90度（默认摄像头是横拍）
                    camera.setDisplayOrientation(90); // 在2.2以上可以使用
                }
                else{// 如果是横屏
                    parameters.set("orientation", "landscape"); //
                    camera.setDisplayOrientation(0); // 在2.2以上可以使用
                }
                //camera.setPreviewCallback(mJpegPreviewCallback);
                camera.setParameters(parameters); // 将Camera.Parameters设定予Camera
                camera.startPreview(); // 打开预览画面
                isPreview = true;
            }catch (Exception e){
                Log.e("1",e.toString() );
            }
        }
    }
}
