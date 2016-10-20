package com.example.zw.plane;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    private Rocker rocker1,rocker2;
    private float distance1,distance2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rocker1=(Rocker)findViewById(R.id.rocker1);
        rocker2=(Rocker)findViewById(R.id.rocker2);
        rocker1.post(new Runnable() {
            @Override
            public void run() {
               rocker1.setLeft_Top(rocker1.getX(),rocker1.getTop());
            }
        });
         rocker2.post(new Runnable() {
             @Override
             public void run() {
                 rocker2.setLeft_Top(rocker2.getX(),rocker2.getTop());
             }
         });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    distance1=rocker1.Getdistance();
                    distance2=rocker2.Getdistance();
                    Log.v("distance1",String.valueOf(distance1));
                    Log.v("distance2",String.valueOf(distance2));
                }
            }
        });
    }

}
