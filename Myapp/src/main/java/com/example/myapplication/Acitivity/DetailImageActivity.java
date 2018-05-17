package com.example.myapplication.Acitivity;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;
import com.lidroid.xutils.BitmapUtils;

public class DetailImageActivity extends AppCompatActivity {

    private ViewPager image_viewPager;
    private String[] splits;
    private BitmapUtils bitmapUtils;
    private RelativeLayout detail_relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LEI", "111");
        setContentView(R.layout.activity_detail_image);

        bitmapUtils = new BitmapUtils(this);
        splits = getIntent().getStringArrayExtra("split");
        int position = getIntent().getIntExtra("position", 0);
        image_viewPager = (ViewPager) findViewById(R.id.image_viewPager);
        image_viewPager.setAdapter(new ImagePageAdapte());
        image_viewPager.setCurrentItem(position);
    }

    class ImagePageAdapte extends PagerAdapter {

        @Override
        public int getCount() {
            if (splits != null) {
                return splits.length;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View inflate = View.inflate(DetailImageActivity.this, R.layout.image_detail, null);
            ImageView detail_iv = (ImageView) inflate.findViewById(R.id.detail_iv);

            detail_relative = (RelativeLayout) inflate.findViewById(R.id.detail_relative);
            detail_relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailImageActivity.this.finish();
                }
            });
            detail_iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //date
            bitmapUtils.display(detail_iv, splits[position]);
            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
