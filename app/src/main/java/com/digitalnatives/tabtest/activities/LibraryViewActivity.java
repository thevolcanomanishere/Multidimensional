package com.digitalnatives.tabtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView poster = (ImageView) findViewById(R.id.libraryViewPoster);
        TextView movieNameText = (TextView) findViewById(R.id.libViewMovieName);






    }

}
