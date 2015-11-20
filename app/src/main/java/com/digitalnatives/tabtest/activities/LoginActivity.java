package com.digitalnatives.tabtest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.digitalnatives.tabtest.R;
import com.parse.Parse;
import com.parse.ParseObject;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private String tag = "LoginActityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //parse enable Datastore test 1
        Log.d(tag, "The class is run first");

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "CqyuqfAKTmdVZ5EwzsFTTWfs4xjVz2UjGrNcCNTj", "8gEZBpBpGN0FJT0E3A5foYusxoiPucfdmZRhIzQ9");

        //parse Test
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);



        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }






}

