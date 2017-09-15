package com.example.cq.android_master.CircleProgressView;


import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ForwardingListener;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.cq.android_master.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleProgressActivity extends AppCompatActivity {
    @BindView(R.id.circleProgressView)
    CircleProgressView circleProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setDuration(4000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float current = (float) valueAnimator.getAnimatedValue();
                circleProgressView.setmCurrent((int)current);
            }
        });
        animator.start();
        circleProgressView.setOnLoadingCompleteListener(new CircleProgressView.OnLoadingCompleteListener() {
            @Override
            public void complete() {
                Toast.makeText(CircleProgressActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
