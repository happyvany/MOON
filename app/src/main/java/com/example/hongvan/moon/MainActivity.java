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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.hongvan.moon.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText stadtName;
    Button daCinema;
    String stadt;
    TextView text;

    //_________________________START VANYS NOMINATIM EINBINDUNG_________________________
    //__________________________________________________________________________________

    final int MY_PERMISSIONS_STORAGE_INTENRET = 1;

    RequestService mService = null;
    boolean mBound = false;

    //--------------------------    service Verbindung einrichten
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // an den RequestService binden,
            // service-Objekt casting auf IBinder und LocalService instance erhalten
            RequestService.RequestServiceBinder binder =
                    (RequestService.RequestServiceBinder) service;
            mService = binder.getService();
            // callback setzen
            mService.setCallback(getHandler());

            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
    //--------------------------

    //--------------------------handler einrichten:
    //  callbacks vereinbaren für service binding,
    // weiterleiten an bindService() für ergebnismitteilung
    private Handler getHandler() {
        final Handler callbackHandler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String sfile = (String) bundle.get( RequestService.FILEPATH );
                String uniqueId = (String) bundle.get( RequestService.UNIQUEID );
                String note = (String) bundle.get( RequestService.NOTIFICATION );
                Toast.makeText(MainActivity.this, uniqueId +" file: " + sfile+" Bytes: "+note, Toast.LENGTH_LONG).show();
            }// handleMessage
        };
        return callbackHandler;
    }
    //--------------------------








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



        // Permission grant/gewähren
        String[] permissions =
                {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
        ActivityCompat.requestPermissions(this, permissions,
                MY_PERMISSIONS_STORAGE_INTENRET);

        // Bind to RequestService
        Intent myIntent = new Intent(this, RequestService.class);
        // startService(myIntent); oder alternativ:
        bindService(myIntent, mConnection, Context.BIND_AUTO_CREATE);

        Button b;
        b = (Button) findViewById(R.id.da_cinema);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String url;
                String fPath;
                String fileName;
                // Testbeispiel
                url = "https://nominatim.openstreetmap.org/search?bounded=1&format=json&polygon=0&addressdetails=1&q=cinema+in+darmstadt";
                fPath = "NZSE";
                fileName = "cinema.txt";

                if (mService != null)
                    mService.runURLDownload("cinema", url, fPath, fileName);
                //else
                //    ... Hinweis
            }
        });




        Spinner spinner =findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Stadt, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  String item = parent.getItemAtPosition(position).toString();
                                                  spinnerValue = item;

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }
                                          });


        //spinnerValue =Spinner spinner.getSelectedItem().toString();
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



        stadtName=(EditText) findViewById(R.id.editStadt);

        daCinema=(Button) findViewById(R.id.da_cinema);

        text=(TextView) findViewById(R.id.textout);


daCinema.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        stadt=stadtName.getText().toString();

    }
});

text.setText(stadt);



    }





    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_STORAGE_INTENRET:
            {
                // wenn die anfrage gecancelled wird, sind die Ergnisfelder leer.
                if (grantResults.length > 0)
                {
                    for ( int grant = 0; grant < grantResults.length; grant++)
                    {
                        if ( grantResults[grant] == PackageManager.PERMISSION_GRANTED)
                            // Permissions wurden gewährt
                            System.out.println (permissions[grant]+" vorhanden");
                            // Zugriffe ausführen ..
                        else
                            System.out.println (permissions[grant]+"  n i c h t  vorhanden");
                        // Permissions werden abgelehnt,
                        // spezifische Zugriffe werden nicht ausgeführt
                    }
                }
                return;
            }
            // Prüfung anderer/weiterer Permissions
        }
    }

    @Override
    public void finish() {
        System.out.println("******  bye bye");
        unbindService(mConnection);
        super.finish();
    }


    //_________________________ENDE VANYS NOMINATIM EINBINDUNG_________________________
    //__________________________________________________________________________________

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
