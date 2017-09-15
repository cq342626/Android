package com.example.cq.android_master;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cq.android_master.CircleProgressView.CircleProgressActivity;
import com.example.cq.android_master.UserGuideView.UserGActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_ydy)
    Button tvYdy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvYdy.setText("nishi dianji ");
    }

    @OnClick({R.id.tv_ydy, R.id.tv_yxjdt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ydy:
                Intent intent = new Intent(this, UserGActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_yxjdt:
                Intent intent_tv_yxjdt = new Intent(this, CircleProgressActivity.class);
                startActivity(intent_tv_yxjdt);
                break;
        }
    }

}
