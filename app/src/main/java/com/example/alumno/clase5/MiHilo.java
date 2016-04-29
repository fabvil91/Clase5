package com.example.alumno.clase5;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by alumno on 28/04/2016.
 */
public class MiHilo implements Runnable {
    private Handler handler;
    private String url;
    private Boolean esTexto;
    private HttpManager httpManager;

    public MiHilo(Handler h, String url, Boolean esTexto){
        this.handler = h;
        this.url = url;
        this.esTexto = esTexto;
        httpManager = new HttpManager(url);
    }

    @Override
    public void run() {
        Log.d("MiHilo","run");
        Message msg = new Message();
        if(this.esTexto) {
            Log.d("MiHilo:","es Texto");
            String texto = null;
            try {
                texto = httpManager.getStrDataByGET();
                msg.arg1 = 1;
                msg.obj = texto;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("Texto devuelto:", texto);
        }else{
            Log.d("MiHilo:","no es Texto");
            try {
                byte[] arrayImg = httpManager.getBytesDataByGET();
                msg.arg1 = 2;
                msg.obj = arrayImg;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        handler.sendMessage(msg);
      //  TextView tv = (TextView)this.activity.findViewById(R.id.textView);
      //  tv.setText("Hilo");

      //  if(Thread.interrupted()){
            //Porque termina rapido no entra?
      //      Log.d("MiHilo","interrupted");
      //      return;
      //  }
    }
}
