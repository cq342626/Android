package com.example.cq.android_master.FloatingView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cq.android_master.R;
import com.ufreedom.floatingview.Floating;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.CurveFloatingPathTransition;
import com.ufreedom.floatingview.effect.ScaleFloatingTransition;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;
import com.ufreedom.floatingview.spring.ReboundListener;
import com.ufreedom.floatingview.spring.SimpleReboundListener;
import com.ufreedom.floatingview.spring.SpringHelper;
import com.ufreedom.floatingview.transition.BaseFloatingPathTransition;
import com.ufreedom.floatingview.transition.FloatingPath;
import com.ufreedom.floatingview.transition.FloatingTransition;
import com.ufreedom.floatingview.transition.PathPosition;
import com.ufreedom.floatingview.transition.YumFloating;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 时间 ：2017/9/19  9:44
 * 作者 ：陈奇
 * 作用 ：
 */
public class FloatingViewActivity extends AppCompatActivity {

    @BindView(R.id.statusBar)
    View statusBar;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.bikeBg)
    ImageView bikeBg;
    @BindView(R.id.icPaperAirPlane)
    ImageView icPaperAirPlane;
    @BindView(R.id.icCommandLine)
    ImageView icCommandLine;
    @BindView(R.id.icLike)
    ImageView icLike;
    @BindView(R.id.icStar)
    ImageView icStarView;
    @BindView(R.id.itemBikeContainerView)
    RelativeLayout bikeRootView;
    @BindView(R.id.icBeer)
    ImageView icBeer;
    @BindView(R.id.icMilk)
    ImageView icMilk;
    @BindView(R.id.icSoda)
    ImageView icSoda;
    @BindView(R.id.icCream)
    ImageView icCream;
    @BindView(R.id.itemClockContainerView)
    RelativeLayout clockRootView;
    @BindView(R.id.icPlane)
    ImageView icPlane;

    private int mScreenWidth;
    private int mScreenHeight;

    private Floating floating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatingview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        // 初始化一个floating
        floating = new Floating(this);
        // 初始化屏幕宽高
        mScreenWidth = UIUtils.getScreenWidth(this);
        mScreenHeight = UIUtils.getScreenWidth(this);

        int margin = UIUtils.dip2px(this, 15);
        int w = mScreenWidth - margin * 2;
        int h =  (int) (w * 0.53f);

        // 得到第一个根布局的LayoutParams，重设宽高
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bikeRootView.getLayoutParams();
        layoutParams.width = w;
        layoutParams.height = h;

        // 得到第二个根布局的LayoutParams，重设宽高
        RelativeLayout.LayoutParams clockRootViewLayoutParams = (RelativeLayout.LayoutParams)clockRootView.getLayoutParams();
        clockRootViewLayoutParams.width = w;
        clockRootViewLayoutParams.height = h;




    }


    @OnClick({R.id.statusBar, R.id.toolbar, R.id.bikeBg, R.id.icPaperAirPlane, R.id.icCommandLine, R.id.icLike, R.id.icStar, R.id.itemBikeContainerView, R.id.icBeer, R.id.icMilk, R.id.icSoda, R.id.icCream, R.id.itemClockContainerView, R.id.icPlane})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.statusBar:
                break;
            case R.id.toolbar:
                break;
            case R.id.bikeBg:
                break;
            case R.id.icPaperAirPlane: // 纸飞机
                ImageView imageView = new ImageView(FloatingViewActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icPaperAirPlane.getMeasuredWidth(), icPaperAirPlane.getMeasuredHeight()));
                imageView.setImageResource(R.drawable.paper_airplane);

                FloatingElement floatingElement1 = new FloatingBuilder()
                        .anchorView(view)
                        .targetView(imageView)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                floating.startFloating(floatingElement1);
                break;
            case R.id.icCommandLine: // 控制台
                TextView textView = new TextView(FloatingViewActivity.this);
                textView.setText("Hello FloatingView");

                FloatingElement floatingElement2 = new FloatingBuilder()
                        .anchorView(view)
                        .targetView(textView)
                        .offsetY(-view.getMeasuredHeight())
                        .floatingTransition(new ScaleFloatingTransition())
                        .build();
                floating.startFloating(floatingElement2);
                break;
            case R.id.icLike: // 点赞
                FloatingElement floatingElement4 = new FloatingBuilder()
                        .anchorView(view)
                        .targetView(R.layout.ic_like)
                        .floatingTransition(new TranslateFloatingTransition())
                        .build();
                floating.startFloating(floatingElement4);
                break;
            case R.id.icStar: // 小星星
                ImageView imageView3 = new ImageView(FloatingViewActivity.this);
                imageView3.setLayoutParams(new ViewGroup.LayoutParams(icStarView.getMeasuredWidth(), icStarView.getMeasuredHeight()));
                imageView3.setImageResource(R.drawable.star_floating);

                final FloatingElement floatingElement3 = new FloatingBuilder()
                        .anchorView(view)
                        .targetView(imageView3)
                        .floatingTransition(new StarFloating())
                        .build();
                SpringHelper.createWithBouncinessAndSpeed(0f,1f,11,15).reboundListener(new ReboundListener() {
                    @Override
                    public void onReboundUpdate(double currentValue) {
                        view.setScaleX((float) currentValue);
                        view.setScaleY((float) currentValue);
                    }

                    @Override
                    public void onReboundEnd() {
                        floating.startFloating(floatingElement3);
                    }
                }).start();
                break;
            case R.id.icPlane:

                ImageView imageView1 = new ImageView(FloatingViewActivity.this);
                imageView1.setLayoutParams(new ViewGroup.LayoutParams(icPlane.getMeasuredWidth(), icPlane.getMeasuredHeight()));
                imageView1.setImageResource(R.drawable.floating_plane);

                FloatingElement floatingElement = new FloatingBuilder()
                        .anchorView(view)
                        .targetView(imageView1)
                        .floatingTransition(new PlaneFloating())
                        .build();
                floating.startFloating(floatingElement);
                break;
        }
    }

    class PlaneFloating extends BaseFloatingPathTransition {
        @Override
        public FloatingPath getFloatingPath() {
            Path path = new Path();
            path.moveTo(0, 0);
            path.quadTo(100, -300, 0, -600);
            path.rLineTo(0, -mScreenHeight - 300);
            return FloatingPath.create(path, false);
        }

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(getStartPathPosition(), getEndPathPosition());
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    PathPosition floatingPosition = getFloatingPosition(value);
                    yumFloating.setTranslationX(floatingPosition.x);
                    yumFloating.setTranslationY(floatingPosition.y);

                }
            });
            translateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.setTranslationX(0);
                    yumFloating.setTranslationY(0);
                    yumFloating.setAlpha(0f);
                    yumFloating.clear();
                }
            });

            SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f, 14, 15)
                    .reboundListener(new SimpleReboundListener() {
                        @Override
                        public void onReboundUpdate(double currentValue) {
                            yumFloating.setScaleX((float) currentValue);
                            yumFloating.setScaleY((float) currentValue);
                        }
                    }).start(yumFloating);

            translateAnimator.setDuration(3000);
            translateAnimator.start();
        }
    }

    class StarFloating implements FloatingTransition {

        @Override
        public void applyFloating(final YumFloating yumFloating) {
            SpringHelper.createWithBouncinessAndSpeed(0.0f, 1.0f,10, 15)
                    .reboundListener(new SimpleReboundListener(){
                        @Override
                        public void onReboundUpdate(double currentValue) {
                            yumFloating.setScaleX((float) currentValue);
                            yumFloating.setScaleY((float) currentValue);
                        }
                    }).start(yumFloating);


            ValueAnimator rotateAnimator = ObjectAnimator.ofFloat(0, 360);
            rotateAnimator.setDuration(500);
            rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
            rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
            rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    yumFloating.setRotation((float) valueAnimator.getAnimatedValue());
                }
            });

            ValueAnimator translateAnimator = ObjectAnimator.ofFloat(0, 500);
            translateAnimator.setDuration(600);
            translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    yumFloating.setTranslationY(-(Float) valueAnimator.getAnimatedValue());
                }
            });
            translateAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    yumFloating.setAlpha(0f);
                    yumFloating.clear();
                }
            });
            rotateAnimator.start();
            translateAnimator.start();
        }
    }
}
