package com.example.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    int posX;
    int posY;

    Paint paint = new Paint();

    //코드에서 new로 만들면 얘가 호출
    public MyView(Context context) {
        super(context);
        initView();
    }

    //xml로 만들면 얘가 호출
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        setBackgroundColor(Color.CYAN);
        paint.setTextSize(26);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        posX = (int)event.getX();
        posY = (int)event.getY();
        invalidate(); // 화면 무효화 하여 다시 그리기

//        return super.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawText("Touch Event: (" +posX + ", " + posY + ")", posX, posY, paint);
    }
}