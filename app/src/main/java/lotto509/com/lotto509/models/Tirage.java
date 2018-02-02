package lotto509.com.lotto509.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jetro on 1/19/18.
 */

public class Tirage {

    private String dateTirage;
    private String lotto3;
    private String lotto4;


    public Tirage(JSONObject jsonObject) throws JSONException {
        this.lotto3= jsonObject.getString("lotto3");
        this.lotto4= jsonObject.getString("lotto4");
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

    public String getLotto3() {
        return lotto3;
    }

    public void setLotto3(String lotto3) {
        this.lotto3 = lotto3;
    }

    public String getLotto4() {
        return lotto4;
    }

    public void setLotto4(String lotto4) {
        this.lotto4 = lotto4;
    }

}
