package com.example.hongvan.moon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongvan.moon.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView nxtbutton;
Button meinbutton;

//String stadtName;

    TextView t;
   String spinnerValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* t= (TextView) findViewById(R.id.Title) ;
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        t.setTypeface(myCustomFont);
        */


        Spinner spinner =findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Stadt, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        spinnerValue = spinner.getSelectedItem().toString();
        System.out.println(spinnerValue);

         meinbutton=(Button)findViewById(R.id.listbutton);
        meinbutton.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                openFake();
            }
        });




        nxtbutton = findViewById(R.id.go_button);
        nxtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openAngebote();
                Intent intent = new Intent(MainActivity.this, com.example.hongvan.moon.Angebote.class);

                intent.putExtra("key",spinnerValue);
                // intent.putExtra("key",spinnerValue);
                startActivity(intent);
            }
        });
    }

    public void openAngebote(){
        Intent intent = new Intent(this, com.example.hongvan.moon.Angebote.class);

        intent.putExtra("KEY",spinnerValue);
       // intent.putExtra("key",spinnerValue);
        startActivity(intent);



    }

    public void openFake(){
        Intent intent = new Intent(this, com.example.hongvan.moon.Fake.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





}
