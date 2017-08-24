package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.fragments.MidiFragment;
import lotto509.com.lotto509.fragments.SoirFragment;

public class TirageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirage);
        getSupportActionBar().setTitle("TirageMidi");
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
