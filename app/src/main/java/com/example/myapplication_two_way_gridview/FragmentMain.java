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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_two_way_gridview.Grid.GridViewAdapter;
import com.example.myapplication_two_way_gridview.Grid.GridViewAdapterLeft;
import com.example.myapplication_two_way_gridview.List.CountList;
import com.example.myapplication_two_way_gridview.List.GuestList;
import com.example.myapplication_two_way_gridview.List.TimeList;
import com.example.myapplication_two_way_gridview.Recycler.HorizontalRecyclerViewAdapter;
import com.example.myapplication_two_way_gridview.Recycler.RecyclerViewAdapter;
import com.example.myapplication_two_way_gridview.Scroll.ObservableScrollView;
import com.example.myapplication_two_way_gridview.Scroll.ScrollViewListener;

import java.util.List;

public class FragmentMain extends Fragment implements ScrollViewListener {
    private final static String INDEX = "index";

    private float density;

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

    private GridView gridView;
    private GridView gridViewLeft;
    private GridViewAdapter gridViewAdapter;
    private GridViewAdapterLeft gridViewAdapterLeft;

    private ObservableScrollView observableSV1;
    private ObservableScrollView observableSV2;

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

        setObserVableScrollView(getArguments().getInt(INDEX));

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
        gridViewAdapterLeft = null;
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
                gridView = layoutView.findViewById(R.id.gridView);
                gridViewLeft = layoutView.findViewById(R.id.gridViewLeft);

                ViewGroup.LayoutParams lp = gridView.getLayoutParams();
                gridView.setNumColumns(12);
                lp.width = (int) (density * 100 * 12);
                lp.height = (int)(density * 100 * guestList.size() + 1);
                gridView.setLayoutParams(lp);

                lp = gridViewLeft.getLayoutParams();
                lp.height = (int)(density * 100 * (guestList.size() + 1));
                gridViewLeft.setLayoutParams(lp);

                gridViewAdapter = new GridViewAdapter(getContext(), countList, density, guestList.size());
                gridView.setAdapter(gridViewAdapter);

                gridViewAdapterLeft = new GridViewAdapterLeft(getContext(), guestList, density);
                gridViewLeft.setAdapter(gridViewAdapterLeft);

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
                gridView = layoutView.findViewById(R.id.gridView);
                gridViewLeft = layoutView.findViewById(R.id.gridViewLeft);

                ViewGroup.LayoutParams lp = gridView.getLayoutParams();
                gridView.setNumColumns(timeList.size());
                lp.width = (int) (density * 100 * timeList.size());
                lp.height = (int)(density * 100 * guestList.size());
                gridView.setLayoutParams(lp);

                lp = gridViewLeft.getLayoutParams();
                lp.height = (int)(density * 100 * (guestList.size() + 1));
                gridViewLeft.setLayoutParams(lp);

                gridViewAdapter = new GridViewAdapter(getContext(), timeList, density, guestList.size());
                gridView.setAdapter(gridViewAdapter);

                gridViewAdapterLeft = new GridViewAdapterLeft(getContext(), guestList, density);
                gridViewLeft.setAdapter(gridViewAdapterLeft);

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
                    HorizontalScrollView hsv2 = layoutView.findViewById(R.id.hsv2);
                    hsv2.post(() -> hsv2.smoothScrollTo((int) (density * 100 * position * 12), 0));
                }
            });
        }
    }

    private void setObserVableScrollView(int index){
        if (index == 1)return;
        observableSV1 = layoutView.findViewById(R.id.observable_sv1);
        observableSV1.setScrollViewListener(this);
        observableSV2 = layoutView.findViewById(R.id.observable_sv2);
        observableSV2.setScrollViewListener(this);
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == observableSV1){
            observableSV2.smoothScrollTo(x, y);
        } else if (scrollView == observableSV2){
            observableSV1.smoothScrollTo(x, y);
        }
    }
}

