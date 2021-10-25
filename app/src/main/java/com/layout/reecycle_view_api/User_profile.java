package com.layout.reecycle_view_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class User_profile extends AppCompatActivity {

    private ImageView img1;
    private TextView name1;
    private TextView email1;
    private TextView age1;
    int image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        img1 = (ImageView) findViewById(R.id.img1);
        name1 = (TextView) findViewById(R.id.name1);
        email1 = (TextView) findViewById(R.id.email1);
        age1 = (TextView) findViewById(R.id.age1);

        Intent intent = getIntent();

        String name = intent.getStringExtra("key_name");
        String email = intent.getStringExtra("key_email");
        String age = intent.getStringExtra("key_age");
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            image = bundle.getInt("key_img");
        }
        img1.setImageResource(image);

        name1.setText(name);
        email1.setText(email);
        age1.setText(age);

    }
}