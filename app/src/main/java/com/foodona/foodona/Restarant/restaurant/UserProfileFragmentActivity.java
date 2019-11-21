package com.foodona.foodona.Restarant.restaurant;

import android.content.Intent;
import android.os.Bundle;

import com.foodona.foodona.Restarant.Activity.OrderHistory;
import com.foodona.foodona.Restarant.Activity.Splash_Activity;
import com.foodona.foodona.Restarant.Response.UserProfileResponse;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.foodona.foodona.R;
import com.squareup.picasso.Picasso;

import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfileFragmentActivity extends Fragment implements View.OnClickListener {

    View rootView;
TextView tv_username;
TextView tv_useremail;
TextView tv_userphone;
TextView tv_userbio;
TextView tv_userlocation;
TextView tv_userfollower;
CircleImageView profile_image;
TextView tv_edit;
TextView tv_userorder;
String Str_Name;
String Str_Phone;
String Str_email;
String Str_image;
String Str_address;
String Str_bio;
String User_id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.user_profile_fragment, container, false);
       // init();
        User_id = AppPreferences.getSavedUser(getActivity()).getId();

        find();
      //  st_timezoneID = TimeZone.getDefault().getID();
      //  System.out.println(st_timezoneID);
       // getLocation();
        getProfilesApi();
        return rootView;

    }

    public void find() {
        tv_username=rootView.findViewById(R.id.tv_username);
        tv_userlocation=rootView.findViewById(R.id.tv_userlocation);
        tv_userfollower=rootView.findViewById(R.id.tv_userfollower);
        profile_image=rootView.findViewById(R.id.profile_image);
        tv_useremail=rootView.findViewById(R.id.tv_useremail);
        tv_userphone=rootView.findViewById(R.id.tv_userphone);
        tv_userbio=rootView.findViewById(R.id.tv_userbio);

        tv_edit=rootView.findViewById(R.id.tv_edit);
        tv_userorder=rootView.findViewById(R.id.tv_userorder);
        tv_userorder.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
        //RestaurentList();
        //

    }


    @Override
    public void onClick(View view) {
        if (view==tv_userorder){
            Intent intent = new Intent(getActivity(), OrderHistory.class);
            startActivity(intent);

        }if (view==tv_edit){
            Fragment editProfileFragment = new EditProfileFragment();
            FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, editProfileFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }



    private void getProfilesApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id"}, new
                String[]{User_id});
        Log.e("orderprofile", "11111");

        Call<UserProfileResponse> call = apiInterface.UserGetProfile(builder.build());
        call.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                Log.e("orderprofileresponse", "11111");

                if (response.isSuccessful()&& response.body().getStatus().equals("1") ){
                    Str_Name=response.body().getFullname();
                    Str_address=response.body().getAddress();
                    Str_Phone=response.body().getPhone_no();
                    Str_email=response.body().getEmail();
                    Str_image=response.body().getImage();
                    Str_bio=response.body().getBio();
                    tv_username.setText(Str_Name);
                    tv_userlocation.setText(Str_address);
                    tv_userphone.setText(Str_Phone);
                    tv_useremail.setText(Str_email);
                    tv_userbio.setText(Str_bio);
                    Picasso.with(getActivity()).load("https://www.ekaakshgroup.in/FoodDeliverySystem/food/"+Str_image).placeholder(R.drawable.banneone).error(R.drawable.bannertwo).into(profile_image);



                } else {
                    Log.e("orderprofilefff", "" + "else");
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                onApiFailure(call, t);
                Log.e("getprofilefailure", "959595");


            }
        });
    }

    public void onApiFailure(Call<UserProfileResponse> call, Throwable t) {

        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
    }
}
