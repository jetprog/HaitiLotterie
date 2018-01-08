package lotto509.com.lotto509.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    static SearchView searchView;

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirage);
        getSupportActionBar().setTitle("Tirage");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ViewPager vpPage = (ViewPager) findViewById(R.id.viewpagerTirage);

        //set the view pager for the viewpagerAdapter
        vpPage.setAdapter(new ProbabilityActivity.OrderPageAdapter(getSupportFragmentManager()));

        //attach the pager
        adapterViewPager = new OrderPageTirageAdapter(getSupportFragmentManager());
        vpPage.setAdapter(adapterViewPager);

        //find the sliding tabs
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliderTirage);
        tabLayout.setupWithViewPager(vpPage);



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


        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your code here
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Date Tirage");
//                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setQueryHint("Entrée votre date");
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");



            searchView.setQuery(simpleDateFormat.format(c.getTime()), false);




        }



    }

}



