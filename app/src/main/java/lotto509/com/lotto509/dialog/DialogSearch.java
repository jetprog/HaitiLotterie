package lotto509.com.lotto509.dialog;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import lotto509.com.lotto509.R;
import lotto509.com.lotto509.activity.ActivityHome;
import lotto509.com.lotto509.activity.FilterActivity;
import lotto509.com.lotto509.adapters.ArrayAdapterMidi;
import lotto509.com.lotto509.adapters.ArrayAdapterTirage;
import lotto509.com.lotto509.models.TirageMidi;
import lotto509.com.lotto509.models.TirageSoir;

/**
 * Created by Carly Baja on 9/8/2017.
 */

public class DialogSearch extends DialogFragment{

    private ArrayList<TirageMidi> listTirage;
    private ArrayAdapterMidi arrayAdapterTirage;
    ListView lvTirageMidi;
    private ArrayList<TirageSoir> listTirageSoir;
    private ArrayAdapterTirage arrayAdapterTirageSoir;
    ListView lvTirageSoir;

    Spinner spFilter;
    static EditText edtDateSearch;
    Button search;


    public DialogSearch() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static DialogSearch newInstance(String title) {
        DialogSearch frag = new DialogSearch();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_dialog_search, container);

        spFilter = (Spinner) v.findViewById(R.id.spChoiceRangeDate);
        edtDateSearch = (EditText) v.findViewById(R.id.edtDateTirage);
        search = (Button) v.findViewById(R.id.btSearchTirage);

        edtDateSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "Date Tirage");
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = edtDateSearch.getText().toString();

               // searchTirage(date);

                if (spFilter.getSelectedItem().equals("Date")){

                    FragmentManager fm = getFragmentManager();
                    DialogResult result = DialogResult.newInstance(date, date);
                    result.show(fm, "fragment_alert");
                    dismiss();
                }
                else if(spFilter.getSelectedItem().equals("Jour mois")){

                    Intent i = new Intent(getContext(), FilterActivity.class);
                    i.putExtra("dateSearch", date);
                    startActivity(i);
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(), "Verfiez voitre choix", Toast.LENGTH_SHORT).show();
                }


                /*Intent i = new Intent(getContext(), FilterActivity.class);
                i.putExtra("dateSearch", date);
                startActivity(i);*/

            }
        });

        return v;


    }



    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            final Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");



            edtDateSearch.setText(simpleDateFormat.format(c.getTime()));


        }

    }


    private void searchTirage(String userQuery){

        listTirage = new ArrayList<>();
        arrayAdapterTirage = new ArrayAdapterMidi(getContext(), listTirage);
        lvTirageMidi.setAdapter(arrayAdapterTirage);

        listTirageSoir = new ArrayList<>();
        arrayAdapterTirageSoir = new ArrayAdapterTirage(getContext(), listTirageSoir);
        lvTirageSoir.setAdapter(arrayAdapterTirageSoir);


        String query = userQuery;

        String url = "http://192.168.1.12:8888/Lotto509/src/routes/tirageMidi.php/api/tirageMidi";


        AsyncHttpClient client = new AsyncHttpClient();


        client.get(url + '/' + query, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tchalaResults = null;

                try {
                    tchalaResults = response.getJSONArray("tchala");
                    listTirage.addAll(TirageMidi.fromJSONArray(tchalaResults));
                    arrayAdapterTirage.notifyDataSetChanged();
                    Log.d("DEBUG", listTirage.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                //Toast.makeText(getApplicationContext(), String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }
        });

        String urlSoir = "http://192.168.1.12:8888/Lotto509/src/routes/tirageSoir.php/api/tirageSoir";


        AsyncHttpClient clientSoir = new AsyncHttpClient();


        clientSoir.get(urlSoir + '/' + query, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tchalaResults = null;

                try {
                    tchalaResults = response.getJSONArray("tchala");
                    listTirageSoir.addAll(TirageSoir.fromJSONArray(tchalaResults));
                    arrayAdapterTirageSoir.notifyDataSetChanged();
                    Log.d("DEBUG", listTirageSoir.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                //Toast.makeText(getApplicationContext(), String.valueOf(statusCode) , Toast.LENGTH_SHORT).show();
            }
        });



    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getArguments().getString("title", "title");
        getDialog().setTitle(title);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}