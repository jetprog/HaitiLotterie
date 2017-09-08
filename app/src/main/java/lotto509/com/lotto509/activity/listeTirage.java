package lotto509.com.lotto509.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import lotto509.com.lotto509.DialogTirage;
import lotto509.com.lotto509.R;
import lotto509.com.lotto509.fragments.Midi;
import lotto509.com.lotto509.fragments.Soir;

public class listeTirage extends AppCompatActivity {

    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_tirage);

        //get the veiw pager adapter
        ViewPager vpPage = (ViewPager) findViewById(R.id.viewpagerListTirage);
        //set the view pager for the viewpagerAdapter
        vpPage.setAdapter(new OrderPageTirage(getSupportFragmentManager()));
        //find the sliding tabs
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabsLisTirage);
        //attach the pager
        tabStrip.setViewPager(vpPage);

        fabAdd = (FloatingActionButton) findViewById(R.id.fabAddTirage);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DialogTirage alertDialog = DialogTirage.newInstance("Ajouter Tirage");
                alertDialog.show(fm, "fragment_alert");

            }
        });


    }

    //return the order of the fragments and the viewpager
    public static class OrderPageTirage extends FragmentPagerAdapter {

        final int PAGE_COUNT = 2;
        String TabTitles[] = {"Midi", "Soir"};


        public OrderPageTirage(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Midi();

            } else if (position == 1) {
                return new Soir();

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
}
