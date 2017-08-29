package lotto509.com.lotto509.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.models.Konpayel;

/**
 * Created by SET JETRO on 2/16/2017.
 */
public class ArrayAdapterKonpayel extends ArrayAdapter<Konpayel> {

    //constructor
    public ArrayAdapterKonpayel(Context context, ArrayList<Konpayel> tirageList)
    {
        super(context, android.R.layout.simple_list_item_1, tirageList);
    }

    //custom view to populate data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Konpayel konp = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_konpayel, parent, false);
        }

        //Find views
        TextView lot1 = (TextView) convertView.findViewById(R.id.tvKonpayel1);
        TextView lot2 = (TextView) convertView.findViewById(R.id.tvKonpayel2);
        TextView lot3 = (TextView) convertView.findViewById(R.id.tvKonpayel3);


        //populate data
        lot1.setText(konp.getLot1().toString());
        lot2.setText(konp.getLot2().toString());
        lot3.setText(konp.getLot3().toString());


        return convertView;
    }

}