package com.example.sakshi.weatherforecastandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamweather.aeris.communication.AerisCallback;
import com.hamweather.aeris.communication.AerisEngine;
import com.hamweather.aeris.communication.EndpointType;
import com.hamweather.aeris.maps.AerisMapView;
import com.hamweather.aeris.maps.MapViewFragment;
import com.hamweather.aeris.maps.interfaces.OnAerisMapLongClickListener;
import com.hamweather.aeris.model.AerisResponse;
import com.hamweather.aeris.tiles.AerisTile;

import org.json.JSONException;
import org.json.JSONObject;
public class MapActivity extends AppCompatActivity {
    public static double latitude;
    public static double longitude;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lat= "";
        String lng = "";
        Intent interaction = getIntent();
        Bundle b = interaction.getExtras();
        String resultdatamap = (String)b.get("Moredetailsdata");

        try {
            JSONObject jsonObject = new JSONObject(resultdatamap);
            lat = jsonObject.getString("latitude");
            lng = jsonObject.getString("longitude");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        latitude = Double.parseDouble(lat);
        longitude = Double.parseDouble(lng);
        setContentView(R.layout.activity_map);



        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
         MapFragment fragment = new MapFragment();
        fragmentTransaction.add(R.id.fragment_map_id, fragment);
        fragmentTransaction.commit();
    }

}
