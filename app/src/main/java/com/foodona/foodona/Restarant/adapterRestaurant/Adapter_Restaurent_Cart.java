package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.modal.Modal_RestaurentName;
import com.foodona.foodona.Restarant.modal.model_Get_Restaurent_price;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.foodona.foodona.Restarant.restaurant.SingleRestaurent;
import com.squareup.picasso.Picasso;

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

public class Adapter_Restaurent_Cart extends RecyclerView.Adapter<Adapter_Restaurent_Cart.ViewHolder> {
    String Str_User_id;
    String Str_Res_id;
    String Str_Address;
    String Str_Lat;
    String Str_Long;
    String Str_Food_Desc;
    String Str_Description;
    String Str_TotalQuantity;
    int counter = 0;
    String totalquanti;
    String Str_Notes;
    String Str_sub_cat_id;
    String Str_Total_price;
    String Str_Price;
    String clickedItem;

    int totalitem;

    String strimgurl;
    public Context context;
    ArrayList<model_Get_Restaurent_price> restaurantlists;

    public Adapter_Restaurent_Cart(Context context, ArrayList<model_Get_Restaurent_price> restaurantlists) {
        this.context = context;
        this.restaurantlists = restaurantlists;
        Log.e("InsideTheAdaptercart", "InsideTheAdaptercart");
    }

    @Override
    public int getItemCount() {
        Log.e("restaturentlist1size", "" + restaurantlists.size());
        return restaurantlists.size();
    }

    public Object getItem(int position) {
        return context;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Adapter_Restaurent_Cart.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adpter_restaurent_cart, viewGroup, false);
        return new Adapter_Restaurent_Cart.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("InsideTheAdapter3", "InsideTheAdapter3");


        final model_Get_Restaurent_price modal = restaurantlists.get(position);


        String str_id = modal.getId();
        String vegitable = modal.getDish();
        //Str_Total_price=modal.get
        String str_del_quantity = modal.getQuantity();
        String str_price = modal.getPrice();
        holder.veg_price.setText("\u20B9 " + str_price);
        holder.veigitable.setText(vegitable);
        holder.quantity_tv.setText(str_del_quantity);
        Picasso.with(context).load(strimgurl).placeholder(R.drawable.banneone).error(R.drawable.bannertwo).into(holder.rest_cartpic);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem = String.valueOf(position);
                model_Get_Restaurent_price modal = restaurantlists.get(position);
                Str_sub_cat_id = modal.getMenu_id();
                int q_counter = 0;
                int q_ty = Integer.parseInt(clickedItem);
                q_ty++;
                final String str_dish_price = modal.getPrice();
                int rate = Integer.parseInt(str_dish_price);
                //  rate = rate + 1;
                ++rate;
                rate = +rate;
                String items = "Total Items : " + totalitem;
                Toast.makeText(context, "Total Items : " + q_ty, Toast.LENGTH_SHORT).show();
                if (q_ty != 0) {
                    //bootom_sheet.setVisibility(View.VISIBLE);

                    ////total_item.setText((strtotalcart + " ITEM"));

                    //                  SingleRestaurent.refreshCart(totalquanti,str_dish_price);
//
                    //total_rate.setText(("\u20B9" + strTotalPrioce));

                } else {
                    // bootom_sheet.setVisibility(View.GONE);

                }
                if (q_ty > 0) {
                    holder.cart_plus_minus_layout.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                }
            }
        });
        holder.cart_plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cart = holder.quantity_tv.getText().toString().trim();
                Str_User_id = modal.getUser_id();
                Str_Res_id = modal.getRes_id();

                Str_sub_cat_id = modal.getMenu_id();
                strimgurl = modal.getImage();
                Str_Food_Desc = modal.getDish();
                Str_Description = modal.getMenu_id();
                Str_TotalQuantity = modal.getQuantity();
                Str_Price = modal.getPrice();
                counter = Integer.parseInt(cart);
                counter = counter + 1;
                totalquanti = String.valueOf(counter);
                holder.quantity_tv.setText(Integer.toString(counter));
                Add_Cart();
            }
        });
        holder.cart_minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cart = holder.quantity_tv.getText().toString().trim();

                Str_User_id = modal.getUser_id();
                Str_Res_id = modal.getRes_id();
                Str_sub_cat_id = modal.getMenu_id();
                strimgurl = modal.getImage();
                Str_Food_Desc = modal.getDish();
                Str_Description = modal.getMenu_id();
                Str_TotalQuantity = modal.getQuantity();
                Str_Price = modal.getPrice();
                counter = Integer.parseInt(cart);
                counter = counter - 1;

                if (counter <= 0) {
                    holder.add.setVisibility(View.VISIBLE);
                    holder.cart_plus_minus_layout.setVisibility(View.GONE);

                    totalquanti = String.valueOf(counter);
                    holder.quantity_tv.setText("0");

                } else {
                    holder.cart_plus_minus_layout.setVisibility(View.VISIBLE);

                    holder.add.setVisibility(View.GONE);
                    totalquanti = String.valueOf(counter);
                    holder.quantity_tv.setText(Integer.toString(counter));

                }
            /*    if (counter<=0){
                    holder.add.setVisibility(View.VISIBLE);
                    holder.Line_cart_plus_minus_layout.setVisibility(View.GONE);

̥                }else {
                    holder.Line_cart_plus_minus_layout.setVisibility(View.VISIBLE);

                    holder.add.setVisibility(View.GONE);

                }*/
                //totalquanti = String.valueOf(counter);
                //  holder.quantity_tv.setText(Integer.toString(counter));
                RestaurantRemove_Cart();
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView veigitable;
        TextView veg_price;
        TextView quantity_tv;
        ImageView rest_cartpic;
        ImageView cart_minus_img;
        ImageView cart_plus_img;
        Button add;
        LinearLayout cart_plus_minus_layout;


        public ViewHolder(final View itemView) {
            super(itemView);
            /*context = itemView.getContext();*/
            add = itemView.findViewById(R.id.add);
            cart_plus_minus_layout = itemView.findViewById(R.id.cart_plus_minus_layout);
            cart_minus_img = itemView.findViewById(R.id.cart_minus_img);
            cart_plus_img = itemView.findViewById(R.id.cart_plus_img);
            rest_cartpic = itemView.findViewById(R.id.rest_cartpic);
            veigitable = itemView.findViewById(R.id.veigitable);
            veg_price = itemView.findViewById(R.id.veg_price);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);


        }
    }

    public void RestaurantRemove_Cart() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      /*  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity"},
                new String[]{user_id, restid, sub_caty_id, str_dish1_price, clickedItem});*/

        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"cart_id"},
                new String[]{Str_sub_cat_id});
        Call<ResponseBody> call = apiInterface.Restaurant_Remove_Cart(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Addcart", response.body().toString());
                if (response.isSuccessful()) {
                    Log.d("resturentaddcart", response.body().toString());
                    try {
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


    public void Add_Cart() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      /*  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity"},
                new String[]{user_id, restid, sub_caty_id, str_dish1_price, clickedItem});*/

        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity", "dish", "image"},
                new String[]{Str_User_id, Str_Res_id, Str_sub_cat_id, Str_Price, totalquanti, Str_Food_Desc, strimgurl});
        Call<ResponseBody> call = apiInterface.Restaurant_add_Cart(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Addcart", response.body().toString());
                if (response.isSuccessful()) {
                    Log.d("resturentaddcart", response.body().toString());
                    try {
                        String GetFollo = response.body().string();
                        Log.d("addcartresponse", GetFollo);
                        JSONObject jsonObject = new JSONObject(GetFollo);
                        String status = jsonObject.optString("status");
                        //strtotalcart=jsonObject.optString("totalcart");
                        //  Log.e("strtotalcart",strtotalcart);
                        //   strTotalPrioce=jsonObject.optString("totalprice");
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
        //Log.e("error", t.toString());
        //avi.setVisibility(View.GONE);
        //swipeRefreshLayout.setRefreshing(false);

        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Please try later", Toast.LENGTH_SHORT).show();
    }
   /* public void book_order() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id","res_id","address","lat","long","food_desc","description","notes","total_price"},
                new String[]{Str_User_id});
        Call<ResponseBody> call = apiInterface.Book_ORder(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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

        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Please try later", Toast.LENGTH_SHORT).show();
    }*/
}