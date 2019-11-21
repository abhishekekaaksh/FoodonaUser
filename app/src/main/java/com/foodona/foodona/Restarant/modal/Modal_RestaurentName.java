package com.foodona.foodona.Restarant.modal;

import java.io.Serializable;

public class Modal_RestaurentName implements Serializable  {
    /*"id": "43",
            "name": "KINGS FOOD",
            "delivery_time": "30",
            "currency": " \u20b9",
            "image": "resto_1548074999.jpg",
            "lat": "26.9124",
            "lon": "75.78729999999996",
            "Category": [
            "multicusine",
            "fast food",
            "dine in",
            false,
            false
            ],
            "ratting": "0",
            "res_status": "closed",
            "distance": "0.00005899369716644287",
            "distancekm": "0",
            "open_time": "18:00",
            "close_time": "18:00"
}
*/
String id;
String name;
String delivery_time;
String currency;
String image;
String lat;
String lon;
String category;
String rating;
String res_status;
String distance;
String distancekm;
String open_time;
String close_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRes_status() {
        return res_status;
    }

    public void setRes_status(String res_status) {
        this.res_status = res_status;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistancekm() {
        return distancekm;
    }

    public void setDistancekm(String distancekm) {
        this.distancekm = distancekm;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
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
