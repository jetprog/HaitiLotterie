package lotto509.com.lotto509.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.Iterator;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.adapters.ArrayAdapterSoir;
import lotto509.com.lotto509.models.TirageMidi;

/**
 * Created by Carly Baja on 8/24/2017.
 */

public class MidiFragment extends Fragment {

    private ArrayList<TirageMidi> listTirage;
    private ArrayAdapterSoir arrayAdapterTirage;
    ListView lvTirageMidi;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.midi_fragment, parent, false);


        lvTirageMidi = (ListView) v.findViewById(R.id.lvTirageMidi);
        listTirage = new ArrayList<>();
        arrayAdapterTirage = new ArrayAdapterSoir(getActivity(), listTirage);
        lvTirageMidi.setAdapter(arrayAdapterTirage);



        Backendless.Persistence.of( TirageMidi.class).find(new AsyncCallback<BackendlessCollection<TirageMidi>>(){
            @Override
            public void handleResponse( BackendlessCollection<TirageMidi> foundTirage )
            {
                Iterator<TirageMidi> tirageIterator = foundTirage.getCurrentPage().iterator();
                while (tirageIterator.hasNext())
                {
                    TirageMidi newTirage = tirageIterator.next();
                    listTirage.add(newTirage);

                }
                arrayAdapterTirage.notifyDataSetChanged();


            }
            @Override
            public void handleFault( BackendlessFault fault )
            {

            }

        });

        return v;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}

