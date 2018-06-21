package com.example.hongvan.moon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Freizeit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_freizeit);
        String message = getIntent().getStringExtra("key");
        TextView messageT =(TextView)findViewById(R.id.stadtname);
        messageT.setText(message);
    }
}
