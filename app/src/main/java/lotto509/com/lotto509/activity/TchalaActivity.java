package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;
import java.util.Iterator;

import lotto509.com.lotto509.EndlessScrollListener;
import lotto509.com.lotto509.R;
import lotto509.com.lotto509.adapters.ArrayAdapterTchala;
import lotto509.com.lotto509.models.Tchala;

public class TchalaActivity extends AppCompatActivity {


    private ArrayList<Tchala> tchalaliste;
    private ArrayAdapterTchala tchalaAdapter;
    private ListView listTchala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchala);


        getSupportActionBar().setTitle("Tchala");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listTchala = (ListView) findViewById(R.id.lvTchala);
        tchalaliste = new ArrayList<>();
        tchalaAdapter = new ArrayAdapterTchala(this, tchalaliste);
        listTchala.setAdapter(tchalaAdapter);

        String url = "http://192.168.1.167:8888/Lotto509/src/routes/tchala.php/api/tchala";

//        http://localhost:8888/Lotto509/src/routes/tchala.php/api/tchala

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tchalaResults = null;

                try {
                    tchalaResults = response.getJSONArray("tchala");
                    tchalaliste.addAll(Tchala.fromJSONArray(tchalaResults));
                    tchalaAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", tchalaliste.toString());
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
                Intent i = new Intent(getApplicationContext(), ActivityHome.class);
                startActivity(i);
                overridePendingTransition(17432578, 17432579);
                finish();
        }

        return(super.onOptionsItemSelected(item));
    }

    private void customLoadMoreDataFromApi() {

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize(30);
        //queryOptions.setOffset(page);
        queryOptions.addSortByOption("nom ASC");
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        //dataQuery.setWhereClause(query);
        dataQuery.setQueryOptions(queryOptions);


        Backendless.Persistence.of( Tchala.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Tchala>>(){
            @Override
            public void handleResponse( BackendlessCollection<Tchala> foundPatients )
            {

                Iterator<Tchala> tchalaIterator = foundPatients.getCurrentPage().iterator();
                while (tchalaIterator.hasNext())
                {
                    Tchala newPatient = tchalaIterator.next();
                    tchalaliste.add(newPatient);

                }
                tchalaAdapter.notifyDataSetChanged();
                //progress.setVisibility(View.GONE);

            }
            @Override
            public void handleFault(BackendlessFault fault )
            {
                //progress.setVisibility(View.GONE);
            }

        });
    }





    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tchala_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);


        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String querySubmit) {
                // perform query here
                tchalaAdapter.clear();
                searchLotto(querySubmit);
                searchView.clearFocus();
                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void searchLotto(String userQuery){

        listTchala = (ListView) findViewById(R.id.lvTchala);
        tchalaliste = new ArrayList<>();
        tchalaAdapter = new ArrayAdapterTchala(this, tchalaliste);
        listTchala.setAdapter(tchalaAdapter);


        String query = userQuery;
        String ip = ActivityHome.ipAdress;

        String url = ip + "Lotto509/src/routes/tchala.php/api/tchala";

//        http://localhost:8888/Lotto509/src/routes/tchala.php/api/tchala

        AsyncHttpClient client = new AsyncHttpClient();


        client.get(url + '/' + query, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tchalaResults = null;

                try {
                    tchalaResults = response.getJSONArray("tchala");
                    tchalaliste.addAll(Tchala.fromJSONArray(tchalaResults));
                    tchalaAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", tchalaliste.toString());
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


}
