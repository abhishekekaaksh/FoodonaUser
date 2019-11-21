package com.foodona.foodona.Restarant.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Response.UserAddAddress;
import com.foodona.foodona.Restarant.Response.UserEditAddressResponse;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.foodona.foodona.Restarant.restaurant.RestaurentCart;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddress extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
ImageView back;
    //Our Map
    private GoogleMap mMap;
    EditText completaddress;
    //To store longitude and latitude from map
    private double longitude;
    private double latitude;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    Location mlocation;
    Marker mCurrLocationMarker;
    TextView tv_set_change;
    String locality;
    String STRIAddress;
    String Str_All_Address;
    String country;
    GoogleMap.OnCameraIdleListener onCameraIdleListener;
    Button savelocation;
    Float Str_Lat;
    Float Str_Long;
    String user_id;
    String AddressID;
    String Str_Tv_Location;
    TextView tv_set_location;
    String EditAddress;
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        EditAddress=intent.getStringExtra("AddressStr");
        AddressID=intent.getStringExtra("StrAdddressID");
        tv_set_location = findViewById(R.id.tv_set_location);
      //  STRIAddress=completaddress+tv_set_location;
        user_id = AppPreferences.getSavedUser(EditAddress.this).getId();
        back=findViewById(R.id.back);
        tv_set_change = findViewById(R.id.tv_set_change);
        savelocation = findViewById(R.id.savelocation);
        completaddress = findViewById(R.id.completaddress);
        avi = findViewById(R.id.bar);

        completaddress.setText(EditAddress);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Str_Lat = Float.valueOf(sharedPreferences.getString("st_Latitude", "value1"));
        Str_Long = Float.valueOf(sharedPreferences.getString("st_Longituude", "value2"));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Initializing googleapi client
       /* googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(mapFragment)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();*/
        configureCameraIdle();
        tv_set_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(EditAddress.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    printToast("Google Play Service Repair");
                } catch (GooglePlayServicesNotAvailableException e) {
                    printToast("Google Play Service Not Available");
                }
            }
        });
        back.setOnClickListener(this);
        savelocation.setOnClickListener(this);
    }

    /* txtLocationAddress.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  try {
                      Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                      .build(MainActivity.this);
                      startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                  } catch (GooglePlayServicesRepairableException e) {
                      printToast("Google Play Service Repair");
                  } catch (GooglePlayServicesNotAvailableException e) {
                      printToast("Google Play Service Not Available");
                  }
              }
          });*/
    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(EditAddress.this);


                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        locality = addressList.get(0).getAddressLine(0);
                        country = addressList.get(0).getCountryName();

                        if (!locality.isEmpty() && !country.isEmpty())
                            tv_set_location.setText(locality + "  " + country);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraIdleListener(onCameraIdleListener);
        mMap = googleMap;
        //mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng latLng = new LatLng(Str_Lat, Str_Long);
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");
        mMap.addMarker(new MarkerOptions().position(new LatLng(Str_Lat, Str_Long)));
        // mMap.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        mMap.setMyLocationEnabled(true);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                if (!place.getAddress().toString().contains(place.getName())) {
                    tv_set_location.setText(place.getName() + ", " + place.getAddress());
                } else {
                    tv_set_location.setText(place.getAddress());
                }

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 16);
                mMap.animateCamera(cameraUpdate);


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                printToast("Error in retrieving place info");

            }
        }
    }

    private void printToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {
        if (view == savelocation) {
            STRIAddress = completaddress.getText().toString().trim();
            Str_Tv_Location=tv_set_location.getText().toString();
            Str_All_Address=Str_All_Address+Str_Tv_Location;
            UserEditAddress();

            Intent i = new Intent(EditAddress.this, RestaurentCart.class);
            //i.putExtra("strlocality", locality);
            // i.putExtra("strcountry", country);
            startActivity(i);

        }if (view==back){
            onBackPressed();
        }
    }


    public void UserEditAddress() {
        avi.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      /*  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity"},
                new String[]{user_id, restid, sub_caty_id, str_dish1_price, clickedItem});*/

        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"address_id", "address"},
                new String[]{AddressID, Str_All_Address});
        Call<UserEditAddressResponse> call = apiInterface.UserEditAddress(builder.build());
        call.enqueue(new Callback<UserEditAddressResponse>() {
            @Override
            public void onResponse(Call<UserEditAddressResponse> call, Response<UserEditAddressResponse> response) {
                avi.setVisibility(View.GONE);

                Log.d("Addcart", response.body().toString());
                if (response.isSuccessful() && response.body().getSuccess().equals("1")) {
                    Log.d("resturentaddcart", response.body().toString());



                }
            }

            @Override
            public void onFailure(Call<UserEditAddressResponse> call, Throwable t) {
                //onApiFailure(call, t);
                // Toast.makeText(SingleRestaurent.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }




    public void UserAddAddress() {
        avi.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      /*  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity"},
                new String[]{user_id, restid, sub_caty_id, str_dish1_price, clickedItem});*/

        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "address"},
                new String[]{user_id, STRIAddress});
        Call<UserAddAddress> call = apiInterface.userAddAddress(builder.build());
        call.enqueue(new Callback<UserAddAddress>() {
            @Override
            public void onResponse(Call<UserAddAddress> call, Response<UserAddAddress> response) {
                avi.setVisibility(View.GONE);

                Log.d("Addcart", response.body().toString());
                if (response.isSuccessful() && response.body().getStatus().equals("Success")) {
                    Log.d("resturentaddcart", response.body().toString());


                   /* try {

                        String GetFollo = response.body().string();
                        Log.d("addcartresponse", GetFollo);
                        JSONObject jsonObject = new JSONObject(GetFollo);
                        String status = jsonObject.optString("status");
                        //strtotalcart=jsonObject.optString("totalcart");
                        //  Log.e("strtotalcart",strtotalcart);
                        //   strTotalPrioce=jsonObject.optString("totalprice");
                        String msg = jsonObject.optString("msg");
                        String info = jsonObject.optString("info");
                        if (status.equalsIgnoreCase("Failed")) {
                            //       JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        }
                    } catch (JSONException e) {

                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            }

            @Override
            public void onFailure(Call<UserAddAddress> call, Throwable t) {
                onApiFailure(call, t);
                // Toast.makeText(SingleRestaurent.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onApiFailure(Call<UserAddAddress> call, Throwable t) {
        //Log.e("error", t.toString());
        //avi.setVisibility(View.GONE);
        //swipeRefreshLayout.setRefreshing(false);

        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(EditAddress.this, "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(EditAddress.this, "Please try later", Toast.LENGTH_SHORT).show();
    }
}