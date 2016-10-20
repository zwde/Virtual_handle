package com.example.zw.plane;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by zw on 2016/9/8.
 */
public class Rocker extends FrameLayout{
    private ImageView beijing;
    private FrameLayout back;
    private ImageButton btn_an;
    private int startX,startY;
    private float CenterX,CenterY;
    private float sX,sY;
    private float left,top;
    private float distance;
    public Rocker(Context context) {
        super(context);
    }
    public float Getdistance(){
        return distance;
    }
    public void setLeft_Top(float left,float top){
        this.left=left;
        this.top=top;
    }
    public Rocker(Context context, AttributeSet attrs) {
        super(context, attrs);
       View view= LayoutInflater.from(context).inflate(R.layout.rocker_lay,this);
        beijing=(ImageView)view.findViewById(R.id.beijing);
        back=(FrameLayout)view.findViewById(R.id.back);
        btn_an=(ImageButton)view.findViewById(R.id.btn_an);

        btn_an.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CenterY=beijing.getHeight()/2+beijing.getTop();

                CenterX=beijing.getWidth()/2+beijing.getLeft();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=(int)event.getRawX();
                        startY=(int)event.getRawY();
                       // Toast.makeText(MainActivity.this, String.valueOf(beijing.getLeft()),Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int movingX=(int)event.getRawX();
                        int movingY=(int)event.getRawY();
                        int dx=movingX-startX;
                        int dy=movingY-startY;

                        sX=movingX-left-beijing.getLeft();
                        sY=movingY-top-beijing.getTop();
                        float X,Y;
                         distance=(float) Math.sqrt((sX-CenterX)*(sX-CenterX)+(sY-CenterY)*(sY-CenterY));
                        float sin=Math.abs((sY-CenterY)/distance);
                        float cos=Math.abs((sX-CenterX)/distance);
                        if(distance>=beijing.getHeight()/2){
                            //Toast.makeText(MainActivity.this,"sin:"+String.valueOf(sin)+"cos:"+String.valueOf(cos),Toast.LENGTH_SHORT).show();
                            //第一象限
                            X=cos*beijing.getWidth()/2;
                            Y=sin*beijing.getWidth()/2;
                            if(sY-CenterY<=0 && sX-CenterX>=0){
                                //Toast.makeText(MainActivity.this,"第一象限",Toast.LENGTH_SHORT).show();
                                btn_an.layout((int)CenterX+(int)X-btn_an.getWidth()/2,(int)CenterY-(int)Y-btn_an.getHeight()/2,
                                        (int)CenterX+(int)X+btn_an.getWidth()/2,(int)CenterY-(int)Y+btn_an.getHeight()/2);
                            }
                            //第二象限
                            else if(sY-CenterY<=0 && sX-CenterX<=0){
                                //Toast.makeText(MainActivity.this,"第二象限",Toast.LENGTH_SHORT).show();
                                btn_an.layout((int)CenterX-(int)X-btn_an.getWidth()/2,(int)CenterY-(int)Y-btn_an.getHeight()/2,
                                        (int)CenterX-(int)X+btn_an.getWidth()/2,(int)CenterY-(int)Y+btn_an.getHeight()/2);
                            }
                            //第三象限
                            else if(sY-CenterY>0 && sX-CenterX<=0){
                                //Toast.makeText(MainActivity.this,"第三象限",Toast.LENGTH_SHORT).show();
                                btn_an.layout((int)CenterX-(int)X-btn_an.getWidth()/2,(int)CenterY+(int)Y-btn_an.getHeight()/2,
                                        (int)CenterX-(int)X+btn_an.getWidth()/2,(int)CenterY+(int)Y+btn_an.getHeight()/2);
                            }else {
                                //Toast.makeText(MainActivity.this,"第四象限",Toast.LENGTH_SHORT).show();
                                btn_an.layout((int)CenterX+(int)X-btn_an.getWidth()/2,(int)CenterY+(int)Y-btn_an.getHeight()/2,
                                        (int)CenterX+(int)X+btn_an.getWidth()/2,(int)CenterY+(int)Y+btn_an.getHeight()/2);
                            }

                            // btn_an.layout((int)sX-btn_an.getWidth()/2,(int)sY-btn_an.getHeight()/2,(int)sX+btn_an.getWidth()/2,(int)sY+btn_an.getHeight()/2);
                            //Toast.makeText(MainActivity.this,"大于"+String.valueOf(sX)+" "+String.valueOf(sY),Toast.LENGTH_SHORT).show();
                        }
                        else {
                            int left=btn_an.getLeft();
                            int right=btn_an.getRight();
                            int top=btn_an.getTop();
                            int bottom=btn_an.getBottom();
                            btn_an.layout(left+dx,top+dy,right+dx,bottom+dy);
                        }
                        startX=(int)event.getRawX();
                        startY=(int)event.getRawY();

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                beijing.invalidate();
                return true;
            }
        });
    }

}
