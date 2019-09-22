package com.example.myapplication_two_way_gridview.Table;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.celerysoft.tablefixheaders.adapter.BaseTableAdapter;
import com.example.myapplication_two_way_gridview.R;

import java.util.ArrayList;
import java.util.List;

public class MatrixTableAdapter<T> extends BaseTableAdapter
{

	private final static int WIDTH_DIP = 100;
	private final static int HEIGHT_DIP = 100;

	private final Context context;

	public T[][] table;

	private int density;

	private List firstColumnList;
	private List topRowList;

	public MatrixTableAdapter(Context context, T[][] table, int density, List<String> firstColumnList, List<String> topRowList)
	{
		this.context = context;

		this.density = density;

		this.firstColumnList = firstColumnList;
		this.topRowList = topRowList;

		setInformation(table);
	}

	private void setInformation(T[][] table)
	{
		this.table = table;
	}

	@Override
	public int getRowCount() {
		return firstColumnList.size();
	}

	@Override
	public int getColumnCount()
	{
		return topRowList.size();
	}

	@Override
	public View getView(int row, int column, View convertView, ViewGroup parent)
	{
		if (convertView == null) {
			convertView = new TextView(context);
		}
		((TextView) convertView).setGravity(Gravity.CENTER);
		((TextView) convertView).setBackgroundResource(R.drawable.background1);
		try{
			if (row == -1){
				((TextView) convertView).setText(topRowList.get(column).toString());
			} else {
				if (column == -1){
					((TextView) convertView).setText(firstColumnList.get(row).toString());
				} else{
					if (table[row][column] == null){
						((TextView) convertView).setText("");
					} else {
						((TextView) convertView).setText(table[row][column].toString());
					}
				}
			}
		} catch (Exception e){
			Log.e("Exception: ", String.valueOf(e));
		}

		return convertView;
	}

	@Override
	public int getHeight(int row)
	{
		if (row == -1)return HEIGHT_DIP * density / 2 ;
		else return HEIGHT_DIP * density;
	}

	@Override
	public int getWidth(int column)
	{
		return WIDTH_DIP * density;
	}

	@Override
	public int getItemViewType(int row, int column)
	{
		return 0;
	}

	@Override
	public int getViewTypeCount()
	{
		return 1;
	}

	@Override
	public int getBackgroundResId(int row, int column)
	{
		return 0;
	}

	@Override
	public int getBackgroundHighlightResId(int row, int column) {
		return R.drawable.item_highlight_rect;
	}

	@Override
	public boolean isRowSelectable(int row) {
		return true;
	}

	@Override
	public long getItemId(int row, int column) {
		return 0;
	}

	@Override
	public Object getItem(int row, int column) {
		if (row <= getRowCount() && column <= getColumnCount()) {
			return this.table[row][column];
		} else {
			return null;
		}
	}
}
