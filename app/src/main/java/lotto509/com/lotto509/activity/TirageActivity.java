package lotto509.com.lotto509.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.adapters.ArrayAdapterTirage;
import lotto509.com.lotto509.models.Tirages;

public class TirageActivity extends AppCompatActivity {

    private ArrayList<Tirages> tirageliste;
    private ArrayAdapterTirage tirageAdapter;
    private ListView listTirages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirage);
        getSupportActionBar().setTitle("Tirages");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listTirages = (ListView) findViewById(R.id.lvTirages);
        tirageliste = new ArrayList<>();
        tirageAdapter = new ArrayAdapterTirage(this, tirageliste);
        listTirages.setAdapter(tirageAdapter);

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
