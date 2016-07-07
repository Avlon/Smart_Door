package com.example.zhao.smart_doors.Internet;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhao on 2016/4/13.
 */
public class HttpThread extends Thread{
    private URL traget_url;
    private String result,traget,inline,outline;
    public JSONObject josn_str=new JSONObject();;
    private int code;
    public Handler handler;
    @Override
    public void run(){
        try{
            HttpPost();
            if(code==201){
                Message msg=handler.obtainMessage();
                msg.what=1;                     //1表示成功
                msg.obj=result;
                handler.sendMessage(msg);
            }
            else {
                Message msg=handler.obtainMessage();
                msg.what=0;
                handler.sendMessage(msg);
            }
        }catch (Exception e){
            Log.e("1", e.toString());
        }
    }
    public void HttpPost(){
    }
    public void setHandler(Handler fa_handler){
        this.handler=fa_handler;
    }
    public void setTraget(String traget){
        this.traget=traget;
    }
    public void HttpConnect(){
        try{
            outline=josn_str.toString();
            traget_url=new URL(traget);
            HttpURLConnection urlcon=(HttpURLConnection)traget_url.openConnection();
            urlcon.setRequestMethod("POST");
            urlcon.setDoInput(true);
            urlcon.setDoOutput(true);
            urlcon.setUseCaches(false);
            urlcon.setInstanceFollowRedirects(true);
            urlcon.setRequestProperty("Content-Type", "application/json");
            urlcon.connect();
            OutputStreamWriter out=new OutputStreamWriter(urlcon.getOutputStream());
            Log.e("1", outline.toString());
            out.write(outline,0,outline.length());
            out.flush();
            code=urlcon.getResponseCode();
            Log.e("1",Integer.toString(code));
            out.close();
            InputStreamReader in=new InputStreamReader(urlcon.getInputStream());
            BufferedReader read=new BufferedReader(in);
            inline=null;
            while((inline=read.readLine())!=null){
                result+=inline+"\n";
            }
            Log.e("1", result);
            result=result.subSequence(4,result.length()).toString();
            in.close();
            urlcon.disconnect();
        }catch (Exception e){
            Log.e("1",e.toString());
        }
    }
}
