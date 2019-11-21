package com.foodona.foodona.Restarant.modal;

import java.util.ArrayList;

public class Modal_restaurantlist {
    String category_id;
    String category_name;


    ArrayList<Restaurent_subCatBean> mLoistSubCat;

    public ArrayList<Restaurent_subCatBean> getmLoistSubCat() {
        return mLoistSubCat;
    }

    public void setmLoistSubCat(ArrayList<Restaurent_subCatBean> mLoistSubCat) {
        this.mLoistSubCat = mLoistSubCat;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    String rest_name;
    int rest_image;
    String dishname;
    String price;


    public String getRest_name() {
        return rest_name;
    }

    public void setRest_name(String rest_name) {
        this.rest_name = rest_name;
    }

    public int getRest_image() {
        return rest_image;
    }

    public void setRest_image(int rest_image) {
        this.rest_image = rest_image;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
