package com.example.myapplication_two_way_gridview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by muhammadchehab on 12/8/17.
 */

public class GridViewAdapterTop extends BaseAdapter {

    private Context context;
    private List<String> list;

    public GridViewAdapterTop(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item_top, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.textView);
            viewHolder.tv.setText(this.list.get(position));
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
