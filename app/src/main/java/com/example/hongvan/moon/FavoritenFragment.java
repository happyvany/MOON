package com.example.hongvan.moon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritenFragment extends Fragment {


    public FavoritenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favoriten3,
                container, false);
        ListView myListView = rootView.findViewById(R.id.favoritenliste);
       JSONparser favoritenliste = new JSONparser();
       favoritenliste.getFavoritenliste();

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, favoritenliste.getFavoritenliste());
        myListView.setAdapter(arrayAdapter);
        return rootView;
    }

}
