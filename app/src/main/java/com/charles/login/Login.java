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

public class Login extends AppCompatActivity {



    TextView Tregister;
    Button login;
    EditText email;
    EditText password;
    DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        //instantiate widgets
        login = (Button) findViewById(R.id.blogin);
        Tregister = (TextView) findViewById(R.id.xRegister);
        email = (EditText) findViewById(R.id.xEmail);
        password = (EditText) findViewById(R.id.xPassword);
        db = new DBHelper(this);

        //clicker for login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Login.this.email.getText().toString();
                String password = Login.this.password.getText().toString();
                if(db.matches(email, password)) {
                    Intent i = new Intent(Login.this, UserArea.class);
                    Login.this.startActivity(i);
                }
                else {
                    Toast.makeText(Login.this, "Email or password not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Tregister.setOnClickListener(new View.OnClickListener() {
            //move to register activity
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, register.class);
                Login.this.startActivity(i);
            }
        });


    }
}