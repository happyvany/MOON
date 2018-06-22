package com.example.hongvan.moon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Angebote extends AppCompatActivity {
    private static final String TAG = "Angebote";

    //vars

    private Button prevButton;
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private AngeboteFragment angeboteFragment;
    private FavoritenFragment favoritenFragment;
    private EinstellungenFragment einstellungenFragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategorie);
        Log.d(TAG, "onCreate:started");




       /* prevButton = findViewById(R.id.prvbtn);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartBildschirm();
            }
        });
*/




        //PARAMETERANNAHME

        String message = getIntent().getStringExtra("key");
        TextView messageT =(TextView)findViewById(R.id.testtest);

        messageT.setText(message);
        Bundle bundle = new Bundle();

        bundle.putString("key", message );


        //____________________________________


        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.bottomn_navigation);

        angeboteFragment = new AngeboteFragment();
        angeboteFragment.setArguments(bundle);

        favoritenFragment = new FavoritenFragment();
        favoritenFragment.setArguments(bundle);

      /*  einstellungenFragment = new EinstellungenFragment();
        einstellungenFragment.setArguments(bundle);*/

        setFragment(angeboteFragment);

       /* img_einkauf = (ImageView) findViewById(R.id.einkaufen);
        img_einkauf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartBildschirm();
            }
        });*/

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_kat:
                        /*mMainNav.setItemBackgroundResource(R.color.colorPrimary);*/
                        setFragment(angeboteFragment);
                        return true;
                    case R.id.nav_fav:
                       /* mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);*/
                        setFragment(favoritenFragment);
                        return true;
                    case R.id.nav_einst:
                       /* mMainNav.setItemBackgroundResource(R.color.colorTheme);*/
                       openStartBildschirm();
                        //setFragment(einstellungenFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }



    public void openStartBildschirm(){
        Intent intent = new Intent(this, com.example.hongvan.moon.MainActivity.class);
        startActivity(intent);
    }
}
