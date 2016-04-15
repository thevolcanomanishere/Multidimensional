package com.digitalnatives.tabtest.activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.ApiConfig;
import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.SharedPrefs;
import com.digitalnatives.tabtest.User;
import com.digitalnatives.tabtest.VolleyController;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText emailConfEditText;
    private EditText passwordEditText;
    private EditText passwordConfEditText;
    private Button signUpBtn;
    private Button mongoSignUp;
    private Intent loadMainActivity;
    private ProgressBar progressBar;

    private String username;
    private String email;
    private String emailConf;
    private String password;
    private String passwordConf;


    private String tag = "SignUpTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        usernameEditText = (EditText) findViewById(R.id.userName);
        emailEditText = (EditText) findViewById(R.id.editText1);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        passwordConfEditText = (EditText) findViewById(R.id.editText4);
        mongoSignUp = (Button) findViewById(R.id.mongoSignUpBtn);

        loadMainActivity = new Intent(this, MainActivity.class);


        mongoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUpUser();
            }
        });

//        signUpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                email = emailEditText.getText().toString();
//                emailConf = emailConfEditText.getText().toString();
//                password = passwordEditText.getText().toString();
//                passwordConf = passwordConfEditText.getText().toString();
//
//                if (email.equals(emailConf) & password.equals(passwordConf)) {
//                    //create ParseUser object
//                    ParseUser user = new ParseUser();
//                    user.setUsername(email);
//                    user.setPassword(password);
//
//                    user.signUpInBackground(new SignUpCallback() {
//                        public void done(com.parse.ParseException e) {
//                            if (e == null) {
//                                // Show a simple Toast message upon successful registration
//                                startActivity(loadMainActivity);
//                            } else {
//                                Toast.makeText(getApplicationContext(),
//                                        "Sign up Error", Toast.LENGTH_LONG)
//                                        .show();
//                            }
//                        }
//                    });
//
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "Email or password wrong"
//                            , Toast.LENGTH_LONG).show();
//                }
//            }
//        });


    }

    private void signUpUser(){

        //setup network checker
        final ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


        //check network connection first
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected){

            username = usernameEditText.getText().toString().trim();
            email = emailEditText.getText().toString().trim();
            password = passwordEditText.getText().toString().trim();
            passwordConf = passwordConfEditText.getText().toString().trim();

            if(password.equals(passwordConf)){

                StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConfig.getBASE_URL() + ApiConfig.getREGISTER(),
                        new Response.Listener<String>() {
//
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject responseObject = new JSONObject(response);
                                    Boolean error = responseObject.getBoolean("error");
                                    if(!error){
                                        String token = responseObject.getString("token");
                                        SharedPrefs.setUserToken(signup.this, token);
//                                        Toast.makeText(signup.this, token, Toast.LENGTH_SHORT).show();
                                        startActivity(loadMainActivity);
                                    } else {
                                        Toast.makeText(signup.this, responseObject.get("message").toString(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                                error.getStackTrace();
                                Toast.makeText(signup.this, "Volley error " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", username);
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };

                VolleyController.getInstance().addToRequestQueue(stringRequest);
            }

        } else {
            Toast.makeText(signup.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

}

