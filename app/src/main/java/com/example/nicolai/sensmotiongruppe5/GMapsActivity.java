package com.example.nicolai.sensmotiongruppe5;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.Interface.IParent_OnFragmentInteractionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Handler;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class GMapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;


    // Keep track of our markers
    private List<Marker> markers = new ArrayList<Marker>();
    private Marker selectedMarker;

    private Polyline polyLine;
    private PolylineOptions rectOptions = new PolylineOptions();

    View rootView;
    private IParent_OnFragmentInteractionListener mListener;

    Handler handler = new Handler();
    Random random = new Random();
    Runnable runner = new Runnable() {
        @Override
        public void run() {
            setHasOptionsMenu(true);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_gmaps, drawer_layout, false);
        }

        handler.postDelayed(runner, random.nextInt(2000));

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootView;

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;

        float zoomLevel = (float) 18.0;

        gMap.getUiSettings().setMapToolbarEnabled(false);
        gMap.getUiSettings().setAllGesturesEnabled(false);

        //gMap.setMapType(gMap.MAP_TYPE_NORMAL);
        gMap.setMapType(gMap.MAP_TYPE_SATELLITE);
        // Add a marker in Sydney and move the camera
        /*LatLng copenhagen = new LatLng(55.643032, 12.080570);
        gMap.addMarker(new MarkerOptions().position(copenhagen).title("Et sted i Danmark"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(copenhagen));*/

        polyLine = initializePolyLine();

        addDefaultLocations();

        startAnimation();

        //gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.675835, 12.569203), zoomLevel));
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(copenhagen, zoomLevel));


    }

    private void stopAnimation() {
        // TODO Auto-generated method stub

    }

    private void startAnimation() {
        resetMarkers();
        gMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(markers.get(0).getPosition(), 16),
                5000,
                simpleAnimationCancelableCallback);

        currentPt = 0 - 1;
    }

    private void addDefaultLocations() {
        clearMarkers();
        addMarkerToMap(new LatLng(50.961813797827055, 3.5168474167585373), "Her starter du");
        addMarkerToMap(new LatLng(50.96085423274633, 3.517405651509762), "Wow! nu er du kommet hertil");
        addMarkerToMap(new LatLng(50.96020550146382, 3.5177918896079063), "Hvis du kigger til venstre kan du se en måge");
        addMarkerToMap(new LatLng(50.95936754348453, 3.518972061574459), "Godt gået!");
        addMarkerToMap(new LatLng(50.95877285446026, 3.5199161991477013), "");
        addMarkerToMap(new LatLng(50.958179213755905, 3.520646095275879), "");
        addMarkerToMap(new LatLng(50.95901719316589, 3.5222768783569336), "");
        addMarkerToMap(new LatLng(50.95954430150347, 3.523542881011963), "");
        addMarkerToMap(new LatLng(50.95873336312275, 3.5244011878967285), "");
        addMarkerToMap(new LatLng(50.95955781702322, 3.525688648223877), "");
        addMarkerToMap(new LatLng(50.958855004782116, 3.5269761085510254), "Du er nu ankommet til den lille havfrue");
    }


    int currentPt;

    /**
     * Callback that highlights the current marker and keeps animating to the next marker, providing a "next marker" is still available.
     * If we've reached the end-marker the animation stops.
     */
    CancelableCallback simpleAnimationCancelableCallback =
            new CancelableCallback() {

                @Override
                public void onCancel() {
                }

                @Override
                public void onFinish() {

                    if (++currentPt < markers.size()) {

//					double heading = SphericalUtil.computeHeading(googleMap.getCameraPosition().target, markers.get(currentPt).getPosition());
//					System.out.println("Heading  = " + (float)heading);
//					float targetBearing = bearingBetweenLatLngs(googleMap.getCameraPosition().target, markers.get(currentPt).getPosition());
//					System.out.println("Bearing  = " + targetBearing);
//
                        LatLng targetLatLng = markers.get(currentPt).getPosition();

                        CameraPosition cameraPosition =
                                new CameraPosition.Builder()
                                        .target(targetLatLng)
                                        .tilt(currentPt < markers.size() - 1 ? 90 : 0)
                                        //.bearing((float)heading)
                                        .zoom(gMap.getCameraPosition().zoom)
                                        .build();


                        gMap.animateCamera(
                                CameraUpdateFactory.newCameraPosition(cameraPosition),
                                3000,
                                simpleAnimationCancelableCallback);

                        highLightMarker(currentPt);

                    }
                }
            };

    private Location convertLatLngToLocation(LatLng latLng) {
        Location loc = new Location("someLoc");
        loc.setLatitude(latLng.latitude);
        loc.setLongitude(latLng.longitude);
        return loc;
    }

    private float bearingBetweenLatLngs(LatLng begin, LatLng end) {
        Location beginL = convertLatLngToLocation(begin);
        Location endL = convertLatLngToLocation(end);
        return beginL.bearingTo(endL);
    }

    public void toggleStyle() {
        if (GoogleMap.MAP_TYPE_NORMAL == gMap.getMapType()) {
            gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    private Polyline initializePolyLine() {
        rectOptions = new PolylineOptions();
        return gMap.addPolyline(rectOptions);
    }

    /**
     * Add the marker to the polyline.
     */
    private void updatePolyLine(LatLng latLng) {
        List<LatLng> points = polyLine.getPoints();
        points.add(latLng);
        polyLine.setPoints(points);
    }


    /**
     * Adds a marker to the map.
     */
    public void addMarkerToMap(LatLng latLng, String title) {
        Marker marker = gMap.addMarker(new MarkerOptions().position(latLng)
                .title(title)
                .snippet("snippet"));
        markers.add(marker);


    }

    /**
     * Clears all markers from the map.
     */
    public void clearMarkers() {
        gMap.clear();
        markers.clear();
    }

    /**
     * Remove the currently selected marker.
     */
    public void removeSelectedMarker() {
        this.markers.remove(this.selectedMarker);
        this.selectedMarker.remove();
    }

    /**
     * Highlight the marker by index.
     */
    private void highLightMarker(int index) {
        highLightMarker(markers.get(index));
    }

    /**
     * Highlight the marker by marker.
     */
    private void highLightMarker(Marker marker) {
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        marker.showInfoWindow();
        //Utils.bounceMarker(googleMap, marker);
        this.selectedMarker = marker;
    }

    /**
     * Ensure that all markers are using the default red colored icon. (removes any highlighted icons).
     */
    private void resetMarkers() {
        for (Marker marker : this.markers) {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }
    }

}