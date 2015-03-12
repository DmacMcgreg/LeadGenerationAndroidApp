package com.example.dave.davecollgeproexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InfoFragment extends Fragment{

    OnInfoSaved mCallback;
    EditText name, address, email, notes, phone;
    Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info, container, false);

        name = (EditText)view.findViewById(R.id.txtName);
        address = (EditText)view.findViewById(R.id.txtAddress);
        email = (EditText)view.findViewById(R.id.txtEmail);
        notes = (EditText)view.findViewById(R.id.txtNotes);
        phone = (EditText)view.findViewById(R.id.txtPhone);

        btnSubmit = (Button)view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client client = new Client(name.getText().toString(), address.getText().toString(), email.getText().toString(), notes.getText().toString(), phone.getText().toString());
                mCallback.saveInfo(client);
            }
        });

        return view;
    }

    // Container Activity must implement this interface
    public interface OnInfoSaved {
        public void saveInfo(Client client);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnInfoSaved) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentUpdatedListener");
        }
    }
}
