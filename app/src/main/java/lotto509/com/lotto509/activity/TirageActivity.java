package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.astuetz.PagerSlidingTabStrip;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.Iterator;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.adapters.ArrayAdapterMidi;
import lotto509.com.lotto509.adapters.ArrayAdapterTirage;
import lotto509.com.lotto509.fragments.MidiFragment;
import lotto509.com.lotto509.fragments.SoirFragment;
import lotto509.com.lotto509.models.TirageMidi;
import lotto509.com.lotto509.models.TirageSoir;

public class TirageActivity extends AppCompatActivity {


    private ArrayList<TirageMidi> listTirage;
    private ArrayAdapterMidi arrayAdapterTirage;
    ListView lvTirageMidi;
    private ArrayList<TirageSoir> listTirageSoir;
    private ArrayAdapterTirage arrayAdapterTirageSoir;
    ListView lvTirageSoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirage);
        getSupportActionBar().setTitle("Tirage");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //get the veiw pager adapter
        ViewPager vpPage = (ViewPager) findViewById(R.id.viewpagerTirage);
        //set the view pager for the viewpagerAdapter
        vpPage.setAdapter(new OrderPageTirageAdapter(getSupportFragmentManager()));
        //find the sliding tabs
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabsTirage);
        //attach the pager
        tabStrip.setViewPager(vpPage);

    }


    //return the order of the fragments and the viewpager
    public static class OrderPageTirageAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 2;
        String TabTitles[] = {"Midi", "Soir"};


        public OrderPageTirageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new MidiFragment();
            } else if (position == 1) {
                return new SoirFragment();
            } else {
                return null;
            }
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return TabTitles[position];
        }

        @Override
        public int getCount() {
            return TabTitles.length;
        }
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
                //arrayAdapterTirage.clear();
                searchTirage(querySubmit);
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


    private void searchTirage(String userQuery){

        lvTirageMidi = (ListView) findViewById(R.id.lvTirageMidi);
        listTirage = new ArrayList<>();
        arrayAdapterTirage = new ArrayAdapterMidi(this, listTirage);
        lvTirageMidi.setAdapter(arrayAdapterTirage);

        lvTirageSoir = (ListView) findViewById(R.id.lvTirageSoir);
        listTirageSoir = new ArrayList<>();
        arrayAdapterTirageSoir = new ArrayAdapterTirage(this, listTirageSoir);
        lvTirageSoir.setAdapter(arrayAdapterTirageSoir);


        String query = " dateTirage = '" +  userQuery + "'";
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(query);

        //backendless api to find a patient with query search
        Backendless.Persistence.of( TirageMidi.class).find(dataQuery, new AsyncCallback<BackendlessCollection<TirageMidi>>(){
            @Override
            public void handleResponse( BackendlessCollection<TirageMidi> foundPatients )
            {
                Iterator<TirageMidi> patientIterator = foundPatients.getCurrentPage().iterator();
                while (patientIterator.hasNext())
                {
                    TirageMidi newPatient = patientIterator.next();
                    listTirage.add(newPatient);

                }
                //           patientArrayAdapter.clear();
                arrayAdapterTirage.notifyDataSetChanged();
                //progress.setVisibility(View.GONE);

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                //progress.setVisibility(View.GONE);
            }

        });

        //backendless api to find a patient with query search
        Backendless.Persistence.of( TirageSoir.class).find(dataQuery, new AsyncCallback<BackendlessCollection<TirageSoir>>(){
            @Override
            public void handleResponse( BackendlessCollection<TirageSoir> foundPatients )
            {
                Iterator<TirageSoir> patientIterator = foundPatients.getCurrentPage().iterator();
                while (patientIterator.hasNext())
                {
                    TirageSoir newPatient = patientIterator.next();
                    listTirageSoir.add(newPatient);

                }
                //           patientArrayAdapter.clear();
                arrayAdapterTirageSoir.notifyDataSetChanged();
                //progress.setVisibility(View.GONE);

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                //progress.setVisibility(View.GONE);
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
}
