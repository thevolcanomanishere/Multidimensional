package com.digitalnatives.tabtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.Models.MovieIdResponse;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.User;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LibraryViewActivity extends AppCompatActivity {

    final private String TAG = "LibViewAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView poster = (ImageView) findViewById(R.id.libraryViewPoster);
        TextView movieNameText = (TextView) findViewById(R.id.libViewMovieName);
        Button loadChartBtn = (Button) findViewById(R.id.loadChartBtn);

        Intent intent = getIntent();
        final ArrayList<Integer> heartRates = intent.getIntegerArrayListExtra("heartRates");
        Log.d(TAG, "Heart rates array size = " + heartRates.size());


        loadChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartView.class);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("heartRates", heartRates);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });





    }

}
