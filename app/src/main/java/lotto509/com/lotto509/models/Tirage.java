package lotto509.com.lotto509.models;

import java.util.Date;

/**
 * Created by Carly Baja on 9/7/2017.
 */

public class Tirage {


    private String premierLot;
    private String deuxiemeLot;
    private String troisiemeLot;
    private Date dateTirage;
    private String type;

    public Tirage() {

    }

    public Date getDateTirage() {
        return dateTirage;
    }

    public void setDateTirage(Date dateTirage) {
        this.dateTirage = dateTirage;
    }

    public String getPremierLot() {
        return premierLot;
    }

    public void setPremierLot(String premierLot) {
        this.premierLot = premierLot;
    }

    public String getDeuxiemeLot() {
        return deuxiemeLot;
    }

    public void setDeuxiemeLot(String deuxiemeLot) {
        this.deuxiemeLot = deuxiemeLot;
    }

    public String getTroisiemeLot() {
        return troisiemeLot;
    }

    public void setTroisiemeLot(String troisiemeLot) {
        this.troisiemeLot = troisiemeLot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}

