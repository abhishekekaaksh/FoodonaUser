package com.foodona.foodona.Restarant.modal;

public class Login_Model {

    String id;
    String fullname;
    String email;
String password;
    String phone_no;
    String refrelcode;
    String image;
    String created;
    String device_token;


    public Login_Model() {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone_no = phone_no;
        this.refrelcode = refrelcode;
        this.image = image;
        this.created = created;
        this.device_token=device_token;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getRefrelcode() {
        return refrelcode;
    }

    public void setRefrelcode(String refrelcode) {
        this.refrelcode = refrelcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
