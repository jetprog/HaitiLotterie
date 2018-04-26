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
import lotto509.com.lotto509.models.Tirage;

/**
 * Created by jetro on 1/22/18.
 */

public class ArrayAdapterFilter extends ArrayAdapter<Tirage> {

    //constructor
    public ArrayAdapterFilter(Context context, ArrayList<Tirage> tirageList)
    {
        super(context, android.R.layout.simple_list_item_1, tirageList);
    }

    //custom view to populate data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Tirage tir = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.filter_item, parent, false);
        }

        //get String value
        String dateTir = tir.getDateTirage();
        String lotto3 = tir.getLotto3();
        String lotto4 = tir.getLotto4();
        String tirageFinal = lotto3 + " " + lotto4;


        //Find views
        TextView date = (TextView) convertView.findViewById(R.id.tvDateFilter);
        TextView tirageMidi = (TextView) convertView.findViewById(R.id.tvMidiFilter);


        //populate data
        date.setText(dateTir);
        tirageMidi.setText(tirageFinal);

        return convertView;
    }
}
