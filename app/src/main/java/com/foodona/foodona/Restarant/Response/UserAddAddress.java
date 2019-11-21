package com.foodona.foodona.Restarant.Response;

public class UserAddAddress {


    /**
     * info : true
     * status : Success
     * msg : Successfully
     */

    private boolean info;
    private String status;
    private String msg;

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

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
}
