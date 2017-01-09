package com.example.usuario.manageproductsdb.modelo;

import java.util.Date;

/**
 * Created by usuario on 9/01/17.
 */

public class Invoice {
    private int id;
    private int idPharmacy;
    private Date date;
    private int idStatus;


    public Invoice(int idPharmacy, Date date, int idStatus) {
        this.idPharmacy = idPharmacy;
        this.date = date;
        this.idStatus = idStatus;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPharmacy() {
        return idPharmacy;
    }

    public void setIdPharmacy(int idPharmacy) {
        this.idPharmacy = idPharmacy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}
