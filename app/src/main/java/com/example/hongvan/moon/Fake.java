package com.example.hongvan.moon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Fake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake);


        //ADAPTER, konvertiert array in listitems
        String[] shopping = {"Mueller", "H&M", "Candy"};
        ListAdapter meinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shopping);
        //reference to list

        ListView myListView = (ListView) findViewById(R.id.shopping_list);
        myListView.setAdapter(meinAdapter);
        //gdsaiuhij




    }


/*

    ArrayList<String> da_shopping;
    ArrayList<String> da_freizeit;
    ArrayList<String> da_medizin;

    ArrayList<String> le_shopping;
    ArrayList<String> le_freizeit;
    ArrayList<String> le_medizin;

    ArrayList<String> ff_shopping;
    ArrayList<String> ff_freizeit;
    ArrayList<String> ff_medizin;








        public void fillVec(){
            da_shopping = new ArrayList();
            da_shopping.add("Luisencenter");
            da_shopping.add("REWE");
            da_shopping.add("H&M");

            le_shopping = new ArrayList();
            le_shopping.add("Hoefe am Bruehl");
            le_shopping.add("Zara");
            le_shopping.add("Hugendubel");

            ff_shopping = new ArrayList();
            ff_shopping.add("COS");
            ff_shopping.add("Muji");
            ff_shopping.add("Weekday");

            //____________________________

            da_freizeit = new ArrayList();
            da_freizeit.add("Herrengarten");
            da_freizeit.add("Mathildenhoehe");
            da_freizeit.add("Staatstheater");

            le_freizeit = new ArrayList();
            le_freizeit.add("Zoo");
            le_freizeit.add("Oper");
            le_freizeit.add("Cospudener See");

            ff_freizeit = new ArrayList();
            ff_freizeit.add("Römer");
            ff_freizeit.add("Bergstraße");
            ff_freizeit.add("Chinesischer Garten");

//____________________________

            da_medizin = new ArrayList();
            da_medizin.add("Luisencenter");
            da_medizin.add("REWE");
            da_medizin.add("H&M");

            le_medizin = new ArrayList();
            le_medizin.add("Sankt Georg Krankenhaus");
            le_medizin.add("Uniklinik");
            le_medizin.add("Helios Klinik");

            ff_medizin = new ArrayList();
            ff_medizin.add("St. Elisabethen-Krankenhaus");
            ff_medizin.add("Klinikum Frankfurt Höchst");
            ff_medizin.add("Frankfurter Rotkreuz-Kliniken");


        }










    }

    */
}
