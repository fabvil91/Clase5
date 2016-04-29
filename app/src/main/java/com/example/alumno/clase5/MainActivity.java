package com.example.alumno.clase5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback{
   // Thread hilo = null;
    TextView tv = null;
    ImageView iv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);

        Handler h = new Handler(this);
        Thread hilo = new Thread(new MiHilo(h,"http://192.168.2.130:8080/android/clase6.xml",true));

        Thread hilo2 = new Thread(new MiHilo(h,"http://192.168.2.130:8080/android/koala.png",false));

        hilo.start();
        hilo2.start();
    }

    @Override
    protected void onStop(){
        Log.d("MainActivity", "onStop");
  //      hilo.interrupt();//o abajo de tdo el metodo?
        super.onStop();
    }

    @Override
    public boolean handleMessage(Message msg) {
        Object o = null;
        if(msg.arg1 == 1){
            Log.d("MainActivity","Es texto");
            this.tv.setText(msg.obj.toString());
        }else if(msg.arg1 == 2){
            Log.d("MainActivity","No es texto");
            byte[] array = (byte[])msg.obj;
            Bitmap bmp = BitmapFactory.decodeByteArray(array, 0, array.length);
            this.iv.setImageBitmap(bmp);
        }


        return false;
    }
}
