package com.foodona.foodona.Restarant.modal;

import java.io.Serializable;

public class Restaurent_subCatBean implements Serializable {

    String sub_cat_id;
    String sub_cate_name;
    String sub_cat_price;
    String sub_cat_desc;
    String sub_cat_photo;
    String sub_cat_type;

    public String getSub_cat_type() {
        return sub_cat_type;
    }

    public void setSub_cat_type(String sub_cat_type) {
        this.sub_cat_type = sub_cat_type;
    }

    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }

    public String getSub_cate_name() {
        return sub_cate_name;
    }

    public void setSub_cate_name(String sub_cate_name) {
        this.sub_cate_name = sub_cate_name;
    }

    public String getSub_cat_price() {
        return sub_cat_price;
    }

    public void setSub_cat_price(String sub_cat_price) {
        this.sub_cat_price = sub_cat_price;
    }

    public String getSub_cat_desc() {
        return sub_cat_desc;
    }

    public void setSub_cat_desc(String sub_cat_desc) {
        this.sub_cat_desc = sub_cat_desc;
    }

    public String getSub_cat_photo() {
        return sub_cat_photo;
    }

    public void setSub_cat_photo(String sub_cat_photo) {
        this.sub_cat_photo = sub_cat_photo;
    }
}
