package com.example.myapplication_two_way_gridview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.celerysoft.tablefixheaders.TableFixHeaders;
import com.example.myapplication_two_way_gridview.List.CountList;
import com.example.myapplication_two_way_gridview.List.GuestList;
import com.example.myapplication_two_way_gridview.List.TimeList;
import com.example.myapplication_two_way_gridview.Recycler.HorizontalRecyclerViewAdapter;
import com.example.myapplication_two_way_gridview.Recycler.RecyclerViewAdapter;
import com.example.myapplication_two_way_gridview.Table.MatrixTableAdapter;

import java.util.List;

public class FragmentMain extends Fragment {
    private final static String INDEX = "index";

    private float density;

    private String[][] table1;
    private String[][] table2;
    private List<String> guestList;
    private List<String> countList;
    private List<String> timeListShort;
    private List<String> timeList;

    private View layoutView;
    private View viewTop;
    private View view;

    private RecyclerView recyclerViewTop;
    private HorizontalRecyclerViewAdapter recyclerViewAdapterTop;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private TableFixHeaders tableFixHeaders;
    private MatrixTableAdapter<String> matrixTableAdapter;

    public static FragmentMain newInstance(int index)  {
        FragmentMain fragmentMain = new FragmentMain();
        Bundle b = new Bundle();
        b.putInt(INDEX, index);
        fragmentMain.setArguments(b);
        return fragmentMain;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        guestList = new GuestList().getGuestList();
        countList = new CountList().getCountList();
        timeListShort = new TimeList().getTimeListShort();
        timeList = new TimeList().getTimeList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments().getInt(INDEX) == 1){
            view = inflater.inflate(R.layout.fragment_recycler, null);
            return view;
        } else {
            view = inflater.inflate(R.layout.fragment_grid, null);
            return view;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() == null) return;

        layoutView = view;

        density = getResources().getDisplayMetrics().density;

        setViewAdapterTop(getArguments().getInt(INDEX));
        setViewAdapter(getArguments().getInt(INDEX));

        setRecylerViewTopClickListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        recyclerViewAdapterTop = null;
        tableFixHeaders = null;
    }

    private void setViewAdapterTop(int index){
        if (index != 1){ recyclerViewTop = layoutView.findViewById(R.id.recyclerViewTop); }
        switch (index){
            case 0:{
                recyclerViewTop.setVisibility(View.GONE);
                layoutView.findViewById(R.id.dummyView).setVisibility(View.VISIBLE);
                break;
            }
            case 1:{
                viewTop = layoutView.findViewById(R.id.viewTop);
                viewTop.setBackgroundResource(R.color.gray);
                break;
            }
            case 2:{
                recyclerViewTop.setBackgroundResource(R.color.purple);
                break;
            }
            case 3:{
                recyclerViewTop.setBackgroundResource(R.color.green);
                break;
            }
            case 4:{
                recyclerViewTop.setBackgroundResource(R.color.orange);
                break;
            }
        }
        if (index >= 2){
            recyclerViewTop.setHasFixedSize(true);

            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewTop.setLayoutManager(manager);

            recyclerViewAdapterTop = new HorizontalRecyclerViewAdapter(getContext(), timeListShort, density);
            recyclerViewTop.swapAdapter(recyclerViewAdapterTop, false);
        }
    }

    private void setViewAdapter(int index){
        switch (index) {
            case 0: {
                table1 = new String[guestList.size()][countList.size()];
                tableFixHeaders = layoutView.findViewById(R.id.table);

                matrixTableAdapter = new MatrixTableAdapter<String>(getContext(), table1, (int) density, guestList, countList);
                tableFixHeaders.setRowSelectable(false);
                tableFixHeaders.setAdapter(matrixTableAdapter);
                tableFixHeaders.setOnItemClickListener((parent, view, row, column, id) -> {
                    try{
                        matrixTableAdapter.table[row - 1][column - 1] = "row: "+row + "\n" + " column: " + column;
                        matrixTableAdapter.notifyDataSetChanged();
                    } catch (Exception e){
                        Log.e("Exception: ", String.valueOf(e));
                    }
                });

                break;
            }
            case 1:{
                recyclerView = layoutView.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(rLayoutManager);

                recyclerViewAdapter = new RecyclerViewAdapter(getContext(), guestList.size());
                recyclerView.setAdapter(recyclerViewAdapter);

                break;
            }
            case 2: case 3: case 4:{

                table2 = new String[guestList.size()][timeList.size()];
                TableFixHeaders tableFixHeaders = layoutView.findViewById(R.id.table);

                matrixTableAdapter = new MatrixTableAdapter<String>(getContext(), table2, (int) density, guestList, timeList);
                tableFixHeaders.setRowSelectable(false);
                tableFixHeaders.setAdapter(matrixTableAdapter);

                tableFixHeaders.setOnItemClickListener((parent, view, row, column, id) -> {
                    try{
                        matrixTableAdapter.table[row - 1][column - 1] = "row: "+row + "\n" + " column: " + column;
                        matrixTableAdapter.notifyDataSetChanged();
                    } catch (Exception e){
                        Log.e("Exception: ", String.valueOf(e));
                    }
                });
                break;
            }
        }
    }

    private void setRecylerViewTopClickListener() {
        if (recyclerViewAdapterTop != null){
            recyclerViewAdapterTop.setOnItemClickListener((view, position) -> {
                if (tableFixHeaders == null) {
                    tableFixHeaders = layoutView.findViewById(R.id.table);
                }
                int destination = tableFixHeaders.getChildAt(0).getWidth() * position * 12;
                tableFixHeaders.scrollTo(destination,0);
            });
        }
    }
}

