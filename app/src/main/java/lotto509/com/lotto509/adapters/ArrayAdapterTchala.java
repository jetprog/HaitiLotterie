package lotto509.com.lotto509.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.models.Tchala;

/**
 * Created by SET JETRO on 9/27/2016.
 */
public class ArrayAdapterTchala extends ArrayAdapter<Tchala> {

    //constructor
    public ArrayAdapterTchala(Context context, ArrayList<Tchala> tchalaList)
    {
        super(context, android.R.layout.simple_list_item_1, tchalaList);
    }

    //custom view to populate data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Tchala tchala = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_tchala, parent, false);
        }

        //Find views

        TextView nom = (TextView) convertView.findViewById(R.id.tvNom);
        TextView lot = (TextView) convertView.findViewById(R.id.tvLot);

        //populate data
        nom.setText(tchala.getNom());
        lot.setText(tchala.getLot());

        return convertView;
    }
}


