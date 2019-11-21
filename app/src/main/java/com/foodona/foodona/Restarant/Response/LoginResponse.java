package com.foodona.foodona.Restarant.Response;

import com.foodona.foodona.Restarant.Bean.LoginBean;

public class LoginResponse {


    /**
     * info : {"id":"43","fullname":"mukesh","email":"test@test.com","phone_no":"9314497070","referal_code":"302015","image":"profile_1567755813.png","created_at":"1567755813","login_with":"appuser","password":"R1hWdWtmcEIzTk01YkM4VjRTZXN0dz09","password1":null}
     * status : Success
     * msg : Successfully
     * otp : 4239
     */

    private LoginBean info;
    private String status;
    private String msg;
    private String otp;

    public LoginBean getInfo() {
        return info;
    }

    public void setInfo(LoginBean info) {
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


}
