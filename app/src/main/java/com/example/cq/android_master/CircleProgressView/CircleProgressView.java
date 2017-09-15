package com.example.cq.android_master.CircleProgressView;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.constraint.solver.SolverVariable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.cq.android_master.R;

public class CircleProgressView extends View {
    private int mCurrent; // 当前进度
    private Paint mPaintOut;
    private Paint mPaintCurrent;
    private Paint mPaintText; // 定义中间部分的百分比文字画笔
    private float mPaintWidth;// 画笔宽度
    private int mPaintColor = Color.RED;//画笔颜色
    private int mTextColor = Color.BLACK;//字体颜色
    private float mTextSize; // 字体大小
    private int location; //从什么地方开始
    private float startAngle;//开始的角度

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        // 获取属性值
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        location = array.getInt(R.styleable.CircleProgressView_location, 1);
        mPaintWidth = array.getDimension(R.styleable.CircleProgressView_progress_paint_width, dip2px(context, 4));//默认4dp
        mPaintColor = array.getColor(R.styleable.CircleProgressView_progress_paint_color, mPaintColor);
        mTextColor = array.getColor(R.styleable.CircleProgressView_progress_text_color, mTextColor);
        mTextSize = array.getDimension(R.styleable.CircleProgressView_progress_text_size, dip2px(context, 18));

        // 画笔->背景圆弧
        mPaintOut = new Paint();
        // 设置抗锯齿
        mPaintOut.setAntiAlias(true);
        // 设置画笔样式为空心
        mPaintOut.setStyle(Paint.Style.STROKE);
        // 画笔样式为空心时，设置空心画笔的宽度
        mPaintOut.setStrokeWidth(mPaintWidth);
        // 设置画笔颜色(灰色)
        mPaintOut.setColor(Color.GRAY);
        //设置画笔笔触的风格(圆角的笔触)
        mPaintOut.setStrokeCap(Paint.Cap.ROUND);

        //画笔->进度圆弧
        mPaintCurrent = new Paint();
        mPaintCurrent.setAntiAlias(true);
        mPaintCurrent.setStyle(Paint.Style.STROKE);
        mPaintCurrent.setStrokeWidth(mPaintWidth);
        mPaintCurrent.setColor(mPaintColor);
        mPaintCurrent.setStrokeCap(Paint.Cap.ROUND);

        //画笔->绘制字体
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(mTextColor);
        mPaintText.setTextSize(mTextSize);

        if (location == 1) {//默认从左侧开始
            startAngle = -180;
        } else if (location == 2) {
            startAngle = -90;
        } else if (location == 3) {
            startAngle = 0;
        } else if (location == 4) {
            startAngle = 90;
        }
    }

    /**
     * 设置宽高度，这样取宽高度的最小值，保证进度条是圆形的
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景圆弧,因为画笔有一定的宽度，所有画圆弧的范围要比View本身的大小稍微小一些，不然画笔画出来的东西会显示不完整
        RectF rectf = new RectF(mPaintWidth / 2, mPaintWidth / 2,
                getWidth() - mPaintWidth / 2, getHeight() - mPaintWidth / 2);
        canvas.drawArc(rectf, 0, 360, false, mPaintOut);

        float sweepAngle = 360 * mCurrent / 100;
        canvas.drawArc(rectf, startAngle, sweepAngle, false, mPaintCurrent);

        //绘制进度数字
        String text = mCurrent + "%";
        //获取文字宽度
        float textWidth = mPaintText.measureText(text, 1, text.length());
//        获取文字和圆弧的间距
        float dx = getWidth() / 2 - textWidth / 2;
        Paint.FontMetricsInt fontMetricsInt = mPaintText.getFontMetricsInt();

        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        float baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, dx, baseLine, mPaintText);
        if (mLoadingCompleteListener != null && mCurrent == 100) {
            mLoadingCompleteListener.complete();
        }
    }

    private OnLoadingCompleteListener mLoadingCompleteListener;

    /**
     * 获取当前进度值
     *
     * @return
     */
    public int getmCurrent() {
        return mCurrent;
    }

    /**
     * 设置当前进度并重新绘制界面
     *
     * @param mCurrent
     */
    public void setmCurrent(int mCurrent) {
        this.mCurrent = mCurrent;
        invalidate();
    }

    public void setOnLoadingCompleteListener(OnLoadingCompleteListener loadingCompleteListener) {
        this.mLoadingCompleteListener = loadingCompleteListener;
    }

    /**
     * 定义一个加载完成的接口
     */
    public interface OnLoadingCompleteListener {
        void complete();
    }

    /**
     * 根据手机的屏幕密度，把dip转化成px
     */
    private float dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 输入总值和当前值，获取到进度
     */
    public int getPosition(int current, int total){
        return current / total * 100;
    }


}
