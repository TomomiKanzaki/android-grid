package com.example.myapplication_two_way_gridview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalViewHolder> {
    private LayoutInflater inflater;
    private List<String> list;
    private float density;
    private onItemClickListener listener;

    public HorizontalRecyclerViewAdapter(Context context, List<String> list, float density){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.density = density;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HorizontalViewHolder(inflater.inflate(R.layout.gridview_item_top, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {

        holder.textView.setText(list.get(position));
        holder.textView.setOnClickListener(view -> {
            listener.onClick(view, position);
        });
        try{
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            lp.width = (int) (density * 100);
            holder.itemView.setLayoutParams(lp);
        } catch (Exception e){ Log.e("error", String.valueOf(e)); }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public HorizontalViewHolder(View itemView) {
            super(itemView);


            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onClick(View view, int position);
    }
}

