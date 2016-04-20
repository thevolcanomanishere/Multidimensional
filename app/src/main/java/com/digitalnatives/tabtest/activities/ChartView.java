package com.digitalnatives.tabtest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.digitalnatives.tabtest.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ChartView extends AppCompatActivity {

    private LineChart mChart;


    final private String TAG = "ChartView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);

        mChart = (LineChart) findViewById(R.id.chart1);

        Intent intent = getIntent();
        ArrayList<Integer> heartRatesArray = intent.getIntegerArrayListExtra("heartRates");
        ArrayList<Entry> data = new ArrayList<>();

        for (int i = 0; i < heartRatesArray.size(); i++) {
            Float floater = heartRatesArray.get(i).floatValue();
            Log.d(TAG, "Float = " + floater);
            Entry entry = new Entry(floater, i);
            data.add(entry);
        }

        mChart.setDrawGridBackground(false);
        mChart.setDescription("Working");

        mChart.setTouchEnabled(false);

        LineDataSet heartRateSet = new LineDataSet(data, "Heart Rates");
        heartRateSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(heartRateSet);

        ArrayList<String> xVals = new ArrayList<>();
        for(int i = 0; i < heartRatesArray.size(); i++){
            String label = Integer.toString(i);
            xVals.add(label);
        }

        LineData lineData = new LineData(xVals, dataSets);
        mChart.setData(lineData);
        mChart.invalidate();

    }
}
