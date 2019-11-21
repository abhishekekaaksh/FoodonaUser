package com.foodona.foodona.Restarant.restaurant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.INterface.RestCart;
import com.foodona.foodona.Restarant.Navigation.BottomNavigationActivity;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.adapterRestaurant.AdapterSingle;
import com.foodona.foodona.Restarant.modal.Followmodal;
import com.foodona.foodona.Restarant.modal.Modal_restaurantlist;
import com.foodona.foodona.Restarant.modal.Restaurent_subCatBean;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SingleRestaurent extends Fragment implements View.OnClickListener, RestCart {
    String strRestaurent_Name;
    View rootView;

    SingleRestaurent singleRestaurent;
    String User_id;
    String stCat_id;
    String stRes_id;
    String isFav = "";
    String strRestaurent_Category;
    String strRestaurent_delivery;
    String strRestaurent_Rating;
    TextView hotelnamee, hoteldishnamee, rating, distance;
    ImageView backimage;
    ImageView favorate;
     public static TextView price;
    public static TextView items;
    TextView viewcart;
    String Strtype;
    Followmodal followmodal;
    String stSub = "";
    FragmentTransaction fragmentTransaction;
    ArrayList<Modal_restaurantlist> modalArrya;
    AdapterSingle adapterSingle;
    public static RelativeLayout addcr;
    public static LinearLayout bootom_sheet;
    public static TextView total_item, total_rate;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.single_restaurent, container, false);
        stRes_id = getArguments().getString("cat_id");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String str_dish_price = sharedPreferences.getString("str_ditem_price", "value1");
        String str_dish_count = sharedPreferences.getString("str_TotalCart", "value2");
        Log.e("str_ditem_price", str_dish_price);
        Log.e("str_TotalCartd", str_dish_count);
        stCat_id = getArguments().getString("cat_id");
        User_id = AppPreferences.getSavedUser(getActivity()).getId();
        find();
        strRestaurent_Name = getArguments().getString("rest_name");
        strRestaurent_Category = getArguments().getString("dish_name");
        strRestaurent_delivery = getArguments().getString("delivery_name");
        strRestaurent_Rating = getArguments().getString("reting");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", strRestaurent_Name);
        editor.putString("restuarent_id", stRes_id);
        editor.apply();
        hotelnamee.setText(strRestaurent_Name);
        hoteldishnamee.setText(strRestaurent_Category);
        distance.setText(strRestaurent_delivery + " " + "mins");
        rating.setText(strRestaurent_Rating + ".0");
        //price.setText("\u20B9" + str_dish_price);
        //items.setText(str_dish_count + " Items");
        Log.e("stCat_id", stCat_id);
        Log.e("retses_dish", strRestaurent_Category);
        return rootView;
    }
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_restaurent);
        *//*SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.edit().putString("strRestaurent_Name","Abhi").apply();

        editor.apply();*//*
        stRes_id = getIntent().getStringExtra("cat_id");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String str_dish_price = sharedPreferences.getString("str_ditem_price", "value1");
        String str_dish_count = sharedPreferences.getString("str_TotalCart", "value2");
        Log.e("str_ditem_price", str_dish_price);
        Log.e("str_TotalCartd", str_dish_count);
        stCat_id = getIntent().getStringExtra("cat_id");
        User_id = AppPreferences.getSavedUser(SingleRestaurent.this).getId();
        find();
        strRestaurent_Name = getIntent().getStringExtra("rest_name");
        strRestaurent_Category = getIntent().getStringExtra("dish_name");
        strRestaurent_delivery = getIntent().getStringExtra("delivery_name");
        strRestaurent_Rating = getIntent().getStringExtra("reting");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", strRestaurent_Name);
        editor.putString("restuarent_id", stRes_id);
        editor.apply();
        hotelnamee.setText(strRestaurent_Name);
        hoteldishnamee.setText(strRestaurent_Category);
        distance.setText(strRestaurent_delivery + " " + "mins");
        rating.setText(strRestaurent_Rating + ".0");
        //price.setText("\u20B9" + str_dish_price);
        //items.setText(str_dish_count + " Items");
        Log.e("stCat_id", stCat_id);
        Log.e("retses_dish", strRestaurent_Category);
    }*/

    public static void refreshCart(String totalquanti, String str_dish_price) {




        total_rate.setText("\u20B9"+str_dish_price);
        total_item.setText(totalquanti+" Items");


    }



    public void find() {
        addcr = rootView.findViewById(R.id.addcr);
        backimage = rootView.findViewById(R.id.backimage);
        recyclerView = rootView.findViewById(R.id.dishrecycler);
        recyclerView.setHasFixedSize(true);
        hotelnamee = rootView.findViewById(R.id.hotelnamee);
        distance = rootView.findViewById(R.id.distance);
        rating = rootView.findViewById(R.id.rating);
        favorate = rootView.findViewById(R.id.favorate);
        bootom_sheet = rootView.findViewById(R.id.bootom_sheet);
        total_item = rootView.findViewById(R.id.total_item);
        total_rate = rootView.findViewById(R.id.total_rate);

        // viewcart=findViewById(R.id.viewcart);
        //price = findViewById(R.id.price);
        //items = findViewById(R.id.items);
        hoteldishnamee = rootView.findViewById(R.id.hoteldishnamee);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //viewcart.setOnClickListener(this);
        favorate.setOnClickListener(this);
        backimage.setOnClickListener(this);
        addcr.setOnClickListener(this);
        Single_rest();
        GetFollow();
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/

    @Override
    public void onClick(View view) {
        if (view == backimage) {
            //onBackPressed();
        }
        if (view == favorate) {
            if (isFav.equals("1")) {
                favorate.setImageResource(R.drawable.ic_favorite_red_24dp);
            } else {
                isFav = "0";
                favorate.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            }
            GetFollowAdd();
        }
        if (view == addcr) {
           /* Intent intent=new Intent(SingleRestaurent.this,RestaurentCart.class);
            intent.putExtra("redirectedFrom","SingleRestaurent");
            startActivity(intent);*/
            Fragment restaurentCart = new RestaurentCart();
            FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, restaurentCart);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void GetFollowAdd() {


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "is_fav"},
                new String[]{User_id, stRes_id, isFav});
        Call<ResponseBody> call = apiInterface.RestaurantADD_Fav(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("6546", response.body().toString());
                if (response.isSuccessful()) {
                    Log.d("resturentfollow", response.body().toString());

                    try {
                        String GetFollo = response.body().string();
                        Log.d("GetFollo", GetFollo);
                        JSONObject jsonObject = new JSONObject(GetFollo);
                        String status = jsonObject.optString("status");
                        String msg = jsonObject.optString("msg");
                        if (status.equalsIgnoreCase("Success")) {
                            JSONObject mJsonObject = jsonObject.optJSONObject("info");
                            Followmodal followmodal = new Followmodal();
                            followmodal.setId(mJsonObject.getString("id"));
                            followmodal.setIs_fav(mJsonObject.getString("is_fav"));
                            isFav = mJsonObject.getString("is_fav");
                            if (isFav.equals("1")) {
                                isFav = "0";
                                favorate.setImageResource(R.drawable.ic_favorite_red_24dp);
                            } else {
                                isFav = "1";
                                favorate.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                            }
                            //       JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        } else {
                            isFav = "1";
                            favorate.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                        }
                    } catch (JSONException e) {
                    } catch (IOException e) {
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

    public void GetFollow() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id"},
                new String[]{User_id, stRes_id});
        Call<ResponseBody> call = apiInterface.Restaurant_Fav(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("6546", response.body().toString());
                if (response.isSuccessful()) {
                    Log.d("resturentfollow", response.body().toString());

                    try {
                        String GetFollo = response.body().string();
                        Log.d("GetFollo", GetFollo);
                        JSONObject jsonObject = new JSONObject(GetFollo);
                        String status = jsonObject.optString("status");
                        String msg = jsonObject.optString("msg");
                        if (status.equalsIgnoreCase("Success")) {
                            JSONObject mJsonObject = jsonObject.optJSONObject("info");
                            Followmodal followmodal = new Followmodal();
                            followmodal.setId(mJsonObject.getString("id"));
                            followmodal.setIs_fav(mJsonObject.getString("is_fav"));
                            isFav = mJsonObject.getString("is_fav");

                            if (isFav.equals("1")) {
                                isFav = "0";
                                favorate.setImageResource(R.drawable.ic_favorite_red_24dp);
                            } else {
                                isFav = "1";
                                favorate.setImageResource(R.drawable.ic_favorite_border_red_24dp);

                            }

                            //       JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        } else {
                            isFav = "1";
                            favorate.setImageResource(R.drawable.ic_favorite_border_red_24dp);

                        }
                    } catch (JSONException e) {

                    } catch (IOException e) {
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


    public void Single_rest() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"res_id"}
                , //new String[]{stCat_id});
                new String[]{stCat_id});
        Call<ResponseBody> call = apiInterface.Restaurent_Menu(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("", response.body().toString());

                if (response.isSuccessful()) {
                    try {
                        String resturentMenu = response.body().string();
                        Log.d("resturentMenu", resturentMenu);
                        JSONObject jsonObject = new JSONObject(resturentMenu);
                        String status = jsonObject.optString("status");
                        String msg = jsonObject.optString("msg");
                        if (status.equals("Success")) {
                            modalArrya = new ArrayList<>();
                            JSONArray jsonArray = jsonObject.getJSONArray("info");
                            Log.e("arraylenth", String.valueOf(jsonArray.length()));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Log.e("iddddd", "11111");
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                Modal_restaurantlist modal_restaurantlist = new Modal_restaurantlist();
                                modal_restaurantlist.setCategory_id(jsonObject1.getString("id"));
                                Log.e("iddddd", jsonObject1.getString("id"));
                                modal_restaurantlist.setCategory_name(jsonObject1.getString("name"));
                                if (jsonObject1.getString("sub").equalsIgnoreCase("false")) {
                                /*  Restaurent_subCatBean mRestaurent_subCatBean=new Restaurent_subCatBean();
                                  mRestaurent_subCatBean.setSub_cat_id("00");
                                  mRestaurent_subCatBean.setSub_cat_desc("Select Sub Category");
                                  mRestaurent_subCatBean.setSub_cat_photo("Select Sub Category");
                                  mRestaurent_subCatBean.setSub_cat_price("Select Sub Category");
                                  mRestaurent_subCatBean.setSub_cate_name("Select Sub Category");

                                  ArrayList<Restaurent_subCatBean> mListSubCat=new ArrayList<>();
                                  mListSubCat.add(mRestaurent_subCatBean);*/

                                } else {
                                    ArrayList<Restaurent_subCatBean> mListSubCat = new ArrayList<>();
                                    JSONArray jsonArray1 = jsonObject1.getJSONArray("sub");
                                    Log.e("arraysublenth", String.valueOf(jsonArray1.length()));
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                        Restaurent_subCatBean mRestaurent_subCatBean = new Restaurent_subCatBean();
                                        mRestaurent_subCatBean.setSub_cat_id(jsonObject2.getString("id"));
                                        mRestaurent_subCatBean.setSub_cat_desc(jsonObject2.getString("desc"));
                                        mRestaurent_subCatBean.setSub_cat_type(jsonObject2.getString("type"));

                                        mRestaurent_subCatBean.setSub_cat_photo(jsonObject2.getString("image"));
                                        Log.e("imageesingle", jsonObject2.getString("image"));

                                        mRestaurent_subCatBean.setSub_cat_price(jsonObject2.getString("price"));
                                        mRestaurent_subCatBean.setSub_cate_name(jsonObject2.getString("name"));

                                        mListSubCat.add(mRestaurent_subCatBean);


                                    }
                                    modal_restaurantlist.setmLoistSubCat(mListSubCat);

                                    modalArrya.add(modal_restaurantlist);
                                }
                            }
                            if (modalArrya.size() > 0) {

                                adapterSingle = new AdapterSingle(getActivity(), modalArrya);
                                recyclerView.setAdapter(adapterSingle);
                            }

                        }

                    } catch (Exception e) {

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
        //avi.setVisibility(View.GONE);
        //swipeRefreshLayout.setRefreshing(false);

        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void foo() {
        Log.e("qwert", "qwerty");

    }
}
