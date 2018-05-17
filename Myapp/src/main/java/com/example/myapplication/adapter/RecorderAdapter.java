package com.example.myapplication.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Response;
import com.example.myapplication.base.Text;

import java.util.List;
import java.util.Timer;

/**
 * Created by L on 2017/9/9.
 */

public class RecorderAdapter extends ArrayAdapter {
    private List mlist;
    private Context mContext;
    private final int mMaxItemWidth;
    private final int mMinItemWidth;
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_RESPONCE = 1;
    private LayoutInflater inflater;
    private ViewHolderText holderText;
    private ViewHolderResponse holderResponse;
    private final float height;
    private final float min;
    private TextView item_text_time;
    private TextView item_response_time;
    private TextView item_rcord_time;
    private String vaule;
    private View id_response;
    private final Timer timer;


    public RecorderAdapter(Context context, List mDates) {
        super(context, -1, mDates);
        mContext = context;
        mlist = mDates;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        inflater = LayoutInflater.from(mContext);

        wm.getDefaultDisplay().getMetrics(dm);
        mMaxItemWidth = (int) (dm.widthPixels * 0.65f);
        mMinItemWidth = (int) (dm.widthPixels * 0.01f);
        //
        timer = new Timer();
        height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, mContext.getResources().getDisplayMetrics());
        min = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, mContext.getResources().getDisplayMetrics());

    }

    @Override
    public int getCount() {
        if (mlist != null) {
            return mlist.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (mlist.get(position) instanceof Text) {
            return TYPE_TEXT;
        } else if (mlist.get(position) instanceof Response) {
            return TYPE_RESPONCE;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        //TODO
        switch (type) {
            case TYPE_TEXT:
                holderText = new ViewHolderText();
                convertView = inflater.inflate(R.layout.list_item_text, parent, false);
                holderText.mText = (TextView) convertView.findViewById(R.id.id_tv);
                holderText.text_length = convertView.findViewById(R.id.id_text_length);
                item_text_time = (TextView) convertView.findViewById(R.id.id_item_text_time);
                convertView.setTag(R.id.TAG_TEXT, holderText);
                break; //文本
            case TYPE_RESPONCE:
                holderResponse = new ViewHolderResponse();
                convertView = inflater.inflate(R.layout.list_item_response, parent, false);
                holderResponse.mResponseTextView = (TextView) convertView.findViewById(R.id.id_tv_response);
                holderResponse.text_length_response = convertView.findViewById(R.id.id_text_length_response);
                item_response_time = (TextView) convertView.findViewById(R.id.id_item_response_time);
                id_response = convertView.findViewById(R.id.id_response);
                convertView.setTag(R.id.TAG_RESPONSE, holderResponse);
                break; //返回文本
        }
        Object object = mlist.get(position);
        switch (type) {
            case TYPE_TEXT:   //文本
                Text text = (Text) object;
                if (text != null) {
                    vaule = ((Text) mlist.get(position)).getText();
                    holderText.mText.setText(vaule);
                    ViewGroup.LayoutParams layoutParams = holderText.text_length.getLayoutParams();
                    int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    holderText.text_length.measure(w, w);
                    if (((Text) mlist.get(position)).getVisible()) {
                        item_text_time.setText(((Text) mlist.get(position)).getTime());
                    } else {
                        item_text_time.setVisibility(View.GONE);
                    }

                    int width = holderText.text_length.getMeasuredWidth();

                    if (width > mMaxItemWidth) {
                        layoutParams.width = mMaxItemWidth;
                    } else {
                        if (vaule.length() < 3) {
                            layoutParams.width = (int) min;
                        }
                        layoutParams.height = (int) height;
                    }
                }
                break;
            case TYPE_RESPONCE:   //返回文本
                Response response = (Response) object;
                if (response != null) {
                    //TODO
                    id_response.setVisibility(View.VISIBLE);
                    String texts = ((Response) mlist.get(position)).getResponse();
                    holderResponse.mResponseTextView.setText(texts);
                    ViewGroup.LayoutParams layout = holderResponse.text_length_response.getLayoutParams();
                    int ww = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    holderResponse.text_length_response.measure(ww, ww);

                    int widths = holderResponse.text_length_response.getMeasuredWidth();
                    if (widths > mMaxItemWidth) {
                        layout.width = mMaxItemWidth;
                    } else {
                        if (texts.length() < 3) {
                            layout.width = (int) min;
                        }
                        layout.height = (int) height;
                    }
                    if (((Response) mlist.get(position)).getVisible()) {
                        item_response_time.setText(((Response) mlist.get(position)).getTime());
                    } else {
                        item_response_time.setVisibility(View.GONE);
                    }
                }
                break;
        }
        return convertView;
    }


    static class ViewHolderText {
        TextView mText;
        View text_length;
    }

    static class ViewHolderResponse {
        TextView mResponseTextView;
        View text_length_response;
    }
}
