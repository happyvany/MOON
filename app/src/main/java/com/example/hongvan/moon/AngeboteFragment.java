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
        final String stadt = (String) this.getArguments().get("key");

        View rootView = inflater.inflate(R.layout.fragment_angebote2,
                container, false);
        img_einkauf = rootView.findViewById(R.id.einkaufen);
        img_einkauf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Einkauf.class);

                intent.putExtra("key",stadt);
                // intent.putExtra("key",spinnerValue);
                startActivity(intent);
                //openEinkaufen();
            }
        });

        img_freizeit =  rootView.findViewById(R.id.freizeit);
        img_freizeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Freizeit.class);

                intent.putExtra("key",stadt);
                // intent.putExtra("key",spinnerValue);
                startActivity(intent);
                //openFreizeit();
            }
        });


        img_medizin =  rootView.findViewById(R.id.medizin);
        img_medizin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Medizin1.class);

                intent.putExtra("key",stadt);
                // intent.putExtra("key",spinnerValue);
                startActivity(intent);
                //openMedizin();
            }
        });


        return rootView;
    }

    public void openEinkaufen(){
        Intent intent = new Intent(getActivity(), Einkauf.class);

        startActivity(intent);
    }

    public void openFreizeit(){
        Intent intent = new Intent(getActivity(), Freizeit.class);
        startActivity(intent);
    }

    public void openMedizin(){
        Intent intent = new Intent(getActivity(), Medizin1.class);
        startActivity(intent);
    }


}
