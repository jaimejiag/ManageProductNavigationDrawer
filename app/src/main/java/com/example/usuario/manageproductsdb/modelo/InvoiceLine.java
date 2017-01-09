package com.example.usuario.manageproductsdb.modelo;

/**
 * Created by usuario on 9/01/17.
 */

public class InvoiceLine {
    private int idInvoice;
    private int orderProduct;
    private int idProduct;
    private int amount;
    private double price;


    public InvoiceLine(int idInvoice, int orderProduct, int idProduct, int amount, double price) {
        this.idInvoice = idInvoice;
        this.orderProduct = orderProduct;
        this.idProduct = idProduct;
        this.amount = amount;
        this.price = price;
    }


    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(int orderProduct) {
        this.orderProduct = orderProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
