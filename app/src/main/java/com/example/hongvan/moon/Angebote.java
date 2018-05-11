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

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");
        mImageUrls.add("https://photos-5.dropbox.com/t/2/AADlqhzWXoeRhwjGrvhKQBm2nq48-Jh9U5DBrf_wBbJAUw/12/17675179/jpeg/32x32/1/_/1/2/freizeit.bmp/EJvOlg0YtZ4WIAIoAg/KNuyV_PpM0v4Rf_4c6KLhRUxAP2N-HNq-ldj5zarGOI?size=2048x1536&size_mode=3");
        mNames.add("Freizeit");

        mImageUrls.add("https://photos-6.dropbox.com/t/2/AAD_gPRsLDPrOcCJ0LAlWZwFECvHIYqfdG1YbwUGoydMBA/12/17675179/jpeg/32x32/1/_/1/2/einkaufen.bmp/EJvOlg0YtZ4WIAIoAg/L0VImr98dCeAnMD7T18L962cpK3OS4fe3iTcqpP0T6c?size=2048x1536&size_mode=3");
        mNames.add("Einkaufen");

        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/e/e3/Logo_BILD.svg");
        mNames.add("Medizin");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerView.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void openStartBildschirm(){
        Intent intent = new Intent(this, com.example.hongvan.moon.MainActivity.class);
        startActivity(intent);
    }
}
