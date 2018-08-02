package com.coco.imv3demo.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.coco.imv3demo.R;

import java.util.List;

/**
 * Created by ydx on 18-8-2.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private GridView gridView;
    private List<String> list;
    private static int ROW_NUMBER = 3;

    public GridAdapter(Context context, GridView gridView, List<String> list) {
        this.context = context;
        this.gridView = gridView;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (null != list)
            return list.size();
        return 0;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.girditem, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.ItemText);
            viewHolder.img = convertView.findViewById(R.id.ItemImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                gridView.getHeight() / ROW_NUMBER);
        convertView.setLayoutParams(params);

        viewHolder.img.setImageResource(R.drawable.icon);
        viewHolder.tv.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView tv;
        ImageView img;
    }
}
