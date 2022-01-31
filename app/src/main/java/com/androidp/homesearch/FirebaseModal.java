package com.androidp.homesearch;

public class FirebaseModal {

    private String imageurl;
    private String price;
    private String bhk;
    private String sqft;
    private String loca;
    private String rent_sale;

    public FirebaseModal(){

    }

    public FirebaseModal(String imageurl, String price, String bhk, String sqft, String loca, String rent_sale) {
        this.imageurl = imageurl;
        this.price = price;
        this.bhk = bhk;
        this.sqft = sqft;
        this.loca = loca;
        this.rent_sale = rent_sale;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBhk() {
        return bhk;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public String getSqft() {
        return sqft;
    }

    public void setSqft(String sqft) {
        this.sqft = sqft;
    }

    public String getLoca() {
        return loca;
    }

    public void setLoca(String loca) {
        this.loca = loca;
    }

    public String getRent_sale() {
        return rent_sale;
    }

    public void setRent_sale(String rent_sale) {
        this.rent_sale = rent_sale;
    }
}
