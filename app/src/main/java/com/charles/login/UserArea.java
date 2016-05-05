package com.charles.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.widget.LoginButton;



public class UserArea extends AppCompatActivity {


    private Button add;
    private Button done;
    private DBHelper db;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        add = (Button) findViewById(R.id.addBtn);
        done = (Button) findViewById(R.id.done);
        fragmentManager = getFragmentManager();
        Log.d("db", "Created db");
        db = new DBHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();

        final Bundle extras = getIntent().getExtras();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserArea.this, Addlist.class);
                UserArea.this.startActivity(i);

            }
        });

        //clicker for add all items into database
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserArea.this, Completed.class);
                UserArea.this.startActivity(i);
            }
        });

        //get and list All uncompleted item
        Cursor res = db.getAllUncompletedItems();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        while (!res.isAfterLast()) {
            ItemFragment currentFragment = new ItemFragment();
            String item = res.getString(res.getColumnIndex(Values.ITEM_COLUMN_ITEM_NAME));
            int num = res.getInt(res.getColumnIndex(Values.ITEM_COLUMN_QUANTITY));
            int id = res.getInt(res.getColumnIndex("id"));
            Log.d("item", item);
            Log.d("item", String.valueOf(num));
            currentFragment.setId(id);
            currentFragment.setItemName(item);
            currentFragment.setItemQuantity(num);
            fragmentTransaction.add(R.id.fragmentContainer, currentFragment);
            res.moveToNext();

        }
        fragmentTransaction.commit();

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    Intent i = new Intent(UserArea.this, Login.class);
                    UserArea.this.startActivity(i);
                }
            }
        };
    }
}
