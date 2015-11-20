package com.digitalnatives.tabtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private String username;
    private String password;
    private Button signUpLoad;
    private Button signInButton;
    private Intent signUpIntent;


    private Intent mainActivityIntent;
    private String tag = "LoginActityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //parse enable Datastore test 1
        Log.d(tag, "The class is run first");


        //Set and check if their is a user logged in already
        final ParseUser currentUser = ParseUser.getCurrentUser();
        Log.d(tag, "current user = " + currentUser);


        mainActivityIntent = new Intent(this, MainActivity.class);

        //check current user. send to main
        if (currentUser != null){
            startActivity(mainActivityIntent);
        }

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        signInButton = (Button)findViewById(R.id.signin);

        signUpLoad = (Button) findViewById(R.id.button3);
        signUpIntent = new Intent(this, signup.class);

        signUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(signUpIntent);
            }
        });



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEmailView.length() > 1){
                        username = mEmailView.getText().toString();
                        password = mPasswordView.getText().toString();

                        ParseUser.logInInBackground(username, password, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user != null){
                                    startActivity(mainActivityIntent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Wrong username or password combo", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                } else {
                    Log.d(tag, "Email or password too short");
                    Toast.makeText(getApplicationContext(), "Your email or password is too short", Toast.LENGTH_SHORT).show();
                }


            }
        });







    }






}

