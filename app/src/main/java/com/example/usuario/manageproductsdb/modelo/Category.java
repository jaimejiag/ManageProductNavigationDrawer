package com.example.usuario.manageproductsdb.modelo;

/**
 * Created by usuario on 9/01/17.
 */

public class Category {
    private int id;
    private String name;


    public Category(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
