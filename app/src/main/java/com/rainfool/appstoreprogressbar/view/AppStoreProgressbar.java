package com.rainfool.appstoreprogressbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by rainfool on 16/5/30.
 */
public class AppStoreProgressbar extends TextView {

    private Matrix shaderMatrix;

    private Shader mTextShader;
    private Shader mBgShader;
    private int mTextInactiveColor,mTextActiveColor,mBgActiveColor,mBgInactiveColor;
    private int mProgress = 50;

    private int mWidth;
    private int mProgressWidth;

    private String mContent;//进度条中要显示的内容


    public AppStoreProgressbar(Context context) {
        this(context,null);
    }

    public AppStoreProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AppStoreProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        shaderMatrix = new Matrix();
//        mBgActiveColor = Color.parseColor("#00fcff");
        setBgColors(Color.parseColor("#00fcff"),Color.BLACK);
        this.setTextColors(Color.WHITE,Color.BLACK);
        createShader();
    }

    public void setBgColors(int activeColor, int inActiveColor) {
        this.mBgActiveColor = activeColor;
        this.mBgInactiveColor = inActiveColor;
        mBgShader = null;
        invalidate();
    }

    public void setTextColors(int activeColor,int inActiviColor) {
        this.mTextActiveColor = activeColor;
        this.mTextInactiveColor = inActiviColor;
        mTextShader = null;
        invalidate();
    }

    @UiThread
    public void setProgress(int progress) {
        this.mProgress = progress;
        double percentage = (double)progress / 100;
        Log.d("rainfool","percentage:" +
                "" + percentage);
        mProgressWidth = (int) (mWidth * percentage);
        Log.d("rainfool","mProgressWidth:" + mProgressWidth);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        Log.d("rainfool","mPWidth:" + mWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mTextShader == null) {
            createShader();
        }
        shaderMatrix.setTranslate(mProgressWidth,0);
        mTextShader.setLocalMatrix(shaderMatrix);

        Paint paint = new Paint();
        paint.setShader(mBgShader);
        canvas.drawRect(0,0,mProgressWidth,getBottom(),paint);
        getPaint().setShader(mTextShader);
        super.onDraw(canvas);
    }

    private void createShader() {
        if (mTextShader == null) {
            mTextShader = new LinearGradient(0,0,1,0,mTextActiveColor, mTextInactiveColor, Shader.TileMode.CLAMP);
            mBgShader = new LinearGradient(0,0,1,0,mBgActiveColor,mBgActiveColor, Shader.TileMode.CLAMP);
        }
    }


}
