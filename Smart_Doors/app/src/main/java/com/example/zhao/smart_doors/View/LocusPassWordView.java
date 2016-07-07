package com.example.zhao.smart_doors.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/4/18.
 */
public class LocusPassWordView extends View {
    private boolean isCache;
    private float w,h;
    private Paint mPaint =new Paint(Paint.ANTI_ALIAS_FLAG);

    private Point[][] mPoints =new  Point[3][3];

    private float r;//园的半径

    private List<Point> sPoint=new ArrayList<Point>();

    private boolean checking=false;

    private long CLEAR_TIME = 0;// 清除痕迹的时间
    private int passwordMinLength = 5;// 密码最小长度
    private boolean isTouch = true; // 是否可操作

    private Bitmap locus_round_original;// 圆点初始状态时的图片
    private Bitmap locus_round_click;// 圆点点击时的图片

    public LocusPassWordView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LocusPassWordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LocusPassWordView(Context context) {
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas) {
        if (!isCache) {
            initCache();
        }
        drawToCanvas(canvas);
    }
    public void initCache(){

    }
    private void drawToCanvas(Canvas canvas){

    }
}
