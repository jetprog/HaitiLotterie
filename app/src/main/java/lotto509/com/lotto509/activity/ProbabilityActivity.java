package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.astuetz.PagerSlidingTabStrip;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.fragments.ColonneFragment;
import lotto509.com.lotto509.fragments.KonpayelFragment;

public class ProbabilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probability);
        getSupportActionBar().setTitle("Probabilite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the veiw pager adapter
        ViewPager vpPage = (ViewPager) findViewById(R.id.viewpager);
        //set the view pager for the viewpagerAdapter
        vpPage.setAdapter(new OrderPageAdapter(getSupportFragmentManager()));
        //find the sliding tabs
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //attach the pager
        tabStrip.setViewPager(vpPage);
    }


    //return the order of the fragments and the viewpager
    public class OrderPageAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 2;
        String TabTitles[] = {"Colonne 3", "Colonne 4"};


        public OrderPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ColonneFragment();
            } else if (position == 1) {
                return new KonpayelFragment();
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
        }

        return(super.onOptionsItemSelected(item));
    }
}
