package com.example.myapplication_two_way_gridview;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private float density;

    private FragmentMain fMain;
    private FragmentManager fragmentManager;

    private View beforeSelectedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        density = getResources().getDisplayMetrics().density;

        fMain = FragmentMain.newInstance(0);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rl, fMain).commit();

        Button btn1 = findViewById(R.id.btn1);
        btn1.setY(density * 20);
        beforeSelectedBtn = btn1;

        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);

        btn1.setOnClickListener(view -> {
            beforeSelectedBtn.setY(density * 5);
            beforeSelectedBtn = view;
            view.setY(density * 20);
            fMain = FragmentMain.newInstance(0);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.rl, fMain).commit();
        });
        btn2.setOnClickListener(view -> {
            beforeSelectedBtn.setY(density * 5);
            beforeSelectedBtn = view;
            view.setY(density * 20);
            fMain = FragmentMain.newInstance(1);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.rl, fMain).commit();
        });
        btn3.setOnClickListener(view -> {
            beforeSelectedBtn.setY(density * 5);
            beforeSelectedBtn = view;
            view.setY(density * 20);
            fMain = FragmentMain.newInstance(2);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.rl, fMain).commit();
        });
        btn4.setOnClickListener(view -> {
            beforeSelectedBtn.setY(density * 5);
            beforeSelectedBtn = view;
            view.setY(density * 20);
            fMain = FragmentMain.newInstance(3);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.rl, fMain).commit();
        });
        btn5.setOnClickListener(view -> {
            beforeSelectedBtn.setY(density * 5);
            beforeSelectedBtn = view;
            view.setY(density * 20);
            fMain = FragmentMain.newInstance(4);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.rl, fMain).commit();
        });
    }
}