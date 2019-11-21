package com.foodona.foodona.Restarant.Response;

import com.foodona.foodona.Restarant.Bean.OrderBean;
import com.foodona.foodona.Restarant.Bean.OrderDetailsBean;
import com.foodona.foodona.Restarant.Bean.UserBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailsResponse {


    /**
     * success : 1
     * order_details : {"0":"141","order_id":"141","1":"44","res_id":"44","2":"NEAR CABWALA OFFICE","restaurant_address":"NEAR CABWALA OFFICE","3":"123","order_amount":"123","4":"1","order_status":"1","5":"43","user_id":"43","6":"Jaipur","user_address":"Jaipur","7":"26.914838","user_lat":"26.914838","8":"75.7937602","user_long":"75.7937602","9":"","user_phone":"","10":"1573201071","created_at":"1573201071","11":null,"accept_date_time":null,"12":null,"accept_status":null,"13":null,"delivery_date_time":null,"14":"44","deliveryboy_id":"44","15":null,"delivery_status":null,"16":"08-11-2019 03:50","delivered_date_time":"08-11-2019 03:50","17":null,"reject_date_time":null,"18":null,"reject_status":null,"19":"44","id":"44","20":"AHAA BIRYANI","restaurant_name":"AHAA BIRYANI","21":"26.9124","restaurant_lat":"26.9124","22":"resto_1548076648.jpg","photo":"resto_1548076648.jpg","23":"75.7873","restaurant_lng":"75.7873","24":"8540899999","phone":"8540899999","25":"29","delivery_time":"29"}
     * order : [{"order_id":"141","ItemId":"1","ItemQty":"1","ItemAmt":"80","menu_name":"Mutton"},{"order_id":"141","ItemId":"249","ItemQty":"2","ItemAmt":"150","menu_name":"EGG BIRYANI"},{"order_id":"141","ItemId":"250","ItemQty":"3","ItemAmt":"150","menu_name":"VEG BIRYANI"},{"order_id":"141","ItemId":"3","ItemQty":"1","ItemAmt":"180","menu_name":"CHICKEN BIRYANI"},{"order_id":"141","ItemId":"4","ItemQty":"4","ItemAmt":"210","menu_name":"MUTTON BIRYANI"}]
     * deliveryboy : null
     * user : {"0":"43","id":"43","1":"mukesh","fullname":"mukesh","2":"9314497070","phone_no":"9314497070","3":"test@test.com","email":"test@test.com","4":"R1hWdWtmcEIzTk01YkM4VjRTZXN0dz09","password":"R1hWdWtmcEIzTk01YkM4VjRTZXN0dz09","5":"302015","referal_code":"302015","6":"profile_1567755813.png","image":"profile_1567755813.png","7":"1567755813","created_at":"1567755813","8":"0","notify":"0","9":"appuser","login_with":"appuser"}
     */

    private String success;
    private OrderDetailsBean order_details;
    private Object deliveryboy;
    private UserBean user;
    private List<OrderBean> order;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public OrderDetailsBean getOrder_details() {
        return order_details;
    }

    public void setOrder_details(OrderDetailsBean order_details) {
        this.order_details = order_details;
    }

    public Object getDeliveryboy() {
        return deliveryboy;
    }

    public void setDeliveryboy(Object deliveryboy) {
        this.deliveryboy = deliveryboy;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }





}
