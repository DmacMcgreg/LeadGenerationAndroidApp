package com.example.dave.davecollgeproexample;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Dave on 3/12/2015.
 */
public class DataFragment extends ListFragment{

    UpdateClients mCallback;
    EditText name, address, email, notes, phone;

    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;

    ClientDbAdapter cDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.client_list, container, false);
        mCallback.updateClients();
        return view;
    }


    // Container Activity must implement this interface
    public interface UpdateClients {
        public void updateClients();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (UpdateClients) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentUpdatedListener");
        }
    }

    public void fillClientData(ClientDbAdapter cDbHelper) {
        this.cDbHelper = cDbHelper;
        Cursor clientsCursor = cDbHelper.fetchAllClients();
        getActivity().startManagingCursor(clientsCursor);


        // Create an array to specify the fields we want to display in the list
        String[] from = new String[]{ClientDbAdapter.KEY_NAME, ClientDbAdapter.KEY_ADDRESS};

        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.txtName, R.id.txtAddress};

        // Now create a simple cursor adapter and set it to display
        android.widget.SimpleCursorAdapter clients =
               new SimpleCursorAdapter(getActivity(), R.layout.client_row, clientsCursor, from, to);
        setListAdapter(clients);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Context context = getActivity();

        Cursor clientsCursor = cDbHelper.fetchClient(id);
        getActivity().startManagingCursor(clientsCursor);

        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
