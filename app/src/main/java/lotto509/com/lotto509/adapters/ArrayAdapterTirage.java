package lotto509.com.lotto509.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.models.TirageSoir;

/**
 * Created by SET JETRO on 2/16/2017.
 */
public class ArrayAdapterTirage extends ArrayAdapter<TirageSoir> {

        //constructor
        public ArrayAdapterTirage(Context context, ArrayList<TirageSoir> tirageList)
        {
            super(context, android.R.layout.simple_list_item_1, tirageList);
        }

        //custom view to populate data
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TirageSoir tirage = getItem(position);
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_tirage, parent, false);
            }

            //Find views
            TextView dateTirage = (TextView) convertView.findViewById(R.id.tvDateTirage);
            TextView lotto3 = (TextView) convertView.findViewById(R.id.tvTirageLotto3);
            TextView lotto4 = (TextView) convertView.findViewById(R.id.tvTirageLotto4);


            //populate data
            dateTirage.setText(tirage.getDateTirage().toString());
            lotto3.setText(tirage.getLotto3().toString());
            lotto4.setText(tirage.getLotto4().toString());


            return convertView;
        }

}
