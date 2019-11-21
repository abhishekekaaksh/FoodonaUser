package com.foodona.foodona.Restarant.Bean;

public class UserOrderHistoryBean {
    /**
     * res_id : 43
     * order_id : 100
     * user_id : 43
     * total_price : 400
     * status : 4
     * resta_name : KINGS FOOD
     */

    private String res_id;
    private String order_id;
    private String user_id;
    private String total_price;
    private String status;
    private String resta_name;
    private String resta_image;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResta_name() {
        return resta_name;
    }

    public void setResta_name(String resta_name) {
        this.resta_name = resta_name;
    }

    public String getResta_image() {
        return resta_image;
    }

    public void setResta_image(String resta_image) {
        this.resta_image = resta_image;
    }
}