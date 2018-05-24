package com.example.hongvan.moon;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AngeboteFragment extends Fragment {
    private ImageView img_einkauf;
    private ImageView img_freizeit;
    private ImageView img_medizin;


    public AngeboteFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* img_einkauf = (ImageView) getView().findViewById(R.id.einkaufen);*/
        /*img_freizeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEinkaufen();
            }
        });*/

        /*ImageView img = (ImageView) container.findViewById(R.id.einkaufen);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Einkauf.class)) ;
            }
        });*/


        return inflater.inflate(R.layout.fragment_angebote2, container, false);
    }

    public void openEinkaufen(){
        Intent intent = new Intent(getActivity(), Einkauf.class);
        startActivity(intent);
    }
/*
    public void openFreizeit(){
        Intent intent = new Intent(this, com.example.hongvan.moon.Freizeit.class);
        startActivity(intent);
    }

    public void openMedizin(){
        Intent intent = new Intent(this, com.example.hongvan.moon.Medizin1.class);
    }
    */

}
