package com.malfoy.leblanko.villes_emploi.Fragment;


import android.app.Fragment;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.malfoy.leblanko.villes_emploi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tribu extends android.support.v4.app.Fragment {


    private GoogleMap googleMap;
    private View view;


    public Tribu() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if(view==null)
            view = inflater.inflate(R.layout.fragment_tribu, container, false);

        try {
            if (googleMap == null)
            {
                googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            //Affiche la position actuelle
            googleMap.setMyLocationEnabled(true);

            GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange (Location location) {

                }
            };
            googleMap.setOnMyLocationChangeListener(myLocationChangeListener);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


}
