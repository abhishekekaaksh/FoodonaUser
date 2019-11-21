package com.foodona.foodona.Restarant.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodona.foodona.MainActivity;
import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.modal.Login_Model;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.wang.avi.AVLoadingIndicatorView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_Up_Activity extends AppCompatActivity {
    String Error;
    private ProgressDialog mDialogProgress;
    EditText edt_username, edt_mob, edt_email, edt_pass, edt_conf_pass, edt_refrel;
    TextView tv_sign_up, tv_sign_in;
    String str_username, str_otp, str_mob, generatedPassword, str_email, str_pass, str_conf_pass, str_referral = "", device_id;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ImageView img_cross;
    AVLoadingIndicatorView avi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        device_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Random random = new Random();
        generatedPassword = String.format("%04d", random.nextInt(10000));
        str_otp = generatedPassword;

        Log.d("GeneratedPassword", "" + generatedPassword);
        edt_username = findViewById(R.id.edt_username);
        avi = findViewById(R.id.bar);

        edt_mob = findViewById(R.id.edt_mob);
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pass);
        edt_conf_pass = findViewById(R.id.edt_conf_pass);
        edt_refrel = findViewById(R.id.edt_refrel);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_sign_in = findViewById(R.id.tv_sign_in);
        img_cross = findViewById(R.id.img_cross);

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_Up_Activity.this, Sign_In_Activity.class);
                startActivity(intent);
            }
        });

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_username = edt_username.getText().toString().trim();
                str_mob = edt_mob.getText().toString().trim();
                str_email = edt_email.getText().toString().trim();
                str_pass = edt_pass.getText().toString().trim();
                str_conf_pass = edt_conf_pass.getText().toString().trim();
                str_referral = edt_refrel.getText().toString().trim();
                if (str_username.equals("") || str_username.isEmpty()) {
                    Toast.makeText(Sign_Up_Activity.this, "Please enter Username.", Toast.LENGTH_SHORT).show();
                } else if (str_mob.equals("") || str_mob.isEmpty()) {
                    Toast.makeText(Sign_Up_Activity.this, "Please enter Mobile No.", Toast.LENGTH_SHORT).show();
                } else if (str_mob.length() < 10) {
                    Toast.makeText(Sign_Up_Activity.this, "Please enter valid Mobile No.", Toast.LENGTH_SHORT).show();
                } else if (str_email.equals("") || str_email.isEmpty()) {
                    Toast.makeText(Sign_Up_Activity.this, "Please enter valid Email.", Toast.LENGTH_SHORT).show();
                } else if (!str_email.matches(emailPattern)) {
                    Toast.makeText(Sign_Up_Activity.this, "Invalid Email.", Toast.LENGTH_SHORT).show();
                } else if (str_pass.equals("") || str_pass.isEmpty()) {
                    Toast.makeText(Sign_Up_Activity.this, "Please enter password.", Toast.LENGTH_SHORT).show();
                } else if (str_pass.length() < 6 || str_pass.length() > 15) {
                    Toast.makeText(Sign_Up_Activity.this, "Password should be 6 to 15 characters.", Toast.LENGTH_SHORT).show();
                } else if (str_conf_pass.equals("") || str_conf_pass.isEmpty()) {
                    Toast.makeText(Sign_Up_Activity.this, "Please Re-enter password.", Toast.LENGTH_SHORT).show();
                } else if (!str_pass.equals(str_conf_pass)) {
                    Toast.makeText(Sign_Up_Activity.this, "Password and Confirm Password should be same.", Toast.LENGTH_SHORT).show();
                } else {
                    Sign_Up();
                }
                //        generatedPassword = "Dear "+str_username+" your Dream Tech one time password is "+generatedPassword;
                //        generatedPassword = "uname=ronish&pass=welcome1&send=DREAMV&dest="+str_mob+"&msg="+generatedPassword;
                //generatedPassword= Send_OTP+generatedPassword;
                //         generatedPassword = generatedPassword.replaceAll(" ","%20");
            }
        });
    }
/*
    public class Sending_OTP extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startLoadingDialog(getString(R.string.please_wait));
        }

        @Override
        protected String doInBackground(String... params) {
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(generatedPassword);
                    urlConnection = (HttpURLConnection) url
                            .openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);
                    }
                    // return the data to onPostExecute method
                    return current;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("datasfhjshga", s);
            // dismiss the progress dialog after receiving data from API
            stopLoadingDialog();
           */
/* try {
                JSONArray jsonArray = new JSONArray(s);
                JSONObject oneObject = jsonArray.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }*//*



        }

    }
*/
    private void Sign_Up() {
        avi.setVisibility(View.VISIBLE);

        // startLoadingDialog(getString(R.string.please_wait));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"fullname", "email", "phone_no", "password", "referral_code", "file"},
                //   new String[]{device_id,str_mob,str_email,str_username,str_pass,str_referral});
                new String[]{str_username, str_email, str_mob, str_pass, str_referral,str_pass});
        Call<ResponseBody> call = apiInterface.Sign_up(builder.build());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                avi.setVisibility(View.GONE);

                //   stopLoadingDialog();
                if (response.isSuccessful())
                {
                    try {
                        String result =  response.body().string();
                        Log.d("resut",result);

                        JSONArray mJsonArray = new JSONArray(response.body().string());
                        JSONObject jsonObject = mJsonArray.getJSONObject(0);
                        String status = jsonObject.optString("status");
                        Error = jsonObject.optString("error");
                      //  JSONObject jsonObject =new JSONObject(response.body().string());
                       // String status = jsonObject.optString("status");
                        String msg = jsonObject.optString("msg");
                        if (status.equals("Success")) {
                            JSONObject mJsonObject = jsonObject.optJSONObject("user_detail");
                            Toast.makeText(getApplicationContext(), Error, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Sign_Up_Activity.this, MainActivity.class));

                           /* Login_Model login_model = new Login_Model();
                            login_model.setId(mJsonObject.getString("id"));
                            login_model.setFullname(mJsonObject.getString("fullname"));
                            login_model.setEmail(mJsonObject.getString("email"));
                            login_model.setPhone_no(mJsonObject.getString("phone_no"));
                            login_model.setImage(mJsonObject.getString("image"));
                            login_model.setRefrelcode(mJsonObject.getString("referal_code"));
                            login_model.setCreated(mJsonObject.getString("created_at"));
                            Log.e("fullname",mJsonObject.getString("fullname"));
                            AppPreferences.SaveUserdetail(Sign_Up_Activity.this, login_model);*/
                        }
                    }
                    catch (IOException e){
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



                /*     try {
                    Log.d("onResponseRegister", response.body().string() + "\n" + response.body().toString());
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            Sign_Up_Responce(response.body().string());
                        }

                    }
                 //   if (response.body() != null)

                   // Sign_Up_Responce(response.body().string());
                    else {
                        Toast.makeText(getApplicationContext(), Error, Toast.LENGTH_LONG).show();

                       // Toast.makeText(Sign_Up_Activity.this, R.string.app_name, Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onApiFailure(call, t);
                Toast.makeText(Sign_Up_Activity.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void Sign_Up_Responce(String response) {
        try {
            JSONArray mJsonArray = new JSONArray(response);
            JSONObject jsonObject = mJsonArray.getJSONObject(0);
            String status = jsonObject.optString("Success");
            Error = jsonObject.optString("error");
            if (status.equals("Success")) {
                Toast.makeText(getApplicationContext(), Error, Toast.LENGTH_LONG).show();
                startActivity(new Intent(Sign_Up_Activity.this, Sign_In_Activity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));

                JSONObject mJsonObject = jsonObject.optJSONObject("user_detail");
                Login_Model login_model = new Login_Model();
                login_model.setId(mJsonObject.getString("id"));
                login_model.setFullname(jsonObject.getString("fullname"));
                login_model.setEmail(jsonObject.getString("email"));
                login_model.setPhone_no(jsonObject.getString("phone_no"));
                login_model.setImage(jsonObject.getString("image"));
                login_model.setRefrelcode(jsonObject.getString("referal_code"));
                login_model.setCreated(jsonObject.getString("created_at"));
                AppPreferences.SaveUserdetail(Sign_Up_Activity.this, login_model);
                /*JSONObject mJsonObject = jsonObject.optJSONObject("user_detail");
                Login_Model login_model = new Login_Model();
                login_model.setId(mJsonObject.getString("id"));
                login_model.setFullname(jsonObject.getString("fullname"));
                login_model.setEmail(jsonObject.getString("email"));
                login_model.setPhone_no(jsonObject.getString("phone_no"));
                login_model.setImage(jsonObject.getString("image"));
                login_model.setRefrelcode(jsonObject.getString("referal_code"));
                login_model.setCreated(jsonObject.getString("created_at"));
                AppPreferences.SaveUserdetail(Sign_Up_Activity.this, login_model);*/
            } else if (status.equals("Failed")) {
                Toast.makeText(getApplicationContext(), Error, Toast.LENGTH_LONG).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

      /*  try {
            Log.d("loginResponse", response);
            JSONObject object = new JSONObject(response);
            String status=object.optString("res");
             if(status.equals("1")){
                Toast.makeText(Sign_Up_Activity.this, "This Device is Already registered.", Toast.LENGTH_LONG).show();
            }else if(status.equals("2")){
                Toast.makeText(Sign_Up_Activity.this, "Mobile Number Already registered.", Toast.LENGTH_LONG).show();
            }else if(status.equals("3")){
                Toast.makeText(Sign_Up_Activity.this, "Email ID Already registered.", Toast.LENGTH_LONG).show();
            }else if (status.equals("4")) {
              *//*  new Sending_OTP().execute();
                Intent intent = new Intent(Sign_Up_Activity.this, Verify_OTP_Activity.class);
                 intent.putExtra("loc","sign_up");
                intent.putExtra("gen_otp",str_otp);
                intent.putExtra("u_mob",str_mob);
                intent.putExtra("u_name",str_username);
                startActivity(intent);*//*
            }
             else if(status.equals("5")){
                Toast.makeText(Sign_Up_Activity.this, "Invalid Referral Code.", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    public void onApiFailure(Call<ResponseBody> call, Throwable t) {
        //Log.e("error", t.toString());
        avi.setVisibility(View.VISIBLE);

        //    stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Please try later", Toast.LENGTH_SHORT).show();
    }

    public void startLoadingDialog(String progressLoading) {
        if (mDialogProgress == null)
            mDialogProgress = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        mDialogProgress.setCancelable(false);
        mDialogProgress.setMessage(progressLoading);
        mDialogProgress.show();
    }

    public void stopLoadingDialog() {
        if (mDialogProgress != null) {
            mDialogProgress.cancel();
        }
        mDialogProgress = null;
    }
}
