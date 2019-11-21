package com.foodona.foodona.Restarant.restaurant;

import android.content.Intent;
import android.os.Bundle;

import com.foodona.foodona.Restarant.Response.OrderDetailsResponse;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.foodona.foodona.R;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrederconfirmationActivity extends AppCompatActivity implements View.OnClickListener {
TextView tv_order_summary,tv_confirmation;
String Order_id;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orederconfirmation);
        find();
        //getnOrdersApi();
    }
    public void find(){
        tv_order_summary=findViewById(R.id.tv_order_summary);
        tv_confirmation=findViewById(R.id.tv_confirmation);

        tv_order_summary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==tv_order_summary){
            Toast.makeText(OrederconfirmationActivity.this, "on touch", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(OrederconfirmationActivity.this,RestaurentOrderSummary.class);
            intent.putExtra("order_id",Order_id);
            startActivity(intent);

           // startActivity(new Intent(OrederconfirmationActivity.this,RestaurentOrderSummary.class));
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getExtras() != null) {
            Log.e("intentcs", "yes " + getIntent().getExtras());
            Log.e("datascs", "" + getIntent().getExtras().getString("NOTIFICATION_DATA"));

            if (getIntent().getExtras().getString("NOTIFICATION_DATA") != null) {
                Order_id = getIntent().getExtras().getString("NOTIFICATION_DATA");
                Log.e("Order_idDcs", Order_id);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!Order_id.equals("null")){
                            Log.e("11111cs", "11111");
                            getnOrdersApi();
                        }
                        handler.postDelayed(this, 5000);
                    }
                }, 5000);

           /*     Bundle bundle = new Bundle();
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
                }*/


            }

        }
    }




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
                        tv_confirmation.setText("Restaurent Accepted Your order");
                    }
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
            Toast.makeText(OrederconfirmationActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(OrederconfirmationActivity.this, "Please try later", Toast.LENGTH_SHORT).show();
    }




}
