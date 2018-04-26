package lotto509.com.lotto509.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import lotto509.com.lotto509.R;
import lotto509.com.lotto509.activity.ActivityHome;
import lotto509.com.lotto509.models.TirageMidi;
import lotto509.com.lotto509.models.TirageSoir;

/**
 * Created by jetro on 1/19/18.
 */

public class DialogResult extends DialogFragment {

    private TextView midi;
    private TextView soir;
    private ImageView ivShare;


    public DialogResult() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static DialogResult newInstance(String title, String dateSearch) {
        DialogResult frag = new DialogResult();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("dateSearch", dateSearch);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.dialog_result, container);



        final String date = (String) getArguments().getString("dateSearch");

        midi = (TextView) v.findViewById(R.id.tvResultMidi);
        soir = (TextView) v.findViewById(R.id.tvResultSoir);

        searchTirage(date);

        ivShare = (ImageView) v.findViewById(R.id.ivShareLotto);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String midiLot = midi.getText().toString();
                String soirLot = soir.getText().toString();

                String share = date + "\nMidi: "+ midiLot + "\nSoir: " + soirLot;

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, share);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

        return v;

    }

    public void searchTirage(String userQuery){

        String query = "?params=" + userQuery.replace("-", "/") ;

        String url = "http://www.cristalhotelhaiti.com/lotto509/tirageMidi.php?params=01/01/2003";


        AsyncHttpClient client = new AsyncHttpClient();


        client.get(url , new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tchalaResults = null;

                try {
                    tchalaResults = response.getJSONArray("tirageMidi");
                    TirageMidi tir = new TirageMidi(tchalaResults.getJSONObject(0));
                    midi.setText(tir.getLotto3() + " " + tir.getLotto4());
                    //Log.d("DEBUG", listTirage.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Toast.makeText(getContext(), String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
            }
        });

        String urlSoir = "http://www.cristalhotelhaiti.com/lotto509/tirageSoir.php?params=01/01/2003";


        AsyncHttpClient clientSoir = new AsyncHttpClient();


        clientSoir.get(urlSoir, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray tchalaResults = null;

                try {
                    tchalaResults = response.getJSONArray("tirageSoir");
                    TirageSoir tirSoir = new TirageSoir(tchalaResults.getJSONObject(0));
                    soir.setText(tirSoir.getLotto3() + " " + tirSoir.getLotto4());

                    //Log.d("DEBUG", listTirageSoir.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Toast.makeText(getContext(), String.valueOf(statusCode) , Toast.LENGTH_SHORT).show();
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
