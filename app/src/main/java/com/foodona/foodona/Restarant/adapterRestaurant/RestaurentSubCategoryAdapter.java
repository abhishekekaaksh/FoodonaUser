package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
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
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.Utils.DatabaseHelper;
import com.foodona.foodona.Restarant.modal.Restaurent_subCatBean;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.foodona.foodona.Restarant.restaurant.SingleRestaurent;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodona.foodona.Restarant.restaurant.SingleRestaurent.bootom_sheet;
import static com.foodona.foodona.Restarant.restaurant.SingleRestaurent.total_item;
import static com.foodona.foodona.Restarant.restaurant.SingleRestaurent.total_rate;


public class RestaurentSubCategoryAdapter extends RecyclerView.Adapter<RestaurentSubCategoryAdapter.ViewHolder> {
    public Context context;
    int ab = 0;
    int totalitem;
    String cart;
    String strtotalcart;
    String strTotalPrioce;
    int p = 1;
    int result;
    String str_Veg_Price;
    String clickedItem;
    String SubCAt_ID_add;
    String SubCAt_Price_add;
    int counter = 0;
    String totalquanti;
    int quantity = 2;
    int i = 0;
    int value = 2;
    String hold = "";
    String str_dish1_price;
    String Str_Price;
    String Str_Image;
    int pos = 0;
    String str_dish_price;
    //   counter = 1;
    String str_dishname;
    String str_dishnameA;
    String imgurl;
    String sub_caty_id;
    String subType;
    DatabaseHelper mydb;
    String user_id;
    String restid;
    ArrayList<String> listItems;
    ArrayList<Restaurent_subCatBean> restaurantlists;
    ArrayList<Restaurent_subCatBean> restaurantCart_count;

    // private ArrayList<Modal_restaurantlist> list;


    public RestaurentSubCategoryAdapter(Context context, ArrayList<Restaurent_subCatBean> restaurantlists) {
        this.context = context;
        this.restaurantlists = restaurantlists;


        Log.e("InsideTheAdaptersingle", "InsideTheAdapter");
    }

    @Override
    public int getItemCount() {
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
    public RestaurentSubCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_rest_adapter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("InsideTheAdaptersingle1", "InsideTheAdapter");
        final Restaurent_subCatBean modal = restaurantlists.get(position);

        sub_caty_id = modal.getSub_cat_id();
        str_dishname = modal.getSub_cate_name();
          str_dish_price = modal.getSub_cat_price();
        str_dish1_price = modal.getSub_cat_price();
        subType = modal.getSub_cat_type();
        if (subType.equals("veg")) {
            holder.veg_image.setImageResource(R.drawable.veg);
        } else {
            holder.veg_image.setImageResource(R.drawable.nveg);
        }

        final String str_dish_desc = modal.getSub_cat_desc();
        imgurl = modal.getSub_cat_photo();
        Log.e("photophoto", modal.getSub_cat_photo());
        holder.dish_price1.setText(str_dish1_price);
        holder.dish_price.setText(str_dish_price);
        holder.dishnmame.setText(str_dishname);
        holder.dish_desc.setText(str_dish_desc);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        restid = preferences.getString("restuarent_id", "restuarent_id");
        Log.e("restuarent_id", restid);
        Picasso.with(context).load(imgurl).placeholder(R.drawable.banneone).error(R.drawable.bannertwo).into(holder.dish_image);

        final String StrCart = holder.cart_product_quantity_tv.getText().toString().trim();
        int totalquantity = Integer.parseInt(StrCart);
        totalitem = totalitem + totalquantity;
        Log.e("totalyiu", String.valueOf(totalitem));

        GET_Cart();

        SingleRestaurent.refreshCart(totalquanti,str_dish_price);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pos = (Integer)view.getTag();
                clickedItem = String.valueOf(position);
                Log.e("clickedItem++", clickedItem);
                Restaurent_subCatBean modal = restaurantlists.get(position);
                SubCAt_ID_add = modal.getSub_cat_id();
                Log.e("SubCAt_ID++", SubCAt_ID_add);

                int q_counter = 0;
                int q_ty = Integer.parseInt(clickedItem);
                //  q_ty = q_ty + 1;
                q_ty++;
                // String retes = String.valueOf(position);
                final String str_dish_price = modal.getSub_cat_price();
                int rate = Integer.parseInt(str_dish_price);
                //  rate = rate + 1;
                ++rate;
                rate = +rate;
                String items = "Total Items : " + totalitem;
                Toast.makeText(context, "Total Items : " + q_ty, Toast.LENGTH_SHORT).show();
                if (q_ty != 0) {
                    bootom_sheet.setVisibility(View.VISIBLE);

                    total_item.setText((strtotalcart + " ITEM"));

  //                  SingleRestaurent.refreshCart(totalquanti,str_dish_price);
//
                    total_rate.setText(("\u20B9" + strTotalPrioce));

                } else {
                    bootom_sheet.setVisibility(View.GONE);

                }
                if (q_ty > 0) {
                    holder.Line_cart_plus_minus_layout.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                }

            }
        });

        holder.cart_plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cart = holder.cart_product_quantity_tv.getText().toString().trim();
                SubCAt_ID_add = modal.getSub_cat_id();
                str_dishnameA = modal.getSub_cate_name();
                Str_Price = modal.getSub_cat_price();
                Str_Image = modal.getSub_cat_photo();
                Log.d("tre", cart);
                counter = Integer.parseInt(cart);
                counter = counter + 1;
                totalquanti = String.valueOf(counter);
                holder.cart_product_quantity_tv.setText(Integer.toString(counter));
                Add_Cart();


                SingleRestaurent.refreshCart(totalquanti,str_dish_price);

            }
        });
        holder.cart_minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubCAt_ID_add = modal.getSub_cat_id();
                String cart = holder.cart_product_quantity_tv.getText().toString().trim();
                str_dishnameA = modal.getSub_cate_name();
                Str_Price = modal.getSub_cat_price();
                Str_Image = modal.getSub_cat_photo();
                Log.d("tre", cart);
                counter = Integer.parseInt(cart);
                Toast.makeText(context, "cart" + counter, Toast.LENGTH_SHORT).show();


                    counter = counter - 1;

                    if (counter <= 0) {
                        holder.add.setVisibility(View.VISIBLE);
                        holder.Line_cart_plus_minus_layout.setVisibility(View.GONE);
                        totalquanti = String.valueOf(counter);
                        holder.cart_product_quantity_tv.setText("0");

                    } else {
                        holder.Line_cart_plus_minus_layout.setVisibility(View.VISIBLE);

                        holder.add.setVisibility(View.GONE);
                        totalquanti = String.valueOf(counter);
                        holder.cart_product_quantity_tv.setText(Integer.toString(counter));

                    }


                RestaurantRemove_Cart();


                SingleRestaurent.refreshCart(totalquanti,str_dish_price);

            }
        });

    }

    public void RestaurantRemove_Cart() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      /*  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity"},
                new String[]{user_id, restid, sub_caty_id, str_dish1_price, clickedItem});*/

        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"cart_id"},
                new String[]{SubCAt_ID_add});
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
                        Log.d("getcartresponse", GetFollo);
                        JSONObject jsonObject = new JSONObject(GetFollo);
                        String status = jsonObject.optString("status");
                        strtotalcart = jsonObject.optString("totalcart");
                        Log.e("strtotalcart", strtotalcart);
                        strTotalPrioce = jsonObject.optString("totalprice");
                        Log.e("strTotalPrioce", strTotalPrioce);
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

    public void Add_Cart() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity", "dish", "image"},
                new String[]{user_id, restid, SubCAt_ID_add, Str_Price, totalquanti, str_dishnameA, Str_Image});
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
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cart_product_quantity_tv;
        TextView dish_desc;
        TextView dishnmame;
        TextView dish_price;
        TextView dish_price1;
        ImageView dish_image;
        Button add;
        LinearLayout Line_cart_plus_minus_layout;
        ImageView cart_minus_img;
        ImageView cart_plus_img;
        ImageView veg_image;

        public ViewHolder(final View itemView) {
            super(itemView);
            /*context = itemView.getContext();*/
            add = itemView.findViewById(R.id.add);

            dish_price = itemView.findViewById(R.id.dish_price);
            cart_product_quantity_tv = itemView.findViewById(R.id.cart_product_quantity_tv);
            dish_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            dish_price1 = itemView.findViewById(R.id.dish_price1);
            Line_cart_plus_minus_layout = itemView.findViewById(R.id.cart_plus_minus_layout);
            dishnmame = itemView.findViewById(R.id.dishnmame);
            dish_desc = itemView.findViewById(R.id.dish_desc);

            dish_image = itemView.findViewById(R.id.dish_image);
            cart_minus_img = itemView.findViewById(R.id.cart_minus_img);
            veg_image = itemView.findViewById(R.id.veg_image);

            cart_plus_img = itemView.findViewById(R.id.cart_plus_img);
            user_id = AppPreferences.getSavedUser(context).getId();
        }


    }


}
