package com.foodona.foodona.Restarant.Activity;

import android.os.Bundle;

import com.foodona.foodona.Restarant.Bean.UserOrderHistoryBean;
import com.foodona.foodona.Restarant.Response.OrderHistoryResponse;
import com.foodona.foodona.Restarant.Utils.AppPreferences;
import com.foodona.foodona.Restarant.adapterRestaurant.AdapterOrderHistory;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.foodona.foodona.Restarant.restaurant.SingleRestaurent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.foodona.foodona.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderHistory extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recycler;
    AVLoadingIndicatorView avi;

    AdapterOrderHistory adapterOrderHistory;
    ImageView back;
    RecyclerView.LayoutManager layoutManager;
    List<UserOrderHistoryBean> orderhistorylist;
    UserOrderHistoryBean userOrderHistoryBean;
    String User_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_order_history);
        orderhistorylist = new ArrayList<>();
        User_id = AppPreferences.getSavedUser(OrderHistory.this).getId();
        find();
        getOrderHistory();
    }

    public void find() {
        avi = findViewById(R.id.bar);
        back = findViewById(R.id.back);
        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == back) {
            onBackPressed();
        }
    }

    private void getOrderHistory() {
        avi.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id"}, new
                String[]{User_id});
        Log.e("userOrderHistoryBean21", "11111");

        Call<OrderHistoryResponse> call = apiInterface.UserAllorder(builder.build());
        call.enqueue(new Callback<OrderHistoryResponse>() {
            @Override
            public void onResponse(Call<OrderHistoryResponse> call, Response<OrderHistoryResponse> response) {
                Log.e("userOrderHistoryBean1s", "11111");
                avi.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body().getSuccess().equals("1")) {
                    for (int i = 0; i < response.body().getUser_order().size(); i++) {
                        userOrderHistoryBean = new UserOrderHistoryBean();
                        userOrderHistoryBean.setResta_name(response.body().getUser_order().get(i).getResta_name());
                        userOrderHistoryBean.setAddress(response.body().getUser_order().get(i).getAddress());
                        userOrderHistoryBean.setStatus(response.body().getUser_order().get(i).getStatus());
                        orderhistorylist.add(userOrderHistoryBean);
                    }
                    Log.e("userOrderHistoryBean", "" + orderhistorylist.size());
                    adapterOrderHistory = new AdapterOrderHistory(getApplicationContext(), orderhistorylist);
                    recycler.setAdapter(adapterOrderHistory);
                } else {
                    Log.e("userOrderHistory1ize", "" + "else");
                }
            }

            @Override
            public void onFailure(Call<OrderHistoryResponse> call, Throwable t) {
                onApiFailure(call, t);
                Log.e("userOrderBean1s95", "959595");

                //  Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void onApiFailure(Call<OrderHistoryResponse> call, Throwable t) {


        // stopLoadingDialog();
        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Please try later", Toast.LENGTH_SHORT).show();
    }


}
