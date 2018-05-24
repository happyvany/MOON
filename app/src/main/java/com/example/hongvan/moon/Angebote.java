package com.example.hongvan.moon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Angebote extends AppCompatActivity {
    private static final String TAG = "Angebote";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Button prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angebote);
        Log.d(TAG, "onCreate:started");

        prevButton = findViewById(R.id.prvbtn);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartBildschirm();
            }
        });

    }





    public void openStartBildschirm(){
        Intent intent = new Intent(this, com.example.hongvan.moon.MainActivity.class);
        startActivity(intent);
    }
}
