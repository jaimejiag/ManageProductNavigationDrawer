package com.example.usuario.manageproductsrecycler.modelo;

import java.util.Comparator;
import java.util.Locale;

/**
 * Created by usuario on 20/10/16.
 */

public class Product implements Comparable<Product>{
    private int mId;
    private String mName;
    private String mDescription;
    private String mDosage;
    private String mBrand;
    private double mPrice;
    private int mStock;
    private int mImage;

    public static final Comparator<Product> PRICE_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getmPrice(), p2.getmPrice());
        }
    };

    public static final Comparator<Product> STOCK_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getmStock() - p2.getmStock();
        }
    };


    public Product(String mName, String mDescription, String mDosage, String mBrand, double mPrice, int mStock, int mImage) {
        //this.mId = UUID.randomUUID(); es para saber que nos puede dar UUID de forma automática
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDosage = mDosage;
        this.mBrand = mBrand;
        this.mPrice = mPrice;
        this.mStock = mStock;
        this.mImage = mImage;
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmDosage() {
        return mDosage;
    }

    public void setmDosage(String mDosage) {
        this.mDosage = mDosage;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getmStock() {
        return mStock;
    }

    public void setmStock(int mStock) {
        this.mStock = mStock;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }


    //Para formatos
    public String getFormatedPrice(){ return String.format("$%s", mPrice);}

    public String getFotmattedUnitsInStock() {
        return String.format(Locale.getDefault(), "%d u/", mStock);
    }


    @Override
    public String toString() {
        return "Product{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mDosage='" + mDosage + '\'' +
                ", mBrand='" + mBrand + '\'' +
                ", mPrice=" + mPrice +
                ", mStock=" + mStock +
                ", mImage=" + mImage +
                '}';
    }

    /* Dos productos son iguales cuando tienen el mismo nombre,
    la misma marca y la misma concentración.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!mName.equals(product.mName)) return false;
        if (!mDosage.equals(product.mDosage)) return false;
        return mBrand.equals(product.mBrand);

    }

    @Override
    public int compareTo(Product p) {
        if (this.getmName().compareTo(p.getmName()) == 0)
            return this.getmBrand().compareTo(p.getmBrand());
        else
            return this.getmName().compareTo(p.getmName());
    }
}
