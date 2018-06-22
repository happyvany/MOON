package com.example.hongvan.moon;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Medizin1 extends AppCompatActivity {

    String[] hospital = {"Alicen Krankenhaus", "Uniklinik", "Delfin Apotheke"};
    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medizin1);
        String message = getIntent().getStringExtra("key");
        TextView messageT =(TextView)findViewById(R.id.stadtname);
        messageT.setText(message);

        //ADAPTER, konvertiert array in listitems

      /*  ListAdapter meinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hospital){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);



                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.BLACK);

                return view;
            }
        };*/
        //reference to list

        ListView myListView = (ListView) findViewById(R.id.hospital_list);


        JSONparser hospitaljson = new JSONparser();
        hospitaljson.getHospital_name();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, hospitaljson.getHospital_name());
        myListView.setAdapter(arrayAdapter);
    }
}
