package com.example.nicolai.sensmotiongruppe5;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.Interface.IParent_OnFragmentInteractionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
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
    private List<LatLng> points = new ArrayList<LatLng>();
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

        gMap.setMapType(gMap.MAP_TYPE_NORMAL);
        //gMap.setMapType(gMap.MAP_TYPE_SATELLITE);
        // Add a marker in Sydney and move the camera
        /*LatLng copenhagen = new LatLng(55.643032, 12.080570);
        gMap.addMarker(new MarkerOptions().position(copenhagen).title("Et sted i Danmark"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(copenhagen));*/

        if (googleMap!=null) {

            polyLine = initializePolyLine();

            addDefaultLocations();

            startAnimation();
        }


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
        addMarkerToMap(new LatLng(55.6914211, 12.5949792), "Her starter ruten.", true);
        addMarkerToMap(new LatLng(55.6914302, 12.5949792), "Har du husket vandrestøvlerne?", false);
        addMarkerToMap(new LatLng(55.6923343, 12.5949524), "Hvis du kigger til venstre kan du se en måge", false);
        addMarkerToMap(new LatLng(55.6928694, 12.5951777), "Godt gået!", true);
        addMarkerToMap(new LatLng(55.6934016, 12.5954513), "", false);
        addMarkerToMap(new LatLng(55.6935618, 12.5956605), "", false);
        addMarkerToMap(new LatLng(55.6935104, 12.5958322), "Godt gået!", false);
        addMarkerToMap(new LatLng(55.6935346, 12.5965457), "", true);
        addMarkerToMap(new LatLng(55.6936283, 12.5972538), "Move it!", false);
        addMarkerToMap(new LatLng(55.6936314, 12.5977634), "", false);
        addMarkerToMap(new LatLng(55.6935316, 12.5984768), "", true);

        addMarkerToMap(new LatLng(55.6934288, 12.5989435), "Godt gået!", false);
        addMarkerToMap(new LatLng(55.6933955, 12.599083), "", false);
        addMarkerToMap(new LatLng(55.6932232, 12.5990562), "Godt gået!", true);
        addMarkerToMap(new LatLng(55.6931325, 12.5992171), "", false);
        addMarkerToMap(new LatLng(55.6930841, 12.5991849), "", false);
        addMarkerToMap(new LatLng(55.6930267, 12.5992118), "", true);
        addMarkerToMap(new LatLng(55.6929843, 12.599244), "", false);
        addMarkerToMap(new LatLng(55.6929178, 12.5992064), "Du er nu ankommet til den lille havfrue", true);

        drawPolyLineOnMap(points);
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

    // Draw polyline on map
    public void drawPolyLineOnMap(List<LatLng> list) {
        PolylineOptions polyOptions = new PolylineOptions();
        polyOptions.color(Color.RED);
        polyOptions.width(5);
        polyOptions.addAll(list);

        //gMap.clear();
        gMap.addPolyline(polyOptions);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : list) {
            builder.include(latLng);
        }

        final LatLngBounds bounds = builder.build();

        //BOUND_PADDING is an int to specify padding of bound.. try 100.
        /*CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, BOUND_PADDING);
        googleMap.animateCamera(cu);*/
    }

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
    public void addMarkerToMap(LatLng latLng, String title, boolean markerVisible) {

        Marker marker = gMap.addMarker(new MarkerOptions().position(latLng)
                .title(title)
                .snippet("snippet"));
        marker.setVisible(markerVisible);
        points.add(latLng);
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