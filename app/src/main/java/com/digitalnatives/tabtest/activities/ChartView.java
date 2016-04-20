package com.digitalnatives.tabtest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.digitalnatives.tabtest.R;

import java.util.ArrayList;

public class ChartView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);

        Intent intent = getIntent();
        ArrayList<Integer> heartRatesArray = intent.getIntegerArrayListExtra("heartRates");

        
    }
}
