package com.example.dave.davecollgeproexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends ActionBarActivity implements InfoFragment.OnInfoSaved,
        EstimateFragment.OnEstimateUpdate, PricesFragment.UpdatePriceSheet, DataFragment.UpdateClients,
        ConnectionCallbacks, OnConnectionFailedListener{

    private float discountPrice, regularPrice = 0;
    private Estimate estimate;
    PricesFragment pricesFragment;
    InfoFragment infoFragment;
    EstimateFragment estimateFragment;
    DataFragment dataFragment;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    SQLiteDatabase db;
    private ClientDbAdapter cDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpTabs(savedInstanceState);
        cDbHelper = new ClientDbAdapter(this);
        cDbHelper.open();

        buildGoogleApiClient();
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    private void setUpTabs(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        ActionBar.Tab tab_one = actionBar.newTab();
        ActionBar.Tab tab_two = actionBar.newTab();
        ActionBar.Tab tab_three = actionBar.newTab();
        ActionBar.Tab tab_four = actionBar.newTab();
        infoFragment = new InfoFragment();
        tab_one.setText("Info")
                .setContentDescription("Collect Info")
                .setTabListener(
                        new MyTabListener<InfoFragment>(
                                infoFragment));

        estimateFragment = new EstimateFragment();
        tab_two.setText("Estimate")
                .setContentDescription("Perform Estimate")
                .setTabListener(
                        new MyTabListener<EstimateFragment>(
                                estimateFragment));

        pricesFragment = new PricesFragment();
        tab_three
                .setText("Prices")
                .setContentDescription("Show Prices")
                .setTabListener(
                        new MyTabListener<PricesFragment>(
                                pricesFragment));
        dataFragment = new DataFragment();
        tab_four
                .setText("Clients")
                .setContentDescription("Show Clients")
                .setTabListener(
                        new MyTabListener<DataFragment>(
                                dataFragment));

        actionBar.addTab(tab_one);
        actionBar.addTab(tab_two);
        actionBar.addTab(tab_three);
        actionBar.addTab(tab_four);

        if (savedInstanceState != null) {
            //Log.i(TAG, "setting selected tab from saved bundle");
//            get the saved selected tab's index and set that tab as selected
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tabIndex", 0));
        }
    }

    @Override
    public void saveInfo(Client client) {

        //client.setLocation(mLastLocation);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        cDbHelper.createClient(client.getName(), client.getAddress());
        Log.i("Latitude", String.valueOf(mLastLocation.getLatitude()));
        Log.i("Longitude", String.valueOf(mLastLocation.getLongitude()));

        Context context = getApplicationContext();


        CharSequence text = "Latitude : " + String.valueOf(mLastLocation.getLatitude()) + "  Longitude: " +String.valueOf(mLastLocation.getLongitude());
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void updateEstimate(Estimate estimate) {
        setEstimate(estimate);

    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public float getRegularPrice() {
        return regularPrice;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }
    public Estimate getEstimate() {
        return estimate;
    }

    @Override
    public void updatePriceSheet(int type) {

        if(getEstimate() != null){
            if(type == 1){
                pricesFragment.updateDiscount(getEstimate());
            }else {
                pricesFragment.updateRegular(getEstimate());
            }
        } else{
            Toast.makeText(getApplicationContext(), "Please update Estimate first!", Toast.LENGTH_SHORT);
        }


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void updateClients() {
        dataFragment.fillClientData(cDbHelper);
    }


}
