/*
package com.app.doctorapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.swayamapp.Interface.Location_update;
import com.swayamapp.R;
import com.swayamapp.services.BackgroundManager;
import com.swayamapp.services.LocationService;
import com.swayamapp.services.LockService;
import com.swayamapp.services.ShareLocation2h;

public class LocationFragment extends Fragment implements OnMapReadyCallback {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LinearLayout share_loc;
    Marker mCurrLocationMarker;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;

    @Nullable
    PowerManager powerManager;
    View view;
    Location_update location_update;
    LatLng latLng;
    LocationManager locationManager;



    public LocationFragment(Location_update location_update) {
        this.location_update = location_update;

    }
    public LocationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_location, container, false);
        share_loc = view.findViewById(R.id.share_loc);

        sharedPreferences = view.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

         getActivity().startService(new Intent(getActivity(),LocationService.class));
        ContextCompat.startForegroundService(view.getContext(), new Intent(view.getContext(), LocationService.class));

        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        powerManager = (PowerManager) view.getContext().getSystemService(Context.POWER_SERVICE);
        turnOnGPS();

        SERVICES_();

         locationManager = (LocationManager) view.getContext().getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            turnOnGPS();

        }
        share_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            return;
                        } else {
                            Toast.makeText(view.getContext(), "Please grant location permission", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }

                    LocationManager locationManager = (LocationManager) view.getContext().getSystemService(Context.LOCATION_SERVICE);

                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                        turnOnGPS();
                        return;
                    }

                    if (!BackgroundManager.getInstance().init(view.getContext()).isServiceRunning(ShareLocation2h.class)) {
                        myEdit.putLong("time_remain", 120 * 60 * 1000);
                        myEdit.apply();

                        Toast.makeText(view.getContext(), "Your location is live", Toast.LENGTH_SHORT).show();

                        Intent servicesLocation = new Intent(view.getContext(), ShareLocation2h.class);
                        view.getContext().startService(servicesLocation);


                    }
                } catch (Exception e) {

                }
            }
        });
        return view;
    }

    private void SERVICES_() {

        if (!BackgroundManager.getInstance().init(view.getContext()).isServiceRunning(LockService.class)) {

            ContextCompat.startForegroundService(view.getContext(), new Intent(view.getContext(), LockService.class));
            ContextCompat.startForegroundService(view.getContext(), new Intent(view.getContext(), LocationService.class));
        }

    }




   @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        location_update.update();

        if (latLng != null) {

            updater(latLng);
            Log.d("KJJLLL", String.valueOf(latLng));
        }

    }




    public void updater(LatLng latLng) {
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        MarkerOptions markerOptions = new MarkerOptions();
        Log.d("JKKLJJ", String.valueOf(latLng));
        markerOptions.position(latLng);
        markerOptions.title("You are here");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Math.min(mGoogleMap.getMaxZoomLevel(), 21)));

    }

    public void latlng(LatLng latLng) {

        this.latLng = latLng;
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION:
//                Log.d("njjjbjbjjj", "onRequestPermissionsResult");
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    if (ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
//                    }
//                } else {
//                    Toast.makeText(view.getContext(), "permission denied", Toast.LENGTH_LONG).show();
//                }
//                break;
//
//            case 200:
//
//                Toast.makeText(view.getContext(), "Your location is live", Toast.LENGTH_SHORT).show();
//                myEdit.putLong("time_remain",120*60*1000);
//                myEdit.apply();
//
//                Intent servicesLocation = new Intent(view.getContext(), ShareLocation2h.class);
//                ContextCompat.startForegroundService(view.getContext(), servicesLocation);
//
//                break;
//
//
//        }
//    }


//            case 3:
//
//                if (resultCode == Activity.RESULT_OK) {
//
//                    getCurrentLocation();
//                }
//                break;

    private void turnOnGPS() {


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(new LocationRequest());
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(view.getContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(getActivity(), 10);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

}*/
