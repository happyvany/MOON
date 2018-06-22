package com.example.hongvan.moon;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Freizeit extends AppCompatActivity {
    String[] freizeit = {"Herrngarten", "Zoologischer Garten", "Luisenplatz"};
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_freizeit);
        String message = getIntent().getStringExtra("key");
        TextView messageT =(TextView)findViewById(R.id.stadtname);
        messageT.setText(message);



        //ADAPTER, konvertiert array in listitems

      /*  ListAdapter meinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, freizeit){
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

        final ListView myListView = (ListView) findViewById(R.id.freizeit_list);


        final JSONparser freizeitjson = new JSONparser();
        freizeitjson.getHospital_name();
        final ArrayList<String> favoritenListe = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, freizeitjson.getCinema_name());
        myListView.setAdapter(arrayAdapter);
        myListView.setAdapter(arrayAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) myListView.getItemAtPosition(position);
                freizeitjson.getFavoritenliste().add(itemValue);

                // Show Alert
                Toast.makeText(getApplicationContext(), itemValue+" in Favoriten gespeichert!" , Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}
