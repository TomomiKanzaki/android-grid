package com.example.myapplication_two_way_gridview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragmentMain extends Fragment {
    private final static String INDEX = "index";
    private final static int GUEST_NUMBER = 10;

    private float density;

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

    private RecyclerView gridViewTop;
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;

    public static FragmentMain newInstance(int index) {
        FragmentMain fragmentMain = new FragmentMain();
        Bundle b = new Bundle();
        b.putInt(INDEX, index);
        fragmentMain.setArguments(b);
        return fragmentMain;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        countList = new CountList().getCountList();
        timeListShort = new TimeList().getTimeListShort();
        timeList = new TimeList().getTimeList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        switch (getArguments().getInt(INDEX)){
            case 0:case 2:case 3:case 4:{
                view = inflater.inflate(R.layout.fragment_grid, null);
                break;
            }
            case 1:{
                view = inflater.inflate(R.layout.fragment_recycler, null);
                break;
            }
        }
        return view;
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
        setGridViewItemClickListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        recyclerViewAdapterTop = null;
        gridViewAdapter = null;
    }

    private void setViewAdapterTop(int index){
        if (index != 1){ recyclerViewTop = layoutView.findViewById(R.id.recyclerViewTop); }
        switch (index){
            case 0:{
                recyclerViewTop.setBackgroundResource(R.color.skyblue);

                ViewGroup.LayoutParams lp = recyclerViewTop.getLayoutParams();
                lp.height = (int) (density * 10);
                recyclerViewTop.setLayoutParams(lp);

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
//            manager.setOrientation(GridLayoutManager.HORIZONTAL);
            recyclerViewTop.setLayoutManager(manager);

            recyclerViewAdapterTop = new HorizontalRecyclerViewAdapter(getContext(), timeListShort, density);
            recyclerViewTop.swapAdapter(recyclerViewAdapterTop, false);
        }
    }

    private void setViewAdapter(int index){
        switch (index) {
            case 0: {
                gridView = layoutView.findViewById(R.id.gridView);

                ViewGroup.LayoutParams lp = gridView.getLayoutParams();
                gridView.setNumColumns(12);
                lp.width = (int) (density * 100 * 12);
                gridView.setLayoutParams(lp);

                gridViewAdapter = new GridViewAdapter(getContext(), countList, density, GUEST_NUMBER);
                gridView.setAdapter(gridViewAdapter);

                break;
            }
            case 1:{
                recyclerView = layoutView.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(rLayoutManager);

                recyclerViewAdapter = new RecyclerViewAdapter(getContext(), GUEST_NUMBER);
                recyclerView.setAdapter(recyclerViewAdapter);

                break;
            }
            case 2: case 3: case 4:{
                gridView = layoutView.findViewById(R.id.gridView);

                ViewGroup.LayoutParams lp = gridView.getLayoutParams();
                gridView.setNumColumns(193);
                lp.width = (int) (density * 100 * 193);
                gridView.setLayoutParams(lp);

                gridViewAdapter = new GridViewAdapter(getContext(), timeList, density, GUEST_NUMBER);
                gridView.setAdapter(gridViewAdapter);

                break;
            }
        }
    }

    private void setGridViewItemClickListener(){
        if (gridView == null) return;
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        });
    }

    private void setRecylerViewTopClickListener() {
        if (recyclerViewAdapterTop != null){
            recyclerViewAdapterTop.setOnItemClickListener(new HorizontalRecyclerViewAdapter.onItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Log.i("onRecyclerClicked", String.valueOf(view.getX() * position));
                    HorizontalScrollView hsv2 = layoutView.findViewById(R.id.hsv2);
                    hsv2.post(() -> hsv2.smoothScrollTo((int) (density * 100 * position * 12), 0));
                }
            });
        }
    }
}

