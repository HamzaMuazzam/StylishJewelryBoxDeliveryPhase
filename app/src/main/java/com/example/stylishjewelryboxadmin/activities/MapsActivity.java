package com.example.stylishjewelryboxadmin.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.stylishjewelryboxadmin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String latitude;
    String longitude;
    String location;

    private int GPS_CODE = 1999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isGPS_Enabled()) {

            getintentvalues();
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);
            }
        }
    }

    private void getintentvalues() {

        latitude = getIntent().getStringExtra("lat");
        longitude = getIntent().getStringExtra("lng");
        location = getIntent().getStringExtra("location");
        Toast.makeText(this, latitude + "\n" + longitude + "\n " + location, Toast.LENGTH_SHORT).show();
    }

    public boolean isGPS_Enabled() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        assert locationManager != null;
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsEnabled) {
            return true;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("GPS Permission").setMessage("Please Enable GPS First")
                    .setPositiveButton("ok", (dialog, which) -> {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, GPS_CODE);

                    }).show();
        }

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double lat = Double.parseDouble(latitude);
        double lng = Double.parseDouble(longitude);

        if (lat != 0.0 && lng != 0.0) {
            LatLng latlng = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(latlng).title("Destiny"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17));
        } else {
            addressToLatLng();
        }


    }

    public void addressToLatLng() {

        Geocoder geocoder = null;
        geocoder = new Geocoder(this, Locale.getDefault());
        // add 1 for single reszult
        // this method is for human readable address to latitudes and longitudes

        try {
            List<Address> list = null;
            list = geocoder.getFromLocationName(location, 1);
            if (list.size() > 0) {
                Address address = list.get(0);

                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                mMap.addMarker(new MarkerOptions().title("Destination").position(latLng));
                Toast.makeText(this, address.getLocality(), Toast.LENGTH_SHORT).show();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

