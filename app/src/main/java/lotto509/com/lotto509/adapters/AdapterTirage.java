package lotto509.com.lotto509.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.models.Tirage;

/**
 * Created by Carly Baja on 9/8/2017.
 */

public class AdapterTirage extends ArrayAdapter<Tirage> {

    //constructor
    public AdapterTirage(Context context, ArrayList<Tirage> tirageList)
    {
        super(context, android.R.layout.simple_list_item_1, tirageList);
    }

    //custom view to populate data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Tirage tirage = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_tirage_final, parent, false);
        }

        //Find views
        TextView dateTirage = (TextView) convertView.findViewById(R.id.tvDate);
        TextView premierLot = (TextView) convertView.findViewById(R.id.tvPremierLot);
        TextView deuxiemeLot = (TextView) convertView.findViewById(R.id.tvDeuxiemeLot);
        TextView troisiemeLot = (TextView) convertView.findViewById(R.id.tvTroisiemLot);


        //populate data
        dateTirage.setText(tirage.getDateTirage().toString());
        premierLot.setText(tirage.getPremierLot());
        deuxiemeLot.setText(tirage.getDeuxiemeLot());
        troisiemeLot.setText(tirage.getTroisiemeLot());

        return convertView;
    }

}
