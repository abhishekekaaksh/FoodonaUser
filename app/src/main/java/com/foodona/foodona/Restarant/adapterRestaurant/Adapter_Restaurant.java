package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.modal.Modal_RestaurentName;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class Adapter_Restaurant extends RecyclerView.Adapter<Adapter_Restaurant.ViewHolder> {
 private ArrayList<Modal_RestaurentName>modal_restaurentNames;
 Context context;
    public Adapter_Restaurant(ArrayList<Modal_RestaurentName> modal_restaurentNames, Context context) {
        this.modal_restaurentNames = modal_restaurentNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_restaurant,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Modal_RestaurentName modal = modal_restaurentNames.get(position);
        int imgurl = modal.getRest_image();
        holder.rest_name.setText(modal.getName());
        holder.dish_name.setText(modal.getCategory());
        holder.pricewtext.setText(modal.getCurrency());
        Picasso.with(context).load(imgurl).resize(240, 120).into(holder.restimage);

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rest_name;
        TextView dish_name;
        TextView pricewtext;
        ImageView restimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pricewtext = (TextView) itemView.findViewById(R.id.pricewtext);

            dish_name = (TextView) itemView.findViewById(R.id.dish_name);
            rest_name = (TextView) itemView.findViewById(R.id.rest_name);
            restimage = (ImageView) itemView.findViewById(R.id.rest_pic);
        }
    }

    @Override
    public int getItemCount() {
        Log.e("restaturentlistsize",""+modal_restaurentNames.size());


        return modal_restaurentNames.size();
    }
}
