package lotto509.com.lotto509.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.adapters.ArrayAdapterColonne;
import lotto509.com.lotto509.models.Colonne;
import lotto509.com.lotto509.utils.Values;


public class ColonneFragment extends Fragment {

    private ArrayList<Colonne> listColonne;
    private ListView lvColonne;
    private ArrayAdapterColonne adapterColonne;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_colonne, container, false);

        listColonne = Values.listColonne();
        lvColonne = (ListView) v.findViewById(R.id.lvColonne);
        adapterColonne = new ArrayAdapterColonne(getContext(), listColonne);
        lvColonne.setAdapter(adapterColonne);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
