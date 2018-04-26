package lotto509.com.lotto509.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SET JETRO on 9/27/2016.
 */
public class Tchala {


    private String nom;
    private String lot;


    public Tchala(JSONObject jsonObject) throws JSONException {
        this.nom = jsonObject.getString("nom");
        this.lot= jsonObject.getString("lot");

    }

    public static ArrayList<Tchala> fromJSONArray(JSONArray array){
        ArrayList<Tchala> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add(new Tchala(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }


}
