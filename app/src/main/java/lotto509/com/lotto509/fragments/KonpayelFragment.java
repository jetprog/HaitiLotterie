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
import lotto509.com.lotto509.adapters.ArrayAdapterKonpayel;
import lotto509.com.lotto509.models.Konpayel;
import lotto509.com.lotto509.utils.Values;

public class KonpayelFragment extends Fragment {

    private ArrayList<Konpayel> listKonpayel;
    private ListView lvKonpayel;
    private ArrayAdapterKonpayel adapterKonpayel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_konpayel, container, false);

        listKonpayel = Values.listKonpayel();
        lvKonpayel = (ListView) v.findViewById(R.id.lvKonpayel);
        adapterKonpayel = new ArrayAdapterKonpayel(getContext(), listKonpayel);

        lvKonpayel.setAdapter(adapterKonpayel);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
