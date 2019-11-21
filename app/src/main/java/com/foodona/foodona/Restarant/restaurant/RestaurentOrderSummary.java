package com.foodona.foodona.Restarant.restaurant;

import android.os.Bundle;

import com.foodona.foodona.Restarant.Bean.OrderBean;
import com.foodona.foodona.Restarant.Response.OrderDetailsResponse;
import com.foodona.foodona.Restarant.adapterRestaurant.AdapterOrderSummary;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.foodona.foodona.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurentOrderSummary extends AppCompatActivity {
String Order_id;
    Handler handler = new Handler();
String Strtv_restName;
    String Strtv_rest_addres;
    String Strtv_ordernumber;
    String Strtv_orderpayment;
    String Strtv_date;

AdapterOrderSummary adapterOrderSummary;
    List<OrderBean> orderList;
    RecyclerView.LayoutManager layoutManager;


    TextView tv_restName;
    TextView tv_rest_addres;
    TextView tv_rest_waittiong;
    RecyclerView tv_recycle_restaurent;
    TextView tv_itemtotal;
    TextView tv_ordernumber;
    TextView tv_orderpayment;
    TextView tv_date;
    TextView tv_userphone;
    TextView tv_deliveryto;
    TextView tv_callburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_order_summary);
        orderList = new ArrayList<>();
        Order_id=getIntent().getStringExtra("order_id");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Order_id.equals("null")){
                    Log.e("11111", "11111");
                    getnOrdersApi();
                }
                handler.postDelayed(this, 5000);
            }
        }, 5000);
        find();
        getnOrdersApi();

      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Order_id.equals("null")){
                    Log.e("11111", "11111");
                   // getnOrdersApi();
                }
                handler.postDelayed(this, 5000);
            }
        }, 5000);*/

    }
    public void find(){
        tv_restName=findViewById(R.id.tv_restName);
        tv_rest_addres=findViewById(R.id.tv_rest_addres);
        tv_rest_waittiong=findViewById(R.id.tv_rest_waittiong);
        tv_recycle_restaurent=findViewById(R.id.tv_recycle_restaurent);

        tv_recycle_restaurent.setHasFixedSize(true);
        //  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        layoutManager = new LinearLayoutManager(RestaurentOrderSummary.this, LinearLayoutManager.VERTICAL, false);


        tv_recycle_restaurent.setLayoutManager(layoutManager);
        tv_recycle_restaurent.addItemDecoration(new DividerItemDecoration(RestaurentOrderSummary.this, DividerItemDecoration.VERTICAL));
        tv_recycle_restaurent.setAdapter(adapterOrderSummary);
        tv_itemtotal=findViewById(R.id.tv_itemtotal);
        tv_ordernumber=findViewById(R.id.tv_ordernumber);
        tv_orderpayment=findViewById(R.id.tv_orderpayment);
        tv_date=findViewById(R.id.tv_date);
        tv_userphone=findViewById(R.id.tv_userphone);
        tv_deliveryto=findViewById(R.id.tv_deliveryto);
        tv_callburger=findViewById(R.id.tv_callburger);




    }


    /*@Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getExtras() != null) {
            Log.e("intent", "yes " + getIntent().getExtras());
            Log.e("datas", "" + getIntent().getExtras().getString("NOTIFICATION_DATA"));

            if (getIntent().getExtras().getString("NOTIFICATION_DATA") != null) {
                Order_id = getIntent().getExtras().getString("NOTIFICATION_DATA");
                Log.e("Order_idDos", Order_id);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!Order_id.equals("null")){
                            Log.e("11111", "11111");
                            getnOrdersApi();
                        }
                        handler.postDelayed(this, 5000);
                    }
                }, 5000);

               *//* Bundle bundle = new Bundle();
                bundle.putString("Order_ID", Order_id);
                // set Fragmentclass Arguments
                FragmentReceivedOrder fragobj = new FragmentReceivedOrder();
                fragobj.setArguments(bundle);
                //getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragobj).commit();

                //setting fragment on home
                getFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragobj).addToBackStack(null).commit();
                //getnOrdersApi();
                if (!Order_id.equals("null")){
                    Log.e("11111", "11111");
                    getnOrdersApi();
                }*//*


            }

        }
    }*/



    private void getnOrdersApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"order_id"}, new
                String[]{Order_id});
        Log.e("orderconfirmidd", "11111");

        Call<OrderDetailsResponse> call = apiInterface.OrderDetails(builder.build());
        call.enqueue(new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                Log.e("ordercon", "11111");

                if (response.isSuccessful()&& response.body().getSuccess().equals("1") ){
                    if (response.body().getOrder_details().getOrder_status().equals("1")){
                        tv_rest_waittiong.setText("Restaurent Accepted Your order");
                    }
                    Strtv_restName=response.body().getOrder_details().getRestaurant_name();
                    Log.e("restsname",response.body().getOrder_details().getRestaurant_name());
                    Strtv_rest_addres=response.body().getOrder_details().getRestaurant_address();
                    Strtv_ordernumber=response.body().getOrder_details().getOrder_id();
                    Strtv_orderpayment=response.body().getOrder_details().getPayment();
                    Strtv_date=response.body().getOrder_details().getDelivered_date_time();
                    tv_restName.setText(Strtv_restName);
                    tv_rest_addres.setText(Strtv_rest_addres);
                    tv_ordernumber.setText(Strtv_ordernumber);
                    tv_date.setText(Strtv_date);
                    tv_orderpayment.setText(Strtv_orderpayment);
                    orderList = response.body().getOrder();
                    for (int i=0;i<response.body().getOrder().size();i++){
                        response.body().getOrder().get(i).getMenu_name();
                        response.body().getOrder().get(i).getItemAmt();
                        response.body().getOrder().get(i).getItemQty();
                        //orderList.add()
                    }
                    Log.e("orderressized", "" + orderList.size());
                    //Log.e("orderressizeresponsed", "" + response.body());
                    adapterOrderSummary=new AdapterOrderSummary(RestaurentOrderSummary.this,orderList);
                    tv_recycle_restaurent.setAdapter(adapterOrderSummary);
                    /*StrLat=response.body().getOrder_details().getRestaurant_lat();
                    Log.e("restaurentlat", "" + response.body().getOrder_details().getRestaurant_lat());
                    Str_OrderStatus=response.body().getOrder_details().getOrder_status();
                    Strlng=response.body().getOrder_details().getRestaurant_lng();
                    showPickUpOrderDialog();*/
                   /* orderList = response.body().getOrder();
                    for (int i=0;i<response.body().getOrder().size();i++){
                        response.body().getOrder().get(i).getMenu_name() ;
                        response.body().getOrder().get(i).getItemQty();

                    }*/



                    /*Log.e("orderressize", "" + orderList.size());
                    Log.e("orderressizeresponse", "" + response.body());
                    adapterOrder = new AdapterShowAcceptOrder(MainActivity.this, orderList);
                    recyclerView.setAdapter(adapterOrder);
*/

                } else {
                    Log.e("orderconfff", "" + "else");
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {
                onApiFailure(call, t);
                Log.e("orderconfirmfailure", "959595");


            }
        });
    }

    public void onApiFailure(Call<OrderDetailsResponse> call, Throwable t) {

        if ((t instanceof ApiClient.NoConnectivityException))
            Toast.makeText(RestaurentOrderSummary.this, "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(RestaurentOrderSummary.this, "Please try later", Toast.LENGTH_SHORT).show();
    }
}
