package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.models.TirageMidi;
import lotto509.com.lotto509.models.TirageSoir;
import lotto509.com.lotto509.utils.Backend;

public class ActivityHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //declare variable to set value for each textview
    private TextView dateTirageMidi;
    private TextView lotto3Midi;
    private TextView lotto4Midi;
    private TextView dateTirageSoir;
    private TextView lotto3Soir;
    private TextView lotto4Soir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //replace actionbar by toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Lotto509");
        setSupportActionBar(toolbar);

        //initialize backendless for the app
        Backendless.initApp(this, Backend.APP_ID, Backend.SECRET_KEY, Backend.VERSION);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //populate data Tirage midi
        setTirageMidi();

        //populate data tirage soir
        setTirageSoir();


    }


    public void setTirageMidi(){

        dateTirageMidi = (TextView) findViewById(R.id.tvDateTirageMidi);
        lotto3Midi = (TextView) findViewById(R.id.tvLotto3Midi);
        lotto4Midi = (TextView) findViewById(R.id.tvLotto4Midi);

        Backendless.Persistence.of( TirageMidi.class).findLast(new AsyncCallback<TirageMidi>(){
            @Override
            public void handleResponse( TirageMidi tirageMidifound )
            {
                // last tirage instance has been found
                TirageMidi tirage = tirageMidifound;
                Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
                String dateTir = tirage.getDateTirage().toString();
                String lot3 = tirage.getLotto3().toString();
                String lot4 = tirage.getLotto4().toString();

                dateTirageMidi.setText(dateTir);
                lotto3Midi.setText(lot3);
                lotto4Midi.setText(lot4);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Toast.makeText(getApplicationContext(), "No tirage", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setTirageSoir(){

        dateTirageSoir = (TextView) findViewById(R.id.tvDateTirageSoir);
        lotto3Soir = (TextView) findViewById(R.id.tvLotto3Soir);
        lotto4Soir = (TextView) findViewById(R.id.tvLotto4Soir);

        Backendless.Persistence.of( TirageSoir.class).findLast(new AsyncCallback<TirageSoir>(){
            @Override
            public void handleResponse( TirageSoir tirageSoirfound )
            {
                // last tirage instance has been found
                TirageSoir tirage = tirageSoirfound;
                Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
                String dateTir = tirage.getDateTirage().toString();
                String lot3 = tirage.getLotto3().toString();
                String lot4 = tirage.getLotto4().toString();

                dateTirageSoir.setText(dateTir);
                lotto3Soir.setText(lot3);
                lotto4Soir.setText(lot4);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Toast.makeText(getApplicationContext(), "No tirage", Toast.LENGTH_SHORT).show();
            }
        });
    }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activityhome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.itTirages) {
            // Handle the Tirage action
            showTirage();

        } else if (id == R.id.itTchala) {
            // Handle the Tchala action
            showTChala();

        } else if (id == R.id.itProbabilite) {
            // Handle the Tirage action
            showProbabilite();


        } else if (id == R.id.itBoulChans) {

        } else if (id == R.id.itShare) {

            String tirageMidi = "Tirage midi: "+ lotto3Midi.getText().toString()+ " "+lotto4Midi.getText().toString();
            String tirageSoir = "TirageSoir: "+ lotto3Soir.getText().toString()+ " "+lotto4Soir.getText().toString();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, tirageMidi+ "  "+tirageSoir);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.itSend) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showTirage(){
        Intent i = new Intent(ActivityHome.this, TirageActivity.class  );
        startActivity(i);
        finish();
    }

    public void showTChala(){
        Intent i = new Intent(ActivityHome.this, TchalaActivity.class  );
        startActivity(i);
        finish();
    }



    public void showProbabilite(){
        Intent i = new Intent(ActivityHome.this, ProbabilityActivity.class  );
        startActivity(i);
        finish();
    }
}