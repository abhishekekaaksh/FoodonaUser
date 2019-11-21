package com.foodona.foodona.Restarant.Response;

public class UserProfileResponse {


    /**
     * user_id : 43
     * fullname : mukesh
     * phone_no : 9314497070
     * email : test@test.com
     * image : profile_1567755813.png
     * address :
     * bio :
     * status : 1
     * msg : Successfully
     */

    private String user_id;
    private String fullname;
    private String phone_no;
    private String email;
    private String image;
    private String address;
    private String bio;
    private String status;
    private String msg;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
