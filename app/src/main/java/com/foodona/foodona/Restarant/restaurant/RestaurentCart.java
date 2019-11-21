package com.foodona.foodona.Restarant.restaurant;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Bean.AddressBean;
import com.foodona.foodona.Restarant.Map.MapsActivity;
import com.foodona.foodona.Restarant.Map.mapsm;
import com.foodona.foodona.Restarant.Response.AddressResponse;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.adapterRestaurant.Adapter_Restaurent_Cart;
import com.foodona.foodona.Restarant.adapterRestaurant.DialogAdapterAddress;
import com.foodona.foodona.Restarant.modal.model_Get_Restaurent_price;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestaurentCart extends Fragment implements View.OnClickListener {
    int ab = 0;
    int p = 1;
    int TaotalPrice;
    int result;
    TextView dishna;
    Adapter_Restaurent_Cart adapter_restaurent_cart;
    public static TextView itemtotal;
    ImageView dish_cart;
    AVLoadingIndicatorView avi;
    View rootView;
    TextView cate;
    TextView veigitable;
    TextView veg_price;
    TextView qua_price;
    String str_Dishna;
    TextView location;
    TextView change;
    ImageView cancel;
    TextView tv_addaddress;
    RecyclerView getrecycleaddress;
    LinearLayout cart_plus_minus_layout;
    String str_Dish_Image;
    String str_Cate;
    RecyclerView.LayoutManager layoutManager;
    Dialog pickUpOrderDialog;
    List<AddressBean> list;

    AddressBean addressBean;
    DialogAdapterAddress dialogAdapterAddress;
    String str_Veigitable;
    String str_Veg_Price;
    String str_Qua_Price;
    ImageView cart_minus_img;
    ImageView cart_plus_img;
    TextView quantity_tv;
    String user_id;
    String Str_Lat;
    String Str_Long;
    String Str_Address;
    String StrAllLocation;
    RecyclerView recyclercart;
    Button add;
    String res_id;
    String strSTR;
    String Str_Description = "ksgbddkjs";
    String Str_Note = "hsdi";
    String Str_TotalPrice = "123";
    JSONArray jsonArray;
    Button total_order;
    ArrayList<model_Get_Restaurent_price> restaurantlists;
    String Strloc;
    String Stradd;
    String StringAddress;
  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_cart);
        Intent intent = getIntent();
        Strloc=intent.getStringExtra("strlocality");
        Stradd=intent.getStringExtra("strcountry");
        StringAddress=Strloc+Stradd;
        user_id = AppPreferences.getSavedUser(RestaurentCart.this).getId();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Str_Lat = sharedPreferences.getString("st_Latitude", "value1");
        Log.e("Str_Lat=", Str_Lat);
        StrAllLocation = sharedPreferences.getString("StrAllLocation", "value0");
        Log.e("StrAllLocation=", StrAllLocation);

        Str_Long = sharedPreferences.getString("st_Longituude", "value2");
        Log.e("Str_Long=", Str_Long);
        Str_Address = sharedPreferences.getString("st_Loction", "value3");
        Log.e("str_Dishna=", Str_Address);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = preferences.getString("name", "defaultValue");
        Log.e("str_Dishna", value);
        find();
*//*if (Str_Address.equals(sharedPreferences.getString("st_Loction", "value3"))){

}*//*
        str_Veigitable = getIntent().getStringExtra("str_dishname");
        str_Cate = getIntent().getStringExtra("str_dish_desc");
        str_Veg_Price = getIntent().getStringExtra("str_dish_price");
        str_Dish_Image = getIntent().getStringExtra("imgurl");
        Picasso.with(this).load(str_Dish_Image).placeholder(R.drawable.banneone).error(R.drawable.bannertwo).into(dish_cart);
        location.setText(StrAllLocation);
        veigitable.setText(str_Veigitable);
        cate.setText(str_Cate);
        veg_price.setText(str_Veg_Price);
        qua_price.setText(str_Qua_Price);
        dishna.setText(value);
        GET_Cart();
        ///  itemtotal.setText(TaotalPrice);

    }*/


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_restaurent_cart, container, false);
        //Intent intent = getIntent();
        //Strloc=intent.getStringExtra("strlocality");
        // Stradd=intent.getStringExtra("strcountry");
        // StringAddress=Strloc+Stradd;
        //loadFragment(new RestaurentCart());

        user_id = AppPreferences.getSavedUser(getActivity()).getId();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Str_Lat = sharedPreferences.getString("st_Latitude", "value1");
        Log.e("Str_Lat=", Str_Lat);
        StrAllLocation = sharedPreferences.getString("StrAllLocation", "value0");
        Log.e("StrAllLocation=", StrAllLocation);

        Str_Long = sharedPreferences.getString("st_Longituude", "value2");
        Log.e("Str_Long=", Str_Long);
        Str_Address = sharedPreferences.getString("st_Loction", "value3");
        Log.e("str_Dishna=", Str_Address);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String value = preferences.getString("name", "defaultValue");
        Log.e("str_Dishna", value);
        find();
/*if (Str_Address.equals(sharedPreferences.getString("st_Loction", "value3"))){

}*/
        str_Veigitable = getActivity().getIntent().getStringExtra("str_dishname");
        str_Cate = getActivity().getIntent().getStringExtra("str_dish_desc");
        str_Veg_Price = getActivity().getIntent().getStringExtra("str_dish_price");
        str_Dish_Image = getActivity().getIntent().getStringExtra("imgurl");
        Picasso.with(getActivity()).load(str_Dish_Image).placeholder(R.drawable.banneone).error(R.drawable.bannertwo).into(dish_cart);
        location.setText(StrAllLocation);
        veigitable.setText(str_Veigitable);
        cate.setText(str_Cate);
        veg_price.setText(str_Veg_Price);
        qua_price.setText(str_Qua_Price);
        dishna.setText(value);
        GET_Cart();
        return rootView;

    }
/*    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
          getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }*/
    public void find() {
        add = rootView.findViewById(R.id.add);
        avi = rootView.findViewById(R.id.bar);
        location = rootView.findViewById(R.id.location);
        change = rootView.findViewById(R.id.change);

        itemtotal = rootView.findViewById(R.id.itemtotal);
        cart_plus_minus_layout = rootView.findViewById(R.id.cart_plus_minus_layout);
        cart_plus_img = rootView.findViewById(R.id.cart_plus_img);
        cart_minus_img = rootView.findViewById(R.id.cart_minus_img);
        quantity_tv = rootView.findViewById(R.id.quantity_tv);
        dishna = rootView.findViewById(R.id.dishna);
        dish_cart = rootView.findViewById(R.id.dish_cart);
        cate = rootView.findViewById(R.id.cate);
        total_order = rootView.findViewById(R.id.total_order);
        veigitable = rootView.findViewById(R.id.veigitable);
        veg_price = rootView.findViewById(R.id.veg_price);
        qua_price = rootView.findViewById(R.id.qua_price);
        cart_plus_img.setOnClickListener(this);
        cart_minus_img.setOnClickListener(this);
        recyclercart = rootView.findViewById(R.id.recyclercart);
        recyclercart.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercart.setLayoutManager(layoutManager);
        recyclercart.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclercart.setAdapter(adapter_restaurent_cart);
        total_order.setOnClickListener(this);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == total_order) {
            book_order();
            startActivity(new Intent(getActivity(), OrederconfirmationActivity.class));
            //finish();
        }
        if (view == change) {
            showPickUpAddress();
        }
/*
        if (view == cart_minus_img) {
            if (ab == 1) {
                add.setVisibility(View.GONE);
                ab = ab - 1;
            }
            p = ab;
            result = Integer.parseInt(str_Veg_Price);
            TaotalPrice = ab * result;
            System.out.println(TaotalPrice);
            //     int valz = st_VegPrice * ab;
            quantity_tv.setText(Integer.toString(ab));
            //  itemtotal.setText(TaotalPrice);
        }
        if (view == cart_plus_img) {
            add.setVisibility(View.GONE);
            ab = ab + 1;
            result = Integer.parseInt(str_Veg_Price);
            TaotalPrice = ab * result;
            Log.e("result", String.valueOf(TaotalPrice));
            Log.e("ab", String.valueOf(ab));
            Log.e("reksult", String.valueOf(result));
            cart_plus_minus_layout.setVisibility(View.VISIBLE);
            p = ab;
            quantity_tv.setText(Integer.toString(ab));
//            itemtotal.setText(TaotalPrice);
        }
        itemtotal.setText(String.valueOf(TaotalPrice));
*/
    }

    private void showPickUpAddress() {
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // custom dialog
        pickUpOrderDialog = new Dialog(getActivity());
        pickUpOrderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pickUpOrderDialog.setContentView(R.layout.dialog_getaddress);
        //orderList = new ArrayList<>();
        list = new ArrayList<>();
        addressBean = new AddressBean();
        pickUpOrderDialog.setCancelable(false);
        Window window = pickUpOrderDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        // toolbar = (Toolbar) pickUpOrderDialog.findViewById(R.id.toolbar);
        cancel = pickUpOrderDialog.findViewById(R.id.cancel);
        tv_addaddress = pickUpOrderDialog.findViewById(R.id.tv_addaddress);
        getrecycleaddress = pickUpOrderDialog.findViewById(R.id.getrecycleaddress);
        getrecycleaddress.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        getrecycleaddress.setLayoutManager(layoutManager);
        getrecycleaddress.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        // tvorderId = pickUpOrderDialog.findViewById(R.id.tvorderId);
        // accept = pickUpOrderDialog.findViewById(R.id.accept);
        //reject = pickUpOrderDialog.findViewById(R.id.reject);
        getnAddressApi();
        tv_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), mapsm.class));
                //finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AcceptOrderComplet();
                pickUpOrderDialog.dismiss();
            }
        });

        pickUpOrderDialog.show();

    }

    private void getnAddressApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id"}, new
                String[]{user_id});
        Log.e("getaddress", "11111");
        Call<AddressResponse> call = apiInterface.userGetAddress(builder.build());
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                Log.e("orderres", "11111");
                if (response.isSuccessful() && response.body().getStatus().equals("Success")) {
                    list = response.body().getInfo();

                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        addressBean.setAddress(response.body().getInfo().get(i).getAddress());
                        addressBean.setId(response.body().getInfo().get(i).getId());
                        Log.e("orderresaddressasa", "" + response.body().getInfo().get(i).getAddress());
//list.add(addressBean);
                    }
                    Log.e("orderresaddress", "" + list.size());
                    list.add(addressBean);
                    dialogAdapterAddress = new DialogAdapterAddress(getActivity(), list);
                    getrecycleaddress.setAdapter(dialogAdapterAddress);
                    dialogAdapterAddress.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                //onApiFailure(call, t);
                Log.e("99595", "959595");


            }
        });
    }


    public void GET_Cart() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id"},
                new String[]{user_id});
        Call<ResponseBody> call = apiInterface.Restaurent_Get_Cart(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("gettcart", response.body().toString());
                if (response.isSuccessful()) {
                    Log.d("resturentgetcart", response.body().toString());
                    try {
                        String GetFollo = response.body().string();
                        JSONObject jsonObject = new JSONObject(GetFollo);
                        String status = jsonObject.optString("status");
                        String strtotalcart = jsonObject.optString("totalcart");
                        String strTotalPrioce = jsonObject.optString("totalprice");
                        itemtotal.setText(strTotalPrioce);
                        String msg = jsonObject.optString("msg");
                        if (status.equals("Success")) {
                            restaurantlists = new ArrayList<>();
                            JSONArray mJSONObject1 = jsonObject.getJSONArray("info");
                            Log.e("arraylenthlk", String.valueOf(mJSONObject1.length()));
                            for (int i = 0; i < mJSONObject1.length(); i++) {
                                JSONObject mJSONObject = mJSONObject1.getJSONObject(i);
                                model_Get_Restaurent_price model_get_restaurent_price = new model_Get_Restaurent_price();
                                model_get_restaurent_price.setId(mJSONObject.getString("id"));
                                model_get_restaurent_price.setUser_id(mJSONObject.getString("user_id"));
                                model_get_restaurent_price.setRes_id(mJSONObject.getString("res_id"));
                                model_get_restaurent_price.setMenu_id(mJSONObject.getString("menu_id"));
                                model_get_restaurent_price.setPrice(mJSONObject.getString("price"));
                                model_get_restaurent_price.setQuantity(mJSONObject.getString("quantity"));
                                model_get_restaurent_price.setDish(mJSONObject.getString("dish"));
                                model_get_restaurent_price.setImage(mJSONObject.getString("image"));
                                restaurantlists.add(model_get_restaurent_price);
                            }
                            if (restaurantlists.size() > 0) {


                                adapter_restaurent_cart = new Adapter_Restaurent_Cart(getActivity(), restaurantlists);
                                recyclercart.setAdapter(adapter_restaurent_cart);
                            } else {
                                Log.e("ERRRR", "No data Found");
                            }
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }

            private String convertObjectArrayToString(Object[] arr, String delimiter) {
                StringBuilder sb = new StringBuilder();
                for (Object obj : arr)
                    sb.append(obj.toString()).append(delimiter);
                return sb.substring(0, sb.length() - 1);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onApiFailure(call, t);
                // Toast.makeText(SingleRestaurent.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void book_order() {


        JSONArray myItem = new JSONArray();

        res_id = restaurantlists.get(0).getRes_id();
        for (int j = 0; j < restaurantlists.size(); j++) {
            JSONObject myobject = new JSONObject();
            Log.e("menu_id", "" + restaurantlists.get(j).getMenu_id());
            try {

                myobject.put("menu_id", restaurantlists.get(j).getMenu_id());
                myobject.put("menu_name", restaurantlists.get(j).getDish());
                myobject.put("quantity", restaurantlists.get(j).getQuantity());
                myobject.put("total_price", restaurantlists.get(j).getPrice());

                myItem.put(myobject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        String strdata = myItem.toString();
        Log.e("strdata", "" + myItem);

        avi.setVisibility(View.VISIBLE);

        // Log.e("array data", ""+str);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "address", "lat", "long", "food_desc", "description", "notes", "total_price"},
                new String[]{user_id, res_id, Str_Address, Str_Lat, Str_Long, strdata, Str_Description, Str_Note, Str_TotalPrice});
        Call<ResponseBody> call = apiInterface.Book_ORder(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                avi.setVisibility(View.GONE);
                Log.d("gettcart", response.body().toString());
                if (response.isSuccessful()) {
                    Log.d("resturentgetcart", response.body().toString());
                    try {
                        String GetFollo = response.body().string();
                        Log.d("getcartresponse", GetFollo);
                        JSONObject jsonObject = new JSONObject(GetFollo);
                        String status = jsonObject.optString("status");
                        // strtotalcart = jsonObject.optString("totalcart");
                        //    Log.e("strtotalcart", strtotalcart);
                        // strTotalPrioce = jsonObject.optString("totalprice");
                        //  Log.e("strTotalPrioce", strTotalPrioce);
                        String msg = jsonObject.optString("msg");
                        String info = jsonObject.optString("info");
                        if (status.equalsIgnoreCase("Success")) {
                            //       JSONObject jsonObject1 = jsonArray.getJSONObject(i);
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
                // Toast.makeText(SingleRestaurent.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onApiFailure(Call<ResponseBody> call, Throwable t) {
        avi.setVisibility(View.GONE);
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
    }

}
