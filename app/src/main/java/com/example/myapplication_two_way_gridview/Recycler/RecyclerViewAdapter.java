package com.example.myapplication_two_way_gridview.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_two_way_gridview.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private LayoutInflater inflater;
    private int guestNumber;

    public RecyclerViewAdapter(Context context, int guestNumber) {
        this.inflater = LayoutInflater.from(context);
        this.guestNumber = guestNumber;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(this.inflater.inflate(R.layout.recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvSub.setText("teacher");
        holder.tvMain.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return guestNumber;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvSub;
        TextView tvMain;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            iv = (ImageView)itemView.findViewById(R.id.iv);
            tvSub = (TextView) itemView.findViewById(R.id.tv_sub);
            tvMain = (TextView) itemView.findViewById(R.id.tv_main);
        }
    }
}
