package com.example.hongvan.moon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hongvan.moon.R;

public class MainActivity extends AppCompatActivity {
    private Button nxtbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nxtbutton = findViewById(R.id.button);
        nxtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAngebote();
            }
        });
    }

    public void openAngebote(){
        Intent intent = new Intent(this, com.example.hongvan.moon.Angebote.class);
        startActivity(intent);
    }
}
