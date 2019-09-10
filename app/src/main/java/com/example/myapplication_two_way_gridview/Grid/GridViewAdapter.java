package com.example.myapplication_two_way_gridview.Grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication_two_way_gridview.R;

import java.util.List;


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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**convertViewには一列目のみ高さを50dpにしたgridViewが入っており、
         * それを使いまわしているので、スクロールして新たな行が表示された時には高さを戻してやる必要がある*/
        if (position < list.size()){
            ViewGroup.LayoutParams lp = viewHolder.tv.getLayoutParams();
            lp.height = (int) (density * 50);
            viewHolder.tv.setLayoutParams(lp);
            viewHolder.tv.setText(list.get(position));
        } else {
            ViewGroup.LayoutParams lp = viewHolder.tv.getLayoutParams();
            lp.height = (int) (density * 100);
            viewHolder.tv.setLayoutParams(lp);
            viewHolder.tv.setText("");
        }
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
