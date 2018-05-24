package com.example.hongvan.moon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Einkauf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einkauf);


        //ADAPTER, konvertiert array in listitems
        String[] shopping = {"Mueller", "H&M", "Candy"};
        ListAdapter meinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shopping);
        //reference to list

        ListView myListView = (ListView) findViewById(R.id.shopping_list);
        myListView.setAdapter(meinAdapter);
        //gdsaiuhij
    }
}
