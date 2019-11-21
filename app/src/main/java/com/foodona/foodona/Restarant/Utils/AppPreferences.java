package com.foodona.foodona.Restarant.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.foodona.foodona.Restarant.modal.Login_Model;


/**
 * Created by Ram on 7/12/2016.
 */
public class AppPreferences {

    // App preference Name
    public static final String Prefsname = "FoodonaUser";
    public static final String KEY_ID = "id";
    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_EMAIL_ID = "email";
    public static final String KEY_MOBILE = "phone_no";
    public static final String KEY_REFREL_ID = "referal_code";
    public static final String KEY_USER_PROFILE = "image";
    public static final String KEY_CREATED_AT = "created_at";
    public static final String KEY_DEVICE_TOKEN = "devivce_token";
    public static Editor editor;


  /*  public static final String KEY_CART_QTY = "cart_qty";
    public static final String KEY_CART_AMOUNT = "cart_amount";
    public static final String KEY_LOGIN_ID = "profile_login_id";
    public static final String KEY_FIRST_NAME = "profile_first_name";
    public static final String KEY_LAST_NAME = "profile_last_name";
    public static final String KEY_USER_MOB = "profile_mob";*/

    public static void SaveUserdetail(Context ctx, Login_Model login_model) {

        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);

        Editor editor = prefs.edit();
        editor.putString(KEY_ID, login_model.getId());
        editor.putString(KEY_FULLNAME, login_model.getFullname());
        editor.putString(KEY_EMAIL_ID, login_model.getEmail());
        editor.putString(KEY_MOBILE, login_model.getPhone_no());
        editor.putString(KEY_REFREL_ID, login_model.getRefrelcode());
        editor.putString(KEY_USER_PROFILE, login_model.getImage());
        editor.putString(KEY_CREATED_AT, login_model.getCreated());
        editor.putString(KEY_DEVICE_TOKEN, login_model.getDevice_token());

        Log.d("nnnnn", login_model.getEmail() + login_model.getFullname() + login_model.getId() + "HHH" + login_model.getPhone_no());
        editor.commit();
    }


    public static Login_Model getSavedUser(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        Login_Model modal = new Login_Model();
        modal.setId(prefs.getString(KEY_ID, "-1"));
        modal.setFullname(prefs.getString(KEY_FULLNAME, "-1"));
        modal.setEmail(prefs.getString(KEY_EMAIL_ID, "-1"));

        modal.setPhone_no(prefs.getString(KEY_MOBILE, "-1"));

        modal.setRefrelcode(prefs.getString(KEY_REFREL_ID, "-1"));
        modal.setImage(prefs.getString(KEY_USER_PROFILE, "-1"));
        modal.setCreated(prefs.getString(KEY_CREATED_AT, "-1"));
        modal.setCreated(prefs.getString(KEY_DEVICE_TOKEN, "-1"));

        return modal;

    }

    public static void clearPrefsdata(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(Prefsname,
                Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear().commit();

    }
}


