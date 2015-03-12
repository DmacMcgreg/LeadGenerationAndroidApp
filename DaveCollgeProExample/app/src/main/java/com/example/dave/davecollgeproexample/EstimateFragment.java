package com.example.dave.davecollgeproexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by clive on 23-May-14.
 * * www.101apps.co.za
 */
public class EstimateFragment extends Fragment{

    OnEstimateUpdate mCallback;

    private Estimate estimate;

    // Declare Hours for each type
    float windowHours, eavesHours, sidingHours, screenHours,
            oneSideHours, twoSideHours = 0;

    //Declare Prices
    float windowsPrice, eavesPrice, sidingPrice, screensPrice, inflateOne,
            discountOne, inflateTwo, discountTwo = 0;

    //Declare each edit Text
    EditText mnWindows1, mnScreens, mnFtEaves, mnFtSiding, mnTotal;

    //Declare Buttons
    Button btnUpdate, btn1SideMore, btn1SideLess, btnScreensMore, btnScreensLess,
            btnEavesMore, btnEavesLess, btnSidingMore, btnSidingLess;

    SeekBar sbEaves, sbSiding;

    //Declare number of each type
    float windows1Side, ftEaves, ftSiding, screens = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.estimate_layout, container, false);


        setup(view);


        return view;
    }

    private void setup(View view){

        mnWindows1 = (EditText)view.findViewById(R.id.mnWindows1);
        mnScreens = (EditText)view.findViewById(R.id.mnScreens);
        mnFtEaves = (EditText)view.findViewById(R.id.mnFtEaves);
        mnFtSiding = (EditText)view.findViewById(R.id.mnFtSiding);

        btn1SideMore = (Button)view.findViewById(R.id.btnM1Side);
        btn1SideLess = (Button)view.findViewById(R.id.btnL1Side);

        btnScreensMore= (Button)view.findViewById(R.id.btnMScreens);
        btnScreensLess= (Button)view.findViewById(R.id.btnLScreens);

        btnEavesMore= (Button)view.findViewById(R.id.btnMEaves);
        btnEavesLess= (Button)view.findViewById(R.id.btnLEaves);

        btnSidingMore= (Button)view.findViewById(R.id.btnMSiding);
        btnSidingLess= (Button)view.findViewById(R.id.btnLSiding);

        sbEaves = (SeekBar)view.findViewById(R.id.sbEaves);
        sbSiding = (SeekBar)view.findViewById(R.id.sbSiding);

        mnTotal = (EditText)view.findViewById(R.id.mnTotal);
        btnUpdate = (Button)view.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculate();

            }
        });
        btn1SideMore.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                windows1Side++;
                mnWindows1.setText("" + windows1Side);

            }
        });

        btn1SideLess.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                oneSideLess();
            }
        });

        btnScreensMore.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                screensMore();
            }
        });
        btnScreensLess.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                screensLess();
            }
        });

        btnEavesMore.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                eavesMore();
            }
        });
        btnEavesLess.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                eavesLess();
            }
        });

        btnSidingMore.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                sidingMore();
            }
        });
        btnSidingLess.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                sidingLess();
            }
        });

        sbEaves.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                ftEaves = arg1;
                mnFtEaves.setText("" + ftEaves);

            }
            public void onStartTrackingTouch(SeekBar arg0) {}
            public void onStopTrackingTouch(SeekBar arg0) {}

        });

        sbSiding.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                ftSiding = arg1;
                mnFtSiding.setText("" + ftSiding);
            }
            public void onStartTrackingTouch(SeekBar arg0) {}
            public void onStopTrackingTouch(SeekBar arg0) {}
        });


    }

    public void calculate(){
        //get values from edit text boxes
        windows1Side = Float.parseFloat(mnWindows1.getText().toString());
        screens = Float.parseFloat(mnScreens.getText().toString());
        ftEaves = Float.parseFloat(mnFtEaves.getText().toString());
        ftSiding = Float.parseFloat(mnFtSiding.getText().toString());


        //calculate Hours
        windowHours = (float)((windows1Side * 3)/60);
        screenHours = (float)((screens * 2) / 60) ;
        eavesHours = (float) ((ftEaves * 1.1)/60);
        sidingHours = (float) ((ftSiding * 0.25)/60);

        oneSideHours = (windowHours + screenHours + 1);
        twoSideHours = (windowHours*2 + screenHours + 1);

        //calculate prices
        windowsPrice = windowHours * 34;
        screensPrice = screenHours *34;
        eavesPrice = eavesHours * 34;
        sidingPrice = sidingHours * 34;

        //inflate and discount prices for 1 side
        inflateOne = (float) Math.round(Math.ceil((((windowsPrice + screensPrice) * 1.2) + 34) * 1.13));
        discountOne = (float) Math.round(Math.ceil((windowsPrice + screensPrice + 34) * 1.13));

        //inflate and discount prices for 2 side
        inflateTwo = (float) Math.round(Math.ceil(((float) ((windowsPrice + screensPrice) * 2) * 1.2 + 44) * 1.13));
        discountTwo = (float) Math.round(Math.ceil((windowsPrice * 2 + screensPrice + 44) * 1.13));

        estimate = new Estimate();
        estimate.setHours(windowHours, screenHours, eavesHours, sidingHours);
        estimate.setCounts(windows1Side, screens,ftEaves,ftSiding);
        estimate.setPrices(inflateOne, discountOne, eavesPrice, sidingPrice);
        setEstimate(estimate);

        mnTotal.setText("" + estimate.getDiscountOne());
        mCallback.updateEstimate(estimate);
    }

    public void oneSideMore(){
        windows1Side++;
        mnWindows1.setText("" + windows1Side);

    }
    public void oneSideLess(){
        windows1Side--;
        mnWindows1.setText("" + windows1Side);
    }
    public void screensMore(){
        screens++;
        mnScreens.setText("" + screens);
    }
    public void screensLess(){
        screens--;
        mnScreens.setText("" + screens);
    }
    public void eavesMore(){
        ftEaves +=3;
        mnFtEaves.setText("" + ftEaves);
    }
    public void eavesLess(){
        ftEaves -=3;
        mnFtEaves.setText("" + ftEaves);
    }
    public void sidingMore(){
        ftSiding +=10;
        mnFtSiding.setText("" + ftSiding);
    }
    public void sidingLess(){
        ftSiding -=10;
        mnFtSiding.setText("" + ftSiding);
    }

    public Estimate getEstimate() {
        return estimate;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    // Container Activity must implement this interface
    public interface OnEstimateUpdate {
        public void updateEstimate(Estimate estimate);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnEstimateUpdate) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnEstimateSaved");
        }
    }
}
