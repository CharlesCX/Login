package com.charles.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Addlist extends AppCompatActivity {

    private Button addBtn;
    private EditText itemName;
    private EditText quantity;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);

        //initialize widgets and database
        addBtn = (Button) findViewById(R.id.aButton);
        itemName = (EditText) findViewById(R.id.aItem);
        quantity = (EditText) findViewById(R.id.aQuantity);
        db = new DBHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final Bundle extras = getIntent().getExtras();

        //clicker for add item
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemName.getText().toString();
                String num = quantity.getText().toString();
                db.insertItem(item, Integer.parseInt(num), false);
                Intent i = new Intent(Addlist.this, UserArea.class);
                Addlist.this.startActivity(i);
            }
        });
    }

}
