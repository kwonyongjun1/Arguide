package com.example.arguide_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapView;

public class NavigationFrag extends Fragment {

    private final String APK ="l7xx147189bbb393444f872ea1a88881c3af";
    private TMapView tMapView;

    private View view;
    private LinearLayout tmap;
    private Context context = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_navigation_frag, container,false);

        tmap = view.findViewById(R.id.tmapview);
        tMapView = new TMapView(view.getContext());
        tMapView.setSKTMapApiKey(APK);
        tmap.addView(tMapView);

        setGps();

        tMapView.setIconVisibility(true);



        return view;
    }

    private final LocationListener mLocationListener = new LocationListener() { //현위치
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if(location != null){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tMapView.setLocationPoint(127.150969,36.850485);
                tMapView.setCenterPoint(127.150969,36.850485);

                //36.850485, 127.150969
            }
        }
    };

    public void setGps(){ //현위치
        final LocationManager lm = (LocationManager)this.getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,1,mLocationListener);
    }
}