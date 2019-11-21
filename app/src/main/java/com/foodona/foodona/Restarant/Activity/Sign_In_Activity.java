package com.foodona.foodona.Restarant.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.foodona.foodona.MainActivity;
import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Bean.LoginBean;
import com.foodona.foodona.Restarant.Response.LoginResponse;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.modal.Login_Model;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.google.firebase.iid.FirebaseInstanceId;
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


public class Sign_In_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_email;
    EditText edit_password;
    Button email_sign_in_button;
    Button email_sign_up_button;
    String stMobile;
    String stPassowrd;
    AVLoadingIndicatorView avi;
ImageView login_log;
    Animation uptodown,downtoup;
String Status;
    String device_id;
    String device_type="Android";
    private ProgressDialog mDialogProgress;
    String Token;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        Token = FirebaseInstanceId.getInstance().getToken();

        //Token = AppPreferences.getSavedUser(Sign_In_Activity.this).getDevice_token();
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);

        device_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        init();

        Random random = new Random();
        //    generatedPassword  = String.format("%04d", random.nextInt(10000));
        //  str_otp = generatedPassword;
     /* //  img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/


      /*  email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stEmail = edit_email.getText().toString().trim();
                stPassowrd = edit_password.getText().toString().trim();
                if (stEmail.equals("") || stEmail.isEmpty()) {
                    Toast.makeText(Sign_In_Activity.this, "Please enter Registered Mobile No.", Toast.LENGTH_SHORT).show();
                } else if (stPassowrd.equals("") || stPassowrd.isEmpty()) {
                    Toast.makeText(Sign_In_Activity.this, "Please enter your Password.", Toast.LENGTH_SHORT).show();
                }*//*else if (str_pass.length()<6){
                    Toast.makeText(Sign_In_Activity.this,"Please enter your valid Password.",Toast.LENGTH_SHORT).show();
                }*//* else {
                    Login();
                    //  generatedPassword = "Dear user your Dream Tech one time password is "+generatedPassword;
                    //    generatedPassword = "uname=ronish&pass=welcome1&send=DREAMV&dest="+str_mob+"&msg="+generatedPassword;
                    // generatedPassword= Send_OTP+generatedPassword;
                    //     generatedPassword = generatedPassword.replaceAll(" ","%20");
                   *//* Intent intent = new Intent(Sign_In_Activity.this, My_Profile_Activity.class);
                    startActivity(intent);
                    finish();*//*
                }
            }
        });*/
    }


public void init(){
    email_sign_in_button = findViewById(R.id.email_sign_in_button);
    email_sign_up_button = findViewById(R.id.email_sign_up_button);
    login_log=findViewById(R.id.login_log);
    edit_email = findViewById(R.id.email);
    avi = findViewById(R.id.bar);

    edit_password = findViewById(R.id.password);
    email_sign_in_button.setOnClickListener(this);
    email_sign_up_button.setOnClickListener(this);
    login_log.setAnimation(uptodown);
    email_sign_in_button.setAnimation(downtoup);

}
    @Override
    public void onClick(View view) {
        if (view == email_sign_in_button) {
            stMobile = edit_email.getText().toString().trim();
            stPassowrd = edit_password.getText().toString().trim();
            if (stMobile.length()<10) {
                Toast.makeText(Sign_In_Activity.this, "Please enter Email No.", Toast.LENGTH_SHORT).show();
//            } else if (stPassowrd.equals("") || stPassowrd.isEmpty()) {
//                Toast.makeText(Sign_In_Activity.this, "Please enter your Password.", Toast.LENGTH_SHORT).show();
            } else {
                Login();
                //Loginc();
            }
        }if (view==email_sign_up_button){
            startActivity(new Intent(Sign_In_Activity.this,Sign_Up_Activity.class));
        }
    }


    /*private void Loginc() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"phone_no","device_id","device_token","device_type"},
                new String[]{stMobile,device_id,device_type,device_type});
        Call<LoginResponse> call = apiInterface.Loginn(builder.build());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                avi.setVisibility(View.GONE);
                Log.d("11111", "11111");
                if (response.isSuccessful()) {
                    Log.d("2222", "2222");
                    try {
                        if (response.isSuccessful() && response.body().getInfo() != null) {
                            //LoginResponse login_responce=response.body().getInfo();
                            LoginBean loginBean=response.body().getInfo();
                            //LoginBean loginBean = response.body().getInfo();

                            Status = loginBean.getId();
                            if (Status.equals("0")) {
                                AppPreferences.SaveUserdetail(Sign_In_Activity.this, loginBean);
                                //Updatedevice();

                                startActivity(new Intent(Sign_In_Activity.this, MainActivity.class));
                            } else {
                                Toast.makeText(Sign_In_Activity.this, "Please Contact To admin", Toast.LENGTH_SHORT).show();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }*/




    private void Login() {
        avi.setVisibility(View.VISIBLE);

        //  startLoadingDialog(getString(R.string.please_wait));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      //  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"email", "password"},
        //        new String[]{"test@test.com", "123456"});
                FormBody.Builder builder = ApiClient.createBuilder(new String[]
                                {"phone_no","device_id","device_token","device_type"},
              new String[]{stMobile,device_id,Token,device_type});
        Call<ResponseBody> call = apiInterface.Login(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
               // public void onResponse(Response<ResponseBody> response, Retrofit retrofit){
                avi.setVisibility(View.GONE);
                Log.d("11111","11111");
               // stopLoadingDialog();
                if (response.isSuccessful())
                {
                    Log.d("2222","2222");
                    try {
                        String result =  response.body().string();
                        Log.d("resut",result);
                        JSONObject jsonObject =new JSONObject(result);
                        String status = jsonObject.optString("status");
                        String msg = jsonObject.optString("msg");
                        String otp=jsonObject.optString("otp");
                        Log.d("msg",status);
                        if (status.equals("Success")) {
                            JSONObject mJsonObject = jsonObject.optJSONObject("info");
                            Login_Model login_model = new Login_Model();
                            login_model.setId(mJsonObject.getString("id"));
                            login_model.setFullname(mJsonObject.getString("fullname"));
                            login_model.setEmail(mJsonObject.getString("email"));
                            login_model.setPhone_no(mJsonObject.getString("phone_no"));
                            login_model.setImage(mJsonObject.getString("image"));
                            login_model.setRefrelcode(mJsonObject.getString("referal_code"));
                            login_model.setCreated(mJsonObject.getString("created_at"));
                            Log.e("fullname",mJsonObject.getString("fullname"));
                            AppPreferences.SaveUserdetail(Sign_In_Activity.this, login_model);
                            startActivity(new Intent(Sign_In_Activity.this, MainActivity.class));
                        }
                    }
                    catch (IOException e){
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onApiFailure(call, t);

                Log.d("0000","0000");

                Toast.makeText(Sign_In_Activity.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void Login_Responce(String response) {
        try {



            JSONArray mJsonArray = new JSONArray(response);
            JSONObject jsonObject = mJsonArray.getJSONObject(0);
            String status = jsonObject.optString("Success");
            if (status.equals("Success")) {
                JSONObject mJsonObject = jsonObject.optJSONObject("user_detail");
                Login_Model login_model = new Login_Model();
                login_model.setId(mJsonObject.getString("id"));
                login_model.setFullname(jsonObject.getString("fullname"));
                login_model.setEmail(jsonObject.getString("email"));
                login_model.setPhone_no(jsonObject.getString("phone_no"));
                login_model.setImage(jsonObject.getString("image"));
                login_model.setRefrelcode(jsonObject.getString("referal_code"));
                login_model.setCreated(jsonObject.getString("created_at"));
                Log.d("fullname",jsonObject.getString("fullname"));
                AppPreferences.SaveUserdetail(Sign_In_Activity.this, login_model);
                startActivity(new Intent(Sign_In_Activity.this, Sign_Up_Activity.class));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
                    //     url = new URL(generatedPassword);
                    // urlConnection = (HttpURLConnection) url
                    //           .openConnection();
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


    public void onApiFailure(Call<ResponseBody> call, Throwable t) {
        //Log.e("error", t.toString());
        avi.setVisibility(View.GONE);

       // stopLoadingDialog();
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

    @Override
    public void onBackPressed() {
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        finish();
    }

}
