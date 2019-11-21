package com.foodona.foodona.Restarant.restaurant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.adapterRestaurant.RestaurantAdapter;
import com.foodona.foodona.Restarant.adapterRestaurant.SlidingImage_Adapter;
import com.foodona.foodona.Restarant.modal.Modal_RestaurentName;
import com.foodona.foodona.Restarant.modal.Modal_restaurantlist;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.foodona.foodona.Restarant.rest.AppUrls;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurentActivity extends Fragment implements LocationListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    SwipeRefreshLayout swipeRefreshLayout;
    String st_Latitude;
    String st_Longituude;
    String st_Loction;
    String  StrAllLocation;
    String st_category;
    String restuarent_id;
    FragmentManager fragmentManager;
    RestaurantAdapter restaurantAdapter;
    Modal_restaurantlist modal_restaurantlist;
    Modal_RestaurentName modal_restaurentName;
    ArrayList<Modal_RestaurentName> modal_restaurantlists;
    LocationManager locationManager;
    AVLoadingIndicatorView avi;
    String st_Radius = "5";
ShimmerFrameLayout shimmerFrameLayout;
    // AppLocationService appLocationService;
    TextView textloc;
    String st_timezoneID;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    View rootView;

    int[] images = {R.drawable.banner, R.drawable.bannertwo, R.drawable.banneone, R.drawable.bannertwo};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_restaurant, container, false);
        fragmentManager=getActivity().getSupportFragmentManager();
        st_timezoneID = TimeZone.getDefault().getID();
        System.out.println(st_timezoneID);
        getLocation();
        init();
        find();

        return rootView;

    }


    public void find() {
        //RestaurentList();
        //
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }



        //    System.out.println(timezoneID);

        textloc = rootView.findViewById(R.id.textloc);
        textloc.setOnClickListener(this);
        avi = rootView.findViewById(R.id.bar);
        swipeRefreshLayout = rootView.findViewById(R.id.swipe);
        recyclerView = rootView.findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);
        //  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //   ArrayList<Modal_restaurantlist> foodversins = preparedata();
        //   restaurantAdapter = new RestaurantAdapter(getActivity(), foodversins);
        recyclerView.setAdapter(restaurantAdapter);
        // RestaurentList();

    }


    private void init() {
        for (int i = 0; i < images.length; i++)
            ImagesArray.add(images[i]);
        mPager = rootView.findViewById(R.id.pager);
        //mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(getActivity(), ImagesArray));


        final float density = getResources().getDisplayMetrics().density;


        NUM_PAGES = images.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 5000);
    }


    private void RestaurentList() {

        swipeRefreshLayout.setRefreshing(true);

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

   /*     FormBody.Builder builder = ApiClient.createBuilder(new String[]{"timezone", "lat", "lon", "location", "radius"}
                , new String[]{"asia/Kolkata", "26.9124", "75.7873", "jaipur", "5"});*/
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"timezone", "lat", "lon", "location", "radius"}
                , new String[]{st_timezoneID, st_Latitude, st_Longituude, st_Loction, st_Radius});
        Call<ResponseBody> call = apiInterface.Restaurent_List(builder.build());
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                avi.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);


                Log.d("resturentResult0", response.body().toString());

                if (response.isSuccessful()) {
                    Log.d("resturentResult1", response.body().toString());

                    try {

                        String resturentResult = response.body().string();
                        Log.d("resturentResult", resturentResult);
                        JSONObject jsonObject = new JSONObject(resturentResult);
                        String status = jsonObject.optString("status");
                        String msg = jsonObject.optString("msg");
                        if (status.equals("Success")) {

                            modal_restaurantlists = new ArrayList<>();
                            JSONArray mJSONObject1 = jsonObject.getJSONArray("info");
                            Log.e("arraylenthlk", String.valueOf(mJSONObject1.length()));
                            //textloc.setText(String.valueOf(mJSONObject1.length()));
                            for (int i = 0; i < mJSONObject1.length(); i++) {
                                JSONObject mJSONObject = mJSONObject1.getJSONObject(i);

                                Modal_RestaurentName modal_restaurentName = new Modal_RestaurentName();
                                modal_restaurentName.setId(mJSONObject.getString("id"));
                                modal_restaurentName.setName(mJSONObject.getString("name"));
                                modal_restaurentName.setDelivery_time(mJSONObject.getString("delivery_time"));
                                modal_restaurentName.setCurrency(mJSONObject.getString("currency"));
                                modal_restaurentName.setImage(AppUrls.IMAGE_URL + mJSONObject.getString("image"));
                                Log.e("imagee", AppUrls.IMAGE_URL + mJSONObject.getString("image"));
                                modal_restaurentName.setLat(mJSONObject.getString("lat"));
                                modal_restaurentName.setLon(mJSONObject.getString("lon"));
                                st_category = "";


                                try {
                                    JSONArray mJSONArray = mJSONObject.getJSONArray("Category");
                                    for (int a = 0; a < mJSONArray.length(); a++) {
                                        if (st_category.isEmpty()) {
                                            st_category = mJSONArray.getString(a);
                                        } else {
                                            st_category = st_category + ", " + mJSONArray.getString(a);
                                        }

                                    }
                                } catch (Exception e) {

                                    st_category = "No Category";
                                    e.printStackTrace();
                                }

                                modal_restaurentName.setCategory(st_category);
                                modal_restaurentName.setRating(mJSONObject.getString("ratting"));
                                modal_restaurentName.setRes_status("res_status");
                                modal_restaurentName.setDistance("distance");
                                modal_restaurentName.setDistancekm("distancekm");
                                modal_restaurentName.setOpen_time("open_time");
                                modal_restaurentName.setClose_time("close_time");

                                modal_restaurantlists.add(modal_restaurentName);
                            }
                            if (modal_restaurantlists.size() > 0) {
                                restaurantAdapter = new RestaurantAdapter(getActivity(), modal_restaurantlists,fragmentManager);
                                recyclerView.setAdapter(restaurantAdapter);
                            } else {
                                Log.e("ERRRR", "No data Found");

                            }

                        } else {
                            Log.e("ERRRR", "ERRRR");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onApiFailure(call, t);
                Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void onApiFailure(Call<ResponseBody> call, Throwable t) {
        //Log.e("error", t.toString());
        avi.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);

        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
    }


    //////////////////////
/*    https://androstock.com/tutorials/
getting-current-location-latitude-longitude-country-android-android-studio.html*/
    public void getLocation() {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //textloc.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        st_Latitude = String.valueOf(location.getLatitude());
        st_Longituude = String.valueOf(location.getLongitude());

        //textloc.setText(st_Latitude + "\n" + st_Longituude);

        st_Longituude = String.valueOf(location.getLongitude());
        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            textloc.setText(addresses.get(0).getFeatureName() + "," + addresses.get(0).getSubLocality()
                    + "," + addresses.get(0).getSubAdminArea());
            st_Loction = addresses.get(0).getLocality();
            StrAllLocation = addresses.get(0).getFeatureName() + "," + addresses.get(0).getSubLocality()+ "," + addresses.get(0).getSubAdminArea();

               /*  textloc.setText(textloc.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
*/
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("st_Latitude",st_Latitude);
            editor.putString("st_Longituude",st_Longituude);
            editor.putString("st_Loction",st_Loction);
            editor.putString("StrAllLocation",StrAllLocation);

            editor.apply();


            if (st_Longituude.isEmpty()) {

            } else {
                RestaurentList();
            }
        } catch (Exception e) {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getActivity(), "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onRefresh() {
        getLocation();
    }



    @Override
    public void onClick(View view) {
        if(view == textloc){
            loadFragment(new RestaurantSearch());
        }
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
