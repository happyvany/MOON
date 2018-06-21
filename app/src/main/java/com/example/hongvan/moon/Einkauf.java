package com.example.hongvan.moon;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Einkauf extends AppCompatActivity {
    String[] shopping = {"Mueller", "H&M", "Candy"};
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einkauf);
        listView = (ListView)findViewById(R.id.shopping_list);

        String message = getIntent().getStringExtra("key");
        TextView messageT =(TextView)findViewById(R.id.stadtname);
        messageT.setText(message);


        //ADAPTER, konvertiert array in listitems

        ListAdapter meinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shopping){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);



                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);

                return view;
            }
        };
        //reference to list

        ListView myListView = (ListView) findViewById(R.id.shopping_list);
        myListView.setAdapter(meinAdapter);
        //gdsaiuhij
    }


}
