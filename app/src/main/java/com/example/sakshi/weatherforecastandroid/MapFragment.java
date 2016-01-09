package com.example.sakshi.weatherforecastandroid;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatDialogFragment;

import com.google.android.gms.maps.model.LatLng;
import com.hamweather.aeris.communication.AerisCallback;
import com.hamweather.aeris.communication.AerisEngine;
import com.hamweather.aeris.communication.EndpointType;
import com.hamweather.aeris.maps.AerisMapView;
import com.hamweather.aeris.maps.AerisMapView.AerisMapType;
import com.hamweather.aeris.maps.MapViewFragment;
import com.hamweather.aeris.maps.interfaces.OnAerisMapLongClickListener;
import com.hamweather.aeris.model.AerisResponse;
import com.hamweather.aeris.tiles.AerisTile;



/**
 * Created by Sakshi on 07-12-2015.
 */
public class MapFragment extends MapViewFragment implements OnAerisMapLongClickListener,AerisCallback {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interactive_layout, container, false);
        AerisEngine.initWithKeys(this.getString(R.string.aeries_client_id), this.getString(R.string.aeries_secret_key), "com.example.sakshi.weatherforecastandroid");
        double lat  = MapActivity.latitude;
        double lng = MapActivity.longitude;


        mapView = (AerisMapView)view.findViewById(R.id.aerisfragment_map);
        mapView.init(savedInstanceState, AerisMapType.GOOGLE);
        //Location location = new Location("");
        //location.setLatitude(lat);
        //location.setLongitude(lng);
        mapView.moveToLocation(new LatLng(lat, lng), 10);
        mapView.addLayer(AerisTile.RADSAT);
        mapView.setOnAerisMapLongClickListener(this);

        return view;
    }
    @Override
    public void onResult(EndpointType endpointType, AerisResponse aerisResponse) {

    }

    @Override
    public void onMapLongClick(double v, double v1) {

    }
}
