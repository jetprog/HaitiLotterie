package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import lotto509.com.lotto509.R;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Change actionBar apparence
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recherche");

        String dateTirage = getIntent().getStringExtra("dateSearch");

        Toast.makeText(getApplicationContext(), dateTirage, Toast.LENGTH_SHORT).show();

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


