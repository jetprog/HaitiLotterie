package lotto509.com.lotto509;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import lotto509.com.lotto509.models.Tirage;

/**
 * Created by Carly Baja on 9/8/2017.
 */

public class DialogTirage extends DialogFragment{

    private EditText dateTirage;
    private EditText premietLot;
    private EditText deuxiemeLot;
    private EditText troisiemeLot;
    private EditText type;
    private Button save;
    private Button cancel;


        public DialogTirage() {
            // Empty constructor is required for DialogFragment
            // Make sure not to add arguments to the constructor
            // Use `newInstance` instead as shown below
        }

        public static DialogTirage newInstance(String title) {
            DialogTirage frag = new DialogTirage();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v =  inflater.inflate(R.layout.fragment_add_tirage, container);

            dateTirage = (EditText) v.findViewById(R.id.edtDate);
            premietLot = (EditText) v.findViewById(R.id.edtPremierLot);
            deuxiemeLot = (EditText) v.findViewById(R.id.edtDeuxiemeLot);
            troisiemeLot = (EditText) v.findViewById(R.id.edtTroisiemeLot);
            type = (EditText) v.findViewById(R.id.edtType);
            save = (Button) v.findViewById(R.id.btSave);
            cancel = (Button) v.findViewById(R.id.btCancel);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String dtTirage = dateTirage.getText().toString();
                    String lotFirst = premietLot.getText().toString();
                    String lotSecond = deuxiemeLot.getText().toString();
                    String lotTrois = troisiemeLot.getText().toString();
                    String typeTirage = type.getText().toString();

                    saveTirage(dtTirage, lotFirst, lotSecond, lotTrois, typeTirage);
                    clearField();

                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

            return v;


        }

        public void clearField(){
            dateTirage.getText().clear();
            premietLot.getText().clear();
            deuxiemeLot.getText().clear();
            troisiemeLot.getText().clear();
            type.getText().clear();
        }

        public void saveTirage(String dateTirage, String premierLot, String deuxLot, String troisiemeLot, String type){

            Tirage tirage = new Tirage();
            tirage.setDateTirage(dateTirage);
            tirage.setPremierLot(premierLot);
            tirage.setDeuxiemeLot(deuxLot);
            tirage.setTroisiemeLot(troisiemeLot);
            tirage.setType(type);

            //Backendless api to save a new tirage
            Backendless.Persistence.save( tirage, new AsyncCallback<Tirage>() {
                @Override
                public void handleResponse(Tirage response) {

                }

                @Override
                public void handleFault(BackendlessFault fault) {

                }
            });
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            String title = getArguments().getString("title", "title");
            getDialog().setTitle(title);
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
}
