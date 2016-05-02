package com.charles.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Intent;
import android.database.Cursor;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.ProfileTracker;
import com.facebook.Profile;

public class Login extends AppCompatActivity {



    private TextView Tregister;
    private Button login;
    private EditText email;
    private EditText password;
    private DBHelper db;
    private LoginButton fbButton;
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FaceBook sdk initialization
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        //instantiate widgets
        login = (Button) findViewById(R.id.blogin);
        Tregister = (TextView) findViewById(R.id.xRegister);
        email = (EditText) findViewById(R.id.xEmail);
        password = (EditText) findViewById(R.id.xPassword);
        db = new DBHelper(this);

        fbButton = (LoginButton) findViewById(R.id.fbButton);
        callbackManager = CallbackManager.Factory.create();
        accessToken = AccessToken.getCurrentAccessToken();

        //start accessToken Tracker
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        accessTokenTracker.startTracking();

        //start profile token tracker
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Intent i = new Intent(Login.this, UserArea.class).putExtra("fb", true);
                if(currentProfile != null) {
                    i.putExtra(Values.USER_COLUMN_NAME, currentProfile.getName());
                    Login.this.startActivity(i);
                }
            }
        };
        profileTracker.startTracking();
        Log.i("onCreate", "call onCtreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart", "call onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        //clicker for login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (accessToken != null) {
                    Toast.makeText(Login.this, "Login already", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = Login.this.email.getText().toString();
                String password = Login.this.password.getText().toString();
                if(db.matches(email, password)) {

                    Cursor temp = db.getData(email);
                    temp.moveToFirst();

                    String name = temp.getString(temp.getColumnIndex(Values.USER_COLUMN_NAME));
                    Intent i = new Intent(Login.this, UserArea.class).putExtra(Values.USER_COLUMN_NAME, name).putExtra("fb", false);;
                    Login.this.startActivity(i);
                }
                else {
                    Toast.makeText(Login.this, "Email or password not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //clicker for register
        Tregister.setOnClickListener(new View.OnClickListener() {
            //move to register activity
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, register.class);
                Login.this.startActivity(i);
            }
        });

        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        Log.i("onResume", "call onResume");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause", "call onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop", "call onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
        Log.i("onDestroy", "call onDestroy");
    }




}