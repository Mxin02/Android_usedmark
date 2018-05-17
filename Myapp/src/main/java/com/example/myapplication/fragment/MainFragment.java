package com.example.myapplication.fragment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.myapplication.Acitivity.MainDetaiActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.MyAdapter;
import com.example.myapplication.base.baseFragment;
import com.example.myapplication.db.Goods;
import com.example.myapplication.view.MyPullableListView;
import com.lidroid.xutils.BitmapUtils;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.Collections;
import java.util.List;


public class MainFragment extends baseFragment implements AdapterView.OnItemClickListener {

    private List<Goods> goodsList;
    private mainAdapter adapter = new mainAdapter();
    private BitmapUtils bitmapUtils;
    private MyPullableListView mian_listview;
    private SliderLayout sliderShow;
    private PagerIndicator indicator;
    private PullToRefreshView mPullToRefreshView;
    private View inflate;

    @Override
    public View initView() {
        inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_main, null);
        bitmapUtils = new BitmapUtils(mContext);
        sliderShow = (SliderLayout) inflate.findViewById(R.id.slider);
        indicator = (PagerIndicator) inflate.findViewById(R.id.custom_indicator);
        mPullToRefreshView = (PullToRefreshView) inflate.findViewById(R.id.pull_to_refresh);
        mian_listview = (MyPullableListView) inflate.findViewById(R.id.mian_listview);
        initSlider();
        initRefrsh();
        mian_listview.setAdapter(adapter);
        mian_listview.setOnItemClickListener(this);
        return inflate;
    }

    @Override
    public void setTitleView(TextView title, ImageView back) {
        title.setText("首页");
        back.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(mContext, MainDetaiActivity.class);
        int id = goodsList.get(position).getId();
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void initRefrsh() {
        mPullToRefreshView = (PullToRefreshView) inflate.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void initSlider() {
        TextSliderView textSliderView1 = new TextSliderView(mContext);
        textSliderView1
                .description("IT生活")
                .image("http://7mno4h.com2.z0.glb.qiniucdn.com/5608cae6Nbb1a39f9.jpg");
        TextSliderView textSliderView2 = new TextSliderView(mContext);
        textSliderView2
                .description("金秋风暴")
                .image("http://7mno4h.com2.z0.glb.qiniucdn.com/5608b7cdN218fb48f.jpg");

        TextSliderView textSliderView3 = new TextSliderView(mContext);
        textSliderView3
                .description("手机会场")
                .image("http://7mno4h.com2.z0.glb.qiniucdn.com/5608eb8cN9b9a0a39.jpg");


        TextSliderView textSliderView4 = new TextSliderView(mContext);
        textSliderView4
                .description("音像会场")
                .image("http://7mno4h.com2.z0.glb.qiniucdn.com/5608f3b5Nc8d90151.jpg");


        sliderShow.addSlider(textSliderView1);
        sliderShow.addSlider(textSliderView2);
        sliderShow.addSlider(textSliderView3);
        sliderShow.addSlider(textSliderView4);


        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//设置默认指示器位置(默认指示器白色)
        sliderShow.setCustomIndicator(indicator); // 设置自定义指示器(位置自定义)
        sliderShow.setCustomAnimation(new DescriptionAnimation());   // 设置TextView自定义动画
        sliderShow.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        sliderShow.setDuration(6000);//设置时间 这个时间 就是延迟时间 第一个图跟第2个图3秒间隔


    }

    class mainAdapter extends MyAdapter {
        @Override
        public int getCount() {
            if (goodsList != null) {
                return goodsList.size();
            }
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.gride_item, null);
            TextView main_price = inflate.findViewById(R.id.main_price);
            TextView main_msg = inflate.findViewById(R.id.main_msg);
            ImageView main_image = inflate.findViewById(R.id.main_image);
            TextView main_location = inflate.findViewById(R.id.main_location);
            TextView main_sellnum = inflate.findViewById(R.id.main_sellnum);

            if (goodsList != null) {
                main_msg.setText(goodsList.get(i).getTitle());
                main_price.setText(goodsList.get(i).getPrice());
                main_location.setText(goodsList.get(i).getLocation());
                main_sellnum.setText(goodsList.get(i).getSellnum());
                String img = goodsList.get(i).getImg();

                if (img != null) {
                    String[] split = img.split("&&&");
                    for (int b = 0; b < split.length; b++) {
                        if (split[0] != null) {
                            bitmapUtils.display(main_image, split[0]);
                        } else {
                            main_image.setImageResource(R.mipmap.xiaomi);
                        }
                    }
                } else {
                    int id = goodsList.get(i).getId();
                    if (id == 1) {
                        main_image.setImageResource(R.mipmap.jixie);
                    } else if (id == 2) {
                        main_image.setImageResource(R.mipmap.shubiao);
                    } else if (id == 3) {
                        main_image.setImageResource(R.mipmap.ear);
                    }
                }

            }
            return inflate;
        }
    }

    @Override
    public void onResume() {
        goodsList = dbTools.QueryGoodsList();
        Collections.reverse(goodsList);
        adapter.notifyDataSetChanged();
        super.onResume();
    }
}
