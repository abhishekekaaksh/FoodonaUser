package com.foodona.foodona.Restarant.Response;

import com.foodona.foodona.Restarant.Bean.UserOrderHistoryBean;

import java.util.List;

public class OrderHistoryResponse {


    /**
     * success : 1
     * user_order : [{"res_id":"43","order_id":"100","user_id":"43","total_price":"400","status":"4","resta_name":"KINGS FOOD"},{"res_id":"44","order_id":"131","user_id":"43","total_price":"400","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"132","user_id":"43","total_price":"400","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"133","user_id":"43","total_price":"400","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"134","user_id":"43","total_price":"400","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"135","user_id":"43","total_price":"400","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"136","user_id":"43","total_price":"400","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"45","order_id":"140","user_id":"43","total_price":"123","status":"0","resta_name":"my test"},{"res_id":"44","order_id":"141","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"142","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"143","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"144","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"145","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"146","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"147","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"148","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"149","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"150","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"151","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"152","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"153","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"154","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"155","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"156","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"157","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"158","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"159","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"160","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"161","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"162","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"163","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"164","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"165","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"166","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"167","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"168","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"169","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"170","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"171","user_id":"43","total_price":"123","status":"4","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"172","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"173","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"174","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"175","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"176","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"177","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"178","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"179","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"180","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"181","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"182","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"183","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"184","user_id":"43","total_price":"123","status":"0","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"185","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"186","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"},{"res_id":"44","order_id":"187","user_id":"43","total_price":"123","status":"1","resta_name":"AHAA BIRYANI"}]
     */

    private String success;
    private List<UserOrderHistoryBean> user_order;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<UserOrderHistoryBean> getUser_order() {
        return user_order;
    }

    public void setUser_order(List<UserOrderHistoryBean> user_order) {
        this.user_order = user_order;
    }


}
