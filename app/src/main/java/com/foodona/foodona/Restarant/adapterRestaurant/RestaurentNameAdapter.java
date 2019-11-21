package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.modal.Modal_restaurantlist;
import com.foodona.foodona.Restarant.restaurant.RestaurentCart;
import com.foodona.foodona.Restarant.restaurant.SingleRestaurent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class RestaurentNameAdapter extends RecyclerView.Adapter<RestaurentNameAdapter.ViewHolder> {
    public Context context;
     ArrayList<Modal_restaurantlist> list;

    public RestaurentNameAdapter(Context context, ArrayList<Modal_restaurantlist> list) {
        this.context = context;
        this.list = list;
        Log.e("Modal_restaurantlist1", "Modal_restaurantlist1");

    }
    @Override
    public RestaurentNameAdapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_rest_adapter, viewGroup, false);
        return new RestaurentNameAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder( RestaurentNameAdapter.ViewHolder holder, int position) {
        Log.e("Modal_restaurantlist", "Modal_restaurantlist");

    final Modal_restaurantlist modal = list.get(position);
        final String str_dishname= modal.getDishname();
        final String str_dish_price= modal.getPrice();
        Log.e("str_dish_name",str_dishname);
    //    final String str_dish_desc= modal.getSub_cat_desc();


          holder.dishnmame.setText(str_dishname);
        RestaurentSubCategoryAdapter mRestaurentSubCategoryAdapter=new RestaurentSubCategoryAdapter(context,list.get(position).getmLoistSubCat());
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
         holder.mRecyclerView.setLayoutManager(gridLayoutManager);
         holder.mRecyclerView.setAdapter(mRestaurentSubCategoryAdapter);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RestaurentCart.class);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         TextView dishnmame;

        RecyclerView mRecyclerView;

        public ViewHolder(final View itemView) {
            super(itemView);



                    mRecyclerView = (RecyclerView) itemView.findViewById(R.id.mRecyclerView);

            dishnmame = (TextView) itemView.findViewById(R.id.dishnmame);

        }
    }
}
