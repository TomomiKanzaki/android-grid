package com.example.myapplication_two_way_gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by muhammadchehab on 12/8/17.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private float density;
    private int guestNumber;

    public GridViewAdapter(Context context, List<String> list, float density, int guestNumber){
        this.context = context;
        this.list = list;
        this.density = density;
        this.guestNumber = guestNumber;
    }

    @Override
    public int getCount() {
        return list.size() * guestNumber;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
        viewHolder = new ViewHolder();
        viewHolder.tv = convertView.findViewById(R.id.textView);
        if (position < list.size()){
            ViewGroup.LayoutParams lp = viewHolder.tv.getLayoutParams();
            lp.height = (int) (density * 50);
            viewHolder.tv.setLayoutParams(lp);
            viewHolder.tv.setText(list.get(position));
        }
        convertView.setTag(viewHolder);
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
