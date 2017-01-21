package lotto509.com.lotto509;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.ArrayList;
import java.util.Iterator;

public class TchalaActivity extends AppCompatActivity {

    public static final String APP_ID = "4DB157CB-1243-34CE-FF94-CA1D45E6E200" ;
    public static final String SECRET_KEY = "64871D48-EA7C-F708-FFC5-6DCA765FBB00";
    public static final String VERSION = "v1";

    private ArrayList<Tchala> tchalaliste;
    private ArrayAdapterTchala tchalaAdapter;
    private ListView listTchala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchala);


        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);

        getSupportActionBar().setTitle("Tchala");

        listTchala = (ListView) findViewById(R.id.lvTchala);
        tchalaliste = new ArrayList<>();
        tchalaAdapter = new ArrayAdapterTchala(this, tchalaliste);
        listTchala.setAdapter(tchalaAdapter);
        setupViews();

        listTchala.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });

    }

    private void customLoadMoreDataFromApi(int page) {

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize(30);
        queryOptions.setOffset(page);
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


    private void setupViews() {

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize(30);
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


        String query = " nom = '" +  userQuery + "'";
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(query);

        //backendless api to find a patient with query search
        Backendless.Persistence.of( Tchala.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Tchala>>(){
            @Override
            public void handleResponse( BackendlessCollection<Tchala> foundPatients )
            {
                Iterator<Tchala> patientIterator = foundPatients.getCurrentPage().iterator();
                while (patientIterator.hasNext())
                {
                    Tchala newPatient = patientIterator.next();
                    tchalaliste.add(newPatient);

                }
                //           patientArrayAdapter.clear();
                tchalaAdapter.notifyDataSetChanged();
                //progress.setVisibility(View.GONE);

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                //progress.setVisibility(View.GONE);
            }

        });

    }


}
