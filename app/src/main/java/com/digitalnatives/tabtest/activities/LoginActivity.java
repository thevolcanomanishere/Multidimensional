package com.digitalnatives.tabtest.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.digitalnatives.tabtest.ApiConfig;
import com.digitalnatives.tabtest.MainActivity;
import com.digitalnatives.tabtest.R;
import com.digitalnatives.tabtest.SharedPrefs;
import com.digitalnatives.tabtest.User;
import com.digitalnatives.tabtest.VolleyController;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;

    private String username;
    private String password;

    private TextView signUpLoad;
    private Button signInButton;
    private Button skipLoginBtn;

    private Intent signUpIntent;
    private Intent mainActivityIntent;
    private String tag = "LoginActityTag";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mainActivityIntent = new Intent(this, MainActivity.class);

        if(SharedPrefs.getUserToken(LoginActivity.this).length() > 0){
            startActivity(mainActivityIntent);
            String token = SharedPrefs.getUserToken(LoginActivity.this);
            Log.d("Login Token check = ", token);
        }

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        signInButton = (Button)findViewById(R.id.signin);

//        //TODO:remove in final
//        skipLoginBtn = (Button) findViewById(R.id.skipLoginBtn);
//        skipLoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String testUsername = "test1234";
//                String testPassword = "hellothere";
//                loginWithMongo(testUsername, testPassword);
//            }
//        });


        signUpLoad = (TextView) findViewById(R.id.signUp);
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
                username = mEmailView.getText().toString().trim();
                password = mPasswordView.getText().toString().trim();
                loginWithMongo(username, password);
            }
        });




    }


    private void loginWithMongo(final String username, final String password){

        Log.d("LoginFired", "The login function fired");

        //setup network checker
        final ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


        //check network connection first
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected){

            if(password.length() > 4){

                StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConfig.getBASE_URL() + ApiConfig.getLOGIN(),
                        new Response.Listener<String>() {
                            //
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject responseObject = new JSONObject(response);
                                    Boolean error = responseObject.getBoolean("error");
                                    if(!error){
                                        String token = responseObject.getString("token");
                                        User.getInstance().setToken(token);
                                        SharedPrefs.setUserToken(LoginActivity.this, token);
                                        startActivity(mainActivityIntent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, responseObject.get("message").toString(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", username);
                        params.put("password", password);
                        return params;
                    }
                };

                VolleyController.getInstance().addToRequestQueue(stringRequest);
            } else {
                Toast.makeText(LoginActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(LoginActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }




}

