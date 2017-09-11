package lotto509.com.lotto509.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.adapters.ArrayAdapterTirage;
import lotto509.com.lotto509.models.TirageSoir;

/**
 * Created by Carly Baja on 8/24/2017.
 */

public class SoirFragment extends Fragment {

    private ArrayList<TirageSoir> listTirage;
    private ArrayAdapterTirage arrayAdapterTirage;
    ListView lvTirageSoir;


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.soir_fragment, parent, false);

        lvTirageSoir = (ListView) v.findViewById(R.id.lvTirageSoir);
        listTirage = new ArrayList<>();
        arrayAdapterTirage = new ArrayAdapterTirage(getActivity(), listTirage);
        lvTirageSoir.setAdapter(arrayAdapterTirage);

        loadTirage();


        return v;
    }

    public void loadTirage(){

        QueryOptions queryOptions = new QueryOptions();
        final int PAGESIZE = 100;
        queryOptions.setPageSize(PAGESIZE);
        queryOptions.addSortByOption("dateTirage ASC");
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        //dataQuery.setWhereClause(query);
        dataQuery.setQueryOptions(queryOptions);

        Backendless.Persistence.of( TirageSoir.class).find(dataQuery, new AsyncCallback<BackendlessCollection<TirageSoir>>(){
            @Override
            public void handleResponse( BackendlessCollection<TirageSoir> foundTirage )
            {

                int size  = foundTirage.getCurrentPage().size();;

                if( size > 0 ) {
                    // all Categorie_Ref instances have been found
                    //Log.d("DEBUG", String.valueOf("categories added - " + size));
                    listTirage.addAll((ArrayList<TirageSoir>) foundTirage.getCurrentPage());

                    if (size == PAGESIZE) {
                        foundTirage.nextPage(this);

                    }

                    arrayAdapterTirage.notifyDataSetChanged();
                }

                /*Iterator<TirageSoir> tirageIterator = foundTirage.getCurrentPage().iterator();
                while (tirageIterator.hasNext())
                {
                    TirageSoir newTirage = tirageIterator.next();
                    listTirage.add(newTirage);

                }
                arrayAdapterTirage.notifyDataSetChanged();*/


            }
            @Override
            public void handleFault( BackendlessFault fault )
            {

            }

        });
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}

