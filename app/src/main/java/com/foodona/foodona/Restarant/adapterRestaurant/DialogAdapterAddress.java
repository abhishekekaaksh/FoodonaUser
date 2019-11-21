package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Bean.AddressBean;
import com.foodona.foodona.Restarant.Map.EditAddress;
import com.foodona.foodona.Restarant.Map.mapsm;
import com.foodona.foodona.Restarant.Response.AddressResponse;
import com.foodona.foodona.Restarant.Response.UserDeleteResponse;
import com.foodona.foodona.Restarant.rest.ApiClient;
import com.foodona.foodona.Restarant.rest.ApiInterface;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAdapterAddress extends RecyclerView.Adapter<DialogAdapterAddress.ViewHolder> {
    Context context;
    List<AddressBean> addressBeanList;
    String StrAdddressID;
    String AddressStr;
    DialogAdapterAddress dialogAdapterAddress;

    public DialogAdapterAddress(Context context, List<AddressBean> addressBeanList) {
        this.context = context;
        this.addressBeanList = addressBeanList;
    }


    @NonNull
    @Override
    public DialogAdapterAddress.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialogadapter_address, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogAdapterAddress.ViewHolder holder, final int position) {
        AddressBean modal_order = addressBeanList.get(position);
        AddressStr = modal_order.getAddress();
        Toast.makeText(context, "You clicked " + AddressStr, Toast.LENGTH_SHORT).show();
        Log.e("ghghh", addressBeanList.size() + "");
        StrAdddressID = modal_order.getId();
        //holder.tv_getaddres.setText(modal_order.getAddress().get(position).getAddress());
        holder.tv_getaddres.setText(AddressStr);
        //holder.tv_quantity.setText(modal_order.getItemQty());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDeleteAddress(position);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditAddress.class);
                intent.putExtra("AddressStr", AddressStr);
                intent.putExtra("StrAdddressID", StrAdddressID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("size", "");
        return addressBeanList.size();
    }

    public void UserDeleteAddress(final int position) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
      /*  FormBody.Builder builder = ApiClient.createBuilder(new String[]{"user_id", "res_id", "sub_id", "price", "quantity"},
                new String[]{user_id, restid, sub_caty_id, str_dish1_price, clickedItem});*/

        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"address_id"},
                new String[]{StrAdddressID});
        Call<UserDeleteResponse> call = apiInterface.UserDeleteAddress(builder.build());
        call.enqueue(new Callback<UserDeleteResponse>() {
            @Override
            public void onResponse(Call<UserDeleteResponse> call, Response<UserDeleteResponse> response) {
                //Log.d("Addcart", response.body().toString());
                if (response.isSuccessful() && response.body().getStatus().equals("Success")) {
                    Log.d("Deleteaddress", response.body().toString());

                    addressBeanList.remove(position);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<UserDeleteResponse> call, Throwable t) {
                //onApiFailure(call, t);
                // Toast.makeText(SingleRestaurent.this, "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_getaddres;
        TextView tv_quantity;
        ImageView edit;
        ImageView delete;
        //ImageView restimage;

        public ViewHolder(final View itemView) {
            super(itemView);

            tv_getaddres = itemView.findViewById(R.id.tv_getaddres);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}