package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.modal.Modal_restaurantlist;

import java.util.ArrayList;

public class AdapterSingle extends RecyclerView.Adapter<AdapterSingle.ViewHolder> {
    public Context context;
    ArrayList<Modal_restaurantlist> restaurantlists;

    // private ArrayList<Modal_restaurantlist> list;


    public AdapterSingle(Context context, ArrayList<Modal_restaurantlist> restaurantlists) {
        this.context = context;
        this.restaurantlists = restaurantlists;


        Log.e("InsideTheAdaptersingle", "InsideTheAdapter"+restaurantlists.size());
    }

    @Override
    public int getItemCount() {
        return restaurantlists.size();
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public AdapterSingle.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("InsideTheAdaptersingle1", "InsideTheAdapter");
        final Modal_restaurantlist modal = restaurantlists.get(position);
        /*final String str_id= restaurantlists.get(position).getId();
        final String str_rest_name= restaurantlists.get(position).getRest_name();
        final String str_dish_name= restaurantlists.get(position).getDishname();
        final String str_price_name= restaurantlists.get(position).getCurrency();
        final String str_rating= restaurantlists.get(position).getRating();
        holder.rest_name.setText(str_rest_name);
        holder.dish_name.setText(str_dish_name);
        holder.pricewtext.setText(str_price_name);
        holder.startext.setText(str_rating);*/

        final String str_dishname= modal.getCategory_name();
      //  final String str_dish_price= modal.getPrice();
       // final String str_dish_desc= modal.get();


      //  int imgurl = modal.getRest_image();
     //   holder.dish_price.setText(str_dish_price);
        holder.dishnmame.setText(str_dishname);


        if (modal.getmLoistSubCat().size() >0) {

            RestaurentSubCategoryAdapter adapterSingle = new RestaurentSubCategoryAdapter(context, modal.getmLoistSubCat());
            holder.mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

            holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            holder.mRecyclerView.setAdapter(adapterSingle);

        }

        //  holder.dish_desc.setText(str_dish_desc);
        //Picasso.with(context).load(imgurl).resize(240, 120).into(holder.restimage);
     //   Picasso.with(context).load("http://foodona.in/FoodDeliverySystem/uploads/restaurant/resto_1567663422.jpg").placeholder(R.drawable.banneone).error(R.drawable.bannertwo).into(holder.dish_image);

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleRestaurent.class);
                intent.putExtra("cat_id",str_id);
                intent.putExtra("rest_name",str_rest_name);
                intent.putExtra("dish_name",str_dish_name);
                intent.putExtra("delivery_name",str_del_time_name);
                intent.putExtra("reting",str_rating);

//                Intent intent = new Intent(context, SingleRestaurent.class);
                context.startActivity(intent);
            }
        });*/
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;



        TextView dish_desc;
        TextView dishnmame;
 /*       TextView dish_price;
        ImageView dish_image;*/

        public ViewHolder(final View itemView) {
            super(itemView);
            /*context = itemView.getContext();*/
/*
            dish_price = (TextView) itemView.findViewById(R.id.dish_price);
*/
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.mRecyclerView);

            dishnmame = (TextView) itemView.findViewById(R.id.dishnmame);
   /*         dish_desc = (TextView) itemView.findViewById(R.id.dish_desc);
            dish_image = (ImageView) itemView.findViewById(R.id.dish_image);*/
        }
    }
}
