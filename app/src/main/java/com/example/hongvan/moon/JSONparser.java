package com.example.hongvan.moon;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JSONparser {

    private static ArrayList<String> hospital_name;
    private static ArrayList<String> cinema_name;
    private static ArrayList<String> supermarkt_name;

    public static ArrayList<String> getHospital_name() {
        return hospital_name;
    }

    public static ArrayList<String> getCinema_name() {
        return cinema_name;
    }

    public static ArrayList<String> getSupermarkt_name() {
        return supermarkt_name;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void load_hospital(View.OnClickListener c) {
        hospital_name = new ArrayList<>();

        String fname = "hospital.txt";
        String alleausgaben = "";
        try{
            File myFile = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/NZSE/"+fname);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader =
                    new BufferedReader( new InputStreamReader( fIn, StandardCharsets.UTF_8.name()) );
            String line;
            while((line = myReader.readLine()) != null) {
                alleausgaben += line;
            }

            JSONArray jsonArray = new JSONArray(alleausgaben);

            for( int i = 0; i < jsonArray.length(); i++ ) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //System.out.println(jsonObject.toString());

                String name = jsonObject.getString("address");
                //String place_id = jsonObject.getString("hospital");
                //System.out.println(name);
                JSONObject jsonObject1 = new JSONObject(name);
                if(jsonObject1.has("hospital")) {
                    String hospital = jsonObject1.getString("hospital");
                    hospital_name.add(hospital);

                }
            }
            for(int j = 0; j < hospital_name.size(); ++j){
                System.out.println(hospital_name.get(j));
            }

        } // try
        catch(Exception e) {
           // Toast.makeText((Context) c, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void load_cinema(View.OnClickListener c) {
        cinema_name = new ArrayList<>();

        String fname = "cinema.txt";
        String alleausgaben = "";
        try{
            File myFile = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/NZSE/"+fname);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader =
                    new BufferedReader( new InputStreamReader( fIn, StandardCharsets.UTF_8.name()) );
            String line;
            while((line = myReader.readLine()) != null) {
                alleausgaben += line;
            }

            JSONArray jsonArray = new JSONArray(alleausgaben);

            for( int i = 0; i < jsonArray.length(); i++ ) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //System.out.println(jsonObject.toString());

                String name = jsonObject.getString("address");
                //String place_id = jsonObject.getString("hospital");
                //System.out.println(name);
                JSONObject jsonObject1 = new JSONObject(name);
                if(jsonObject1.has("cinema")) {
                    String cinema = jsonObject1.getString("cinema");

                    cinema_name.add(cinema);

                }
            }
            for(int j = 0; j < cinema_name.size(); ++j){
                System.out.println(cinema_name.get(j));
            }

        } // try
        catch(Exception e) {
            // Toast.makeText((Context) c, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void load_supermarkt(View.OnClickListener c) {
        supermarkt_name = new ArrayList<>();

        String fname = "supermarkt.txt";
        String alleausgaben = "";
        try{
            File myFile = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/NZSE/"+fname);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader =
                    new BufferedReader( new InputStreamReader( fIn, StandardCharsets.UTF_8.name()) );
            String line;
            while((line = myReader.readLine()) != null) {
                alleausgaben += line;
            }

            JSONArray jsonArray = new JSONArray(alleausgaben);

            for( int i = 0; i < jsonArray.length(); i++ ) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //System.out.println(jsonObject.toString());

                String name = jsonObject.getString("address");
                //String place_id = jsonObject.getString("hospital");
                //System.out.println(name);
                JSONObject jsonObject1 = new JSONObject(name);
                if(jsonObject1.has("supermarket")) {
                    String supermarkt = jsonObject1.getString("supermarket");

                    supermarkt_name.add(supermarkt);
                }
            }
            for(int j = 0; j < supermarkt_name.size(); ++j){
                System.out.println(supermarkt_name.get(j));
            }

        } // try
        catch(Exception e) {
            // Toast.makeText((Context) c, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
