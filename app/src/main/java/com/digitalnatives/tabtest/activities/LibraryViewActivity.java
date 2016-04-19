package com.digitalnatives.tabtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LibraryViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String movieName = intent.getStringExtra("movieName");
        String description = intent.getStringExtra("description");
        String releaseDate = intent.getStringExtra("releaseDate");
        String posterPath = intent.getStringExtra("posterPath");
        ArrayList<Integer> heartRates = intent.getIntegerArrayListExtra("heartRates");
        Toast.makeText(LibraryViewActivity.this, movieName, Toast.LENGTH_SHORT).show();




    }

}
