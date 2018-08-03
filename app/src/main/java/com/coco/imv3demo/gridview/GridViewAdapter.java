package com.coco.imv3demo.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.coco.imv3demo.R;

import java.util.List;

/**
 * Created by ydx on 18-8-3.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> list;
    LayoutInflater layoutInflater;
    private ImageView mImageView;

    public GridViewAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size()+1;//注意此处
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.grid_item, null);
        mImageView = (ImageView) convertView.findViewById(R.id.item);
        if (position < list.size()) {
            mImageView.setBackgroundResource(list.get(position));
        }else{
            mImageView.setBackgroundResource(R.mipmap.ic_launcher);//最后一个显示加号图片
        }
        return convertView;
    }


}
