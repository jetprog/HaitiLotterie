package lotto509.com.lotto509.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import lotto509.com.lotto509.R;
import lotto509.com.lotto509.activity.ActivityHome;
import lotto509.com.lotto509.adapters.ArrayAdapterMidi;
import lotto509.com.lotto509.models.Tchala;
import lotto509.com.lotto509.models.TirageMidi;

/**
 * Created by Carly Baja on 8/24/2017.
 */

public class MidiFragment extends Fragment {

    private ArrayList<TirageMidi> listTirage;
    private ArrayAdapterMidi arrayAdapterTirage;
    ListView lvTirageMidi;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.midi_fragment, parent, false);


        lvTirageMidi = (ListView) v.findViewById(R.id.lvTirageMidi);
        listTirage = new ArrayList<>();
        arrayAdapterTirage = new ArrayAdapterMidi(getActivity(), listTirage);
        lvTirageMidi.setAdapter(arrayAdapterTirage);


        loadTirage();



        return v;
    }

    public void loadTirage(){


        String url = "http://192.168.1.12:8888/Lotto509/src/routes/tirageMidi.php/api/tirageMidi";

        AsyncHttpClient client = new AsyncHttpClient();



        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tirageResults = null;

                try {
                    tirageResults = response.getJSONArray("tirageMidi");
                    listTirage.addAll(TirageMidi.fromJSONArray(tirageResults));
                    arrayAdapterTirage.notifyDataSetChanged();
                    Log.d("DEBUG", listTirage.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}

