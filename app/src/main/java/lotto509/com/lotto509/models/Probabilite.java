package lotto509.com.lotto509.models;

import java.util.ArrayList;

/**
 * Created by jetro on 1/22/18.
 */

public class Probabilite {


    private  String date;
    private String lot1;
    private String lot2;
    private String lot3;
    private String lot4;
    private String lot5;
    private String lot6;
    private String lot7;
    private String lot8;

    public Probabilite(){

    }

    public Probabilite(String date, String lot1, String lot2, String lot3, String lot4, String lot5, String lot6, String lot7, String lot8){
        this.date = date;
        this.lot1 = lot1;
        this.lot2 = lot2;
        this.lot3 = lot3;
        this.lot4 = lot4;
        this.lot5 = lot5;
        this.lot6 = lot6;
        this.lot7 = lot7;
        this.lot8 = lot8;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


    public String getLot5() {
        return lot5;
    }

    public void setLot5(String lot5) {
        this.lot5 = lot5;
    }

    public String getLot6() {
        return lot6;
    }

    public void setLot6(String lot6) {
        this.lot6 = lot6;
    }

    public String getLot7() {
        return lot7;
    }

    public void setLot7(String lot7) {
        this.lot7 = lot7;
    }

    public String getLot8() {
        return lot8;
    }

    public void setLot8(String lot8) {
        this.lot8 = lot8;
    }




}

