package lotto509.com.lotto509.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lotto509.com.lotto509.R;
import lotto509.com.lotto509.models.Colonne;

/**
 * Created by SET JETRO on 2/16/2017.
 */
public class ArrayAdapterColonne  extends ArrayAdapter<Colonne> {

    //constructor
    public ArrayAdapterColonne(Context context, ArrayList<Colonne> tirageList)
    {
        super(context, android.R.layout.simple_list_item_1, tirageList);
    }

    //custom view to populate data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Colonne col = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_colonne, parent, false);
        }

        //Find views
        TextView lot1 = (TextView) convertView.findViewById(R.id.tvColonne1);
        TextView lot2 = (TextView) convertView.findViewById(R.id.tvColonne2);
        TextView lot3 = (TextView) convertView.findViewById(R.id.tvColonne3);
        TextView lot4 = (TextView) convertView.findViewById(R.id.tvColonn4);


        //populate data
        lot1.setText(col.getLot1().toString());
        lot2.setText(col.getLot2().toString());
        lot3.setText(col.getLot3().toString());
        lot4.setText(col.getLot4().toString());


        return convertView;
    }

}
