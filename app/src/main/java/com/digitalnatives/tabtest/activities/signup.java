package com.digitalnatives.tabtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.R;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class signup extends AppCompatActivity {

    private EditText emailEditText;
    private EditText emailConfEditText;
    private EditText passwordEditText;
    private EditText passwordConfEditText;
    private Button signUpBtn;
    private Intent loadMainActivity;
    private ProgressBar progressBar;

    private String email;
    private String emailConf;
    private String password;
    private String passwordConf;

    private String tag = "SignUpTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailEditText = (EditText) findViewById(R.id.editText1);
        emailConfEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        passwordConfEditText = (EditText) findViewById(R.id.editText4);
        signUpBtn = (Button) findViewById(R.id.button2);
        loadMainActivity = new Intent(this, MainActivity.class);

        //if ((emailEditText.getText().length() || emailConfEditText.getText().length()
        //        || passwordEditText.getText().length() || passwordConfEditText.getText().length() != )



        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.d(tag, "Current User = " + currentUser);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEditText.getText().toString();
                emailConf = emailConfEditText.getText().toString();
                password = passwordEditText.getText().toString();
                passwordConf = passwordConfEditText.getText().toString();

                if (email.equals(emailConf) & password.equals(emailConf)) {
                    //create ParseUser object
                    ParseUser user = new ParseUser();
                    user.setUsername(email);
                    user.setPassword(password);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(com.parse.ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                startActivity(loadMainActivity);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Email or password wrong"
                            , Toast.LENGTH_LONG).show();
                }
            }
        });







    }

}

