package com.example.usuario.manageproductsdb.modelo;

/**
 * Created by usuario on 9/01/17.
 */

public class Pharmacy {
    private int id;
    private String cif;
    private String addres;
    private String phone;
    private String email;


    public Pharmacy(String cif, String addres, String phone, String email) {
        this.cif = cif;
        this.addres = addres;
        this.phone = phone;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
