package com.example.cq.android_master.UserGuideView;


import android.icu.util.Measure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cq.android_master.R;
import com.example.userguidelibrary.UserGuideView;
import com.zhl.cbpullrefresh.CBPullRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户指引
 */
public class UserGActivity extends AppCompatActivity {
    private String[] datas = new String[]{"收藏", "字体大小", "软件设置", "换肤"};

    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.top)
    ImageView top;
    @BindView(R.id.guideView)
    UserGuideView guideView;
    private CBPullRefreshListView listView;
    private View tipTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userg_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tipTextView = LayoutInflater.from(this).inflate(R.layout.userg_custom_tipview, null);
        guideView.setTouchOutsideDismiss(false);
        guideView.setHightLightView(top,icon,back);
        gridview.setAdapter(new MyAaapter());
    }

    @OnClick({R.id.icon, R.id.back, R.id.top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon:
                guideView.setTipView(tipTextView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                guideView.setHighLightView(icon);

                break;
            case R.id.back:
                break;
            case R.id.top:
                break;
        }
    }
    private class MyAaapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public Object getItem(int position) {
            return datas[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public int getPosition(){
            return getCount() / 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView==null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(UserGActivity.this).inflate(R.layout.userg_grid_item,parent,false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tx);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(datas[position]);
//            if(position==1){
////                guideView.setTipView(BitmapFactory.decodeResource(getResources(),R.mipmap.sidebar_photo));
//                guideView.setHighLightView(convertView);
//            }
            final View finalConvertView = convertView;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    guideView.setTipView(tipTextView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    guideView.setHighLightStyle(UserGuideView.VIEWSTYLE_RECT);
                    guideView.setHighLightView(finalConvertView);
                }
            });
            return convertView;
        }

        private class ViewHolder{
            public TextView textView;
        }
    }
}
