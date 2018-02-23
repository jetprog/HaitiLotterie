package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import lotto509.com.lotto509.R;
import lotto509.com.lotto509.adapters.ArrayAdapterFilter;
import lotto509.com.lotto509.models.Tirage;
import lotto509.com.lotto509.models.TirageMidi;

public class FilterActivity extends AppCompatActivity {

    private ArrayList<Tirage> listTirage;
    private ArrayAdapterFilter adapterFilter;
    ListView lvTirageMidi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Change actionBar apparence
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recherche");

        String month = getIntent().getStringExtra("searchDay");
        String day = getIntent().getStringExtra("searchMonth");

        String dateTirage = day+"-"+month;

        lvTirageMidi = (ListView) findViewById(R.id.lvFilter);
        listTirage = new ArrayList<>();
        adapterFilter = new ArrayAdapterFilter(getApplicationContext(), listTirage);
        lvTirageMidi.setAdapter(adapterFilter);

        Toast.makeText(getApplicationContext(), dateTirage, Toast.LENGTH_SHORT).show();
        //loadTirage(month);

    }


    public void loadTirage(String date){


        String url = "http://192.168.1.167:8888/Lotto509/src/routes/tirage.php/api/filterMidi/01-01";

        AsyncHttpClient client = new AsyncHttpClient();



        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tirageResults = null;

                try {
                    tirageResults = response.getJSONArray("filterMidi");
                    listTirage.addAll(Tirage.fromJSONArray(tirageResults));
                    adapterFilter.notifyDataSetChanged();
                    Log.d("DEBUG", listTirage.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // do something useful
                Intent i = new Intent(getApplicationContext(), TirageActivity.class);
                startActivity(i);
                overridePendingTransition(17432578, 17432579);
                finish();
        }

        return(super.onOptionsItemSelected(item));
    }
}


