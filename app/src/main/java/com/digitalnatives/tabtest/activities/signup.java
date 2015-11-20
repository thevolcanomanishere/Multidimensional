package com.digitalnatives.tabtest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.digitalnatives.tabtest.R;

public class signup extends AppCompatActivity {

    private EditText emailEditText;
    private EditText emailConfEditText;
    private EditText passwordEditText;
    private EditText passwordConfEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


    }
}
