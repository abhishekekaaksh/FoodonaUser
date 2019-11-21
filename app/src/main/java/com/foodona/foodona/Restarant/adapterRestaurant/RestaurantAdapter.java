package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.modal.Modal_RestaurentName;
import com.foodona.foodona.Restarant.modal.model_Get_Restaurent_price;
import com.foodona.foodona.Restarant.restaurant.RestaurentActivity;
import com.foodona.foodona.Restarant.restaurant.SingleRestaurent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    public Context context;
    ArrayList<Modal_RestaurentName> restaurantlists;
    FragmentManager fragmentManager;
     String str_rating;
    // private ArrayList<Modal_restaurantlist> list;


    public RestaurantAdapter(Context context, ArrayList<Modal_RestaurentName> restaurantlists, FragmentManager fragmentManager) {
        this.context = context;
        this.restaurantlists = restaurantlists;
        this.fragmentManager=fragmentManager;


        Log.e("InsideTheAdapter", "InsideTheAdapter");
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
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_restaurant, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("InsideTheAdapter1", "InsideTheAdapter");
        final Modal_RestaurentName modal = restaurantlists.get(position);

       final   String str_id= modal.getId();
        final String str_rest_name= modal.getName();
        final String str_dish_name= modal.getCategory();
        final String str_del_time_name= modal.getDelivery_time();
        str_rating= modal.getRating();
        /*if (str_rating.equals("3")){
            *//*holder.startext.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ratinglight_green, 0, 0, 0);*//*
        }if (str_rating.equals("4")){
            holder.startext.setText(str_rating);
        }if (str_rating.equals("0")){

       holder.startext.setText("New");
        }*/
       // final String str_rest_name= modal.getName();


        String imgurl = modal.getImage();
        holder.rest_name.setText(str_rest_name);
       holder.dish_name.setText(str_dish_name);
        holder.pricewtext.setText(str_del_time_name);
        holder.startext.setText(str_rating);

        holder.timetext.setText(str_del_time_name + " mins");
        //Picasso.with(context).load(imgurl).resize(240, 120).into(holder.restimage);
        Picasso.with(context).load(imgurl).placeholder(R.drawable.banneone).error(R.drawable.bannertwo).into(holder.restimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle args = new Bundle();
                args.putString("cat_id",str_id);
                args.putString("rest_name",str_rest_name);
                args.putString("dish_name",str_dish_name);
                args.putString("delivery_name",str_del_time_name);
                args.putString("reting",str_rating);
                SingleRestaurent singleRestaurent=new SingleRestaurent();

                singleRestaurent.setArguments(args);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, singleRestaurent);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                /*Intent intent = new Intent(context, SingleRestaurent.class);
                intent.putExtra("cat_id",str_id);
                intent.putExtra("rest_name",str_rest_name);
                intent.putExtra("dish_name",str_dish_name);
                intent.putExtra("delivery_name",str_del_time_name);
                intent.putExtra("reting",str_rating);

//                Intent intent = new Intent(context, SingleRestaurent.class);
                context.startActivity(intent);*/
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rest_name;
        TextView dish_name;
        TextView pricewtext;
        ImageView restimage;
        TextView timetext;
        TextView startext;
        public ViewHolder(final View itemView) {
            super(itemView);
            /*context = itemView.getContext();*/
            pricewtext = itemView.findViewById(R.id.pricewtext);
            timetext = itemView.findViewById(R.id.timetext);
            startext = itemView.findViewById(R.id.startext);
            dish_name = itemView.findViewById(R.id.dish_name);
            rest_name = itemView.findViewById(R.id.rest_name);
            restimage = itemView.findViewById(R.id.rest_pic);
        }
    }
}
