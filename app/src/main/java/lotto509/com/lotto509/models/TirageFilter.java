package lotto509.com.lotto509.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jetro on 1/22/18.
 */

public class TirageFilter {


    private String dateTirage;
    private String lotto3Midi;
    private String lotto4Midi;
    private String lotto3Soir;
    private String lotto4Soir;


    public TirageFilter(JSONObject jsonObject) throws JSONException {
        this.lotto3Midi= jsonObject.getString("lotto3");
        this.lotto4Midi= jsonObject.getString("lotto4");
        this.dateTirage = jsonObject.getString("dateTirage");

    }

    public static ArrayList<Tirage> fromJSONArray(JSONArray array){
        ArrayList<Tirage> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add(new Tirage(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }



    public String getDateTirage() {
        return dateTirage;
    }

    public void setDateTirage(String dateTirage) {
        this.dateTirage = dateTirage;
    }

    public String getLotto3Midi() {
        return lotto3Midi;
    }

    public void setLotto3Midi(String lotto3) {
        this.lotto3Midi = lotto3;
    }

    public String getLotto4Midi() {
        return lotto4Midi;
    }

    public void setLotto4Midi(String lotto4) {
        this.lotto4Midi = lotto4;
    }

    public String getLotto3Soir() {
        return lotto3Midi;
    }

    public void setLotto3Soir(String lotto3) {
        this.lotto3Midi = lotto3;
    }

    public String getLotto4Soir() {
        return lotto4Midi;
    }

    public void setLotto4Soir(String lotto4) {
        this.lotto4Midi = lotto4;
    }

}
