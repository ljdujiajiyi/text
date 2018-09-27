package com.example.dell_pc.qinyongcongyuekao.utif;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * 作者：秦永聪
 *日期：2018/9/25
 * */public class Heple {
     public Heple(){};
     public Heple get(final String wz){
         new Thread(){
             @Override
             public void run() {
                 super.run();
                 try {
                     URL url=new URL(wz);
                     HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                     connection.setRequestMethod("GET");
                     int code = connection.getResponseCode();
                     Log.d("tag","code");
                     if(code==200){

                         Message message = Message.obtain();
                         InputStream inputStream = connection.getInputStream();
                         String s = getinputstream(inputStream);
                         message.obj=s;
                         handler.sendMessage(message);
                     }
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }.start();
       return this;
     };
     //工具类
    public String getinputstream(InputStream is) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int cont=-1;
        while((cont=is.read(buffer))!=-1){
            stream.write(buffer,0,cont);
            stream.flush();
        }
        return stream.toString();
    }
    //handel
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String mm= (String) msg.obj;
            cr.success(mm);
        }
    };
    public interface Cr{
        void success(String mm);
    }
    public Cr cr;
    public void Hd(Cr cr){
        this.cr=cr;
    }
}
