package lotto509.com.lotto509.models;

import java.util.ArrayList;

/**
 * Created by Carly Baja on 8/28/2017.
 */

public class Colonne {

    private String lot1;
    private String lot2;
    private String lot3;
    private String lot4;

    public Colonne(){

    }

    public Colonne(String lot1, String lot2, String lot3, String lot4){
        this.lot1 = lot1;
        this.lot2 = lot2;
        this.lot3 = lot3;
        this.lot4 = lot4;
    }

    public String getLot1() {
        return lot1;
    }

    public void setLot1(String lot1) {
        this.lot1 = lot1;
    }

    public String getLot2() {
        return lot2;
    }

    public void setLot2(String lot2) {
        this.lot2 = lot2;
    }

    public String getLot3() {
        return lot3;
    }

    public void setLot3(String lot3) {
        this.lot3 = lot3;
    }

    public String getLot4() {
        return lot4;
    }

    public void setLot4(String lot4) {
        this.lot4 = lot4;
    }

    public static ArrayList<Colonne> listColonne(){

        ArrayList<Colonne> colList = new ArrayList<>();

        Colonne col1 = new Colonne("01", "26", "51", "76");



       return colList;
    }


}
