package com.example.dave.davecollgeproexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PricesFragment extends Fragment{

    UpdatePriceSheet mCallback;

    TextView regPrice, discountPrice, saveHours, sidingPrice, eavesPrice;
    Button sRegular, sDiscount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.price_layout, container, false);

        regPrice = (TextView)view.findViewById(R.id.txtRegPrice);
        discountPrice = (TextView)view.findViewById(R.id.txtSpecialPrice);
        saveHours = (TextView)view.findViewById(R.id.txtSaveHours);

        sidingPrice = (TextView)view.findViewById(R.id.txtSidingPrice);
        eavesPrice = (TextView)view.findViewById(R.id.txtEavesPrice);

        sRegular = (Button)view.findViewById(R.id.sRegular);
        sDiscount = (Button)view.findViewById(R.id.sDiscount);

        regPrice.setVisibility(View.INVISIBLE);
        sidingPrice.setVisibility(View.INVISIBLE);
        eavesPrice.setVisibility(View.INVISIBLE);
        discountPrice.setVisibility(View.INVISIBLE);
        saveHours.setVisibility(View.INVISIBLE);

        sRegular.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                requestUpdate(0);
                return false;
            }
        });

        sDiscount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                requestUpdate(1);
                return false;
            }
        });

        return view;
    }

    // Container Activity must implement this interface
    public interface UpdatePriceSheet {
        public void updatePriceSheet(int type);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (UpdatePriceSheet) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement UpdatePriceSheet");
        }
    }

    public void requestUpdate(int type) {
        mCallback.updatePriceSheet(type);
    }
    public void updateDiscount(Estimate estimate){

        discountPrice.setVisibility(View.VISIBLE);
        saveHours.setVisibility(View.VISIBLE);
        discountPrice.setText("$" + (estimate.getDiscountOne()));
        saveHours.setText("This saves you " + estimate.getWindowHours() + " hours and $" + (estimate.getInflateOne() - estimate.getDiscountOne()));

    }
    public void updateRegular(Estimate estimate) {
        regPrice.setVisibility(View.VISIBLE);
        sidingPrice.setVisibility(View.VISIBLE);
        eavesPrice.setVisibility(View.VISIBLE);
        regPrice.setText("$" + (estimate.getInflateOne()));
        sidingPrice.setText("Eaves : $" + estimate.getEavesPrice());
        eavesPrice.setText("Siding: $" + estimate.getSidingPrice());
    }
}
