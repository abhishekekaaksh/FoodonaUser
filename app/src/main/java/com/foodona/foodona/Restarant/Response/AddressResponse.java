package com.foodona.foodona.Restarant.Response;

import com.foodona.foodona.Restarant.Bean.AddressBean;

import java.util.List;

public class AddressResponse {


    /**
     * info : [{"id":"3","user_id":"44","address":"rajasthan"},{"id":"4","user_id":"44","address":"a-35,Near bus stand , 80-feet road, near diving public school,mahesh nagar,jaipur"},{"id":"5","user_id":"44","address":"jaipur "},{"id":"7","user_id":"44","address":"p24 jagdamba colony near karni palace panchayawala"},{"id":"8","user_id":"44","address":"Corporate Park, Plot No 8/9, Gopalbari, Jaipur, Rajasthan 302006"}]
     * status : Success
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<AddressBean> info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AddressBean> getInfo() {
        return info;
    }

    public void setInfo(List<AddressBean> info) {
        this.info = info;
    }


}
