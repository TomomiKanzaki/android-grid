package com.example.myapplication_two_way_gridview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentMain extends Fragment {
    private final static String INDEX = "index";
    private final static int GUEST_NUMBER = 10;

    private float density;

    private View view;

    private HorizontalScrollView hsv1;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private GridView gridViewTop;
    private GridView gridView;
    private GridViewAdapterTop gridViewAdapterTop;
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

        density = getResources().getDisplayMetrics().density;

        setGridViewAdapterTop(view, getArguments().getInt(INDEX));
        setGridViewTopItemClickListener(view);

        setGridViewAdapter(view, getArguments().getInt(INDEX));
        setGridViewItemClickListener(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        gridViewAdapterTop = null;
        gridViewAdapter = null;
    }

    private void setGridViewAdapterTop(View v, int index){
        hsv1 = v.findViewById(R.id.hsv1);
        switch (index){
            case 0:{
                hsv1.setBackgroundResource(R.color.skyblue);

                ViewGroup.LayoutParams lp = hsv1.getLayoutParams();
                lp.height = (int) (density * 10);
                hsv1.setLayoutParams(lp);

                gridViewTop = v.findViewById(R.id.gridViewTop);
                gridViewTop.setVisibility(View.GONE);

                break;
            }
            case 1:{
                hsv1.setBackgroundResource(R.color.gray);
                break;
            }
            case 2:{
                hsv1.setBackgroundResource(R.color.purple);
                break;
            }
            case 3:{
                hsv1.setBackgroundResource(R.color.green);
                break;
            }
            case 4:{
                hsv1.setBackgroundResource(R.color.orange);
                break;
            }
        }
        if (index >= 2){
            gridViewTop = v.findViewById(R.id.gridViewTop);
            gridViewAdapterTop = new GridViewAdapterTop(getContext(), new TimeList().getTimeListShort());
            gridViewTop.setAdapter(gridViewAdapterTop);
        }
    }

    private void setGridViewAdapter(View v, int index){
        switch (index) {
            case 0: {
                gridView = v.findViewById(R.id.gridView);

                ViewGroup.LayoutParams lp = gridView.getLayoutParams();
                gridView.setNumColumns(12);
                lp.width = (int) (density * 100 * 12);
                gridView.setLayoutParams(lp);

                gridViewAdapter = new GridViewAdapter(getContext(), new CountList().getCountList(), density, GUEST_NUMBER);
                gridView.setAdapter(gridViewAdapter);

                break;
            }
            case 1:{
                recyclerView = v.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);

                RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(rLayoutManager);

                recyclerViewAdapter = new RecyclerViewAdapter(getContext(), GUEST_NUMBER);
                recyclerView.setAdapter(recyclerViewAdapter);

                break;
            }
            case 2: case 3: case 4:{
                gridView = v.findViewById(R.id.gridView);

                ViewGroup.LayoutParams lp = gridView.getLayoutParams();
                gridView.setNumColumns(193);
                lp.width = (int) (density * 100 * 193);
                gridView.setLayoutParams(lp);

                gridViewAdapter = new GridViewAdapter(getContext(), new TimeList().getTimeList(), density, GUEST_NUMBER);
                gridView.setAdapter(gridViewAdapter);

                break;
            }
        }
    }

    private void setGridViewTopItemClickListener(View v){
        if (gridViewTop == null) return;
        gridViewTop.setOnItemClickListener((parent, view, position, id) -> {
            HorizontalScrollView hsv2 = v.findViewById(R.id.hsv2);
            hsv2.post(() -> hsv2.smoothScrollTo((int) (view.getX() * 12), 0));
        });
    }
    private void setGridViewItemClickListener(View v){
        if (gridView == null) return;
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        });
    }
}

