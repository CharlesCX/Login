package com.charles.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Intent;

public class register extends AppCompatActivity {

    private Button reg;
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText phone;
    private DBHelper db;
    public static final String emailP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String phoneP = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //instantiate widgets
        reg = (Button) findViewById(R.id.bRegister);
        email = (EditText) findViewById(R.id.xEmail);
        password = (EditText) findViewById(R.id.xPassword);
        name = (EditText) findViewById(R.id.xName);
        phone = (EditText) findViewById(R.id.xPhone);
        db = new DBHelper(this);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("")) {
                    Toast.makeText(register.this, "please enter your email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().equals("")) {
                    Toast.makeText(register.this, "please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("register", phone.getText().toString());
                Pattern emailPattern = Pattern.compile(emailP);
                Matcher emailMatcher = emailPattern.matcher(email.getText());

                if (emailMatcher.matches()) {
                    db.insert(email.getText().toString(), password.getText().toString(), name.getText().toString(), phone.getText().toString());

                    Intent i = new Intent(register.this, Login.class);
                    register.this.startActivity(i);
                }

                //if email address's format is invalid
                else if (!emailMatcher.matches()) {
                    Toast.makeText(register.this, "invalid email address", Toast.LENGTH_SHORT).show();
                    Log.d("register", "invalid email");
                }

            }
        });

    }
}