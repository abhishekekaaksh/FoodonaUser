package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Bean.OrderBean;
import com.foodona.foodona.Restarant.Response.OrderDetailsResponse;

import java.util.List;

public class AdapterOrderSummary extends RecyclerView.Adapter<AdapterOrderSummary.ViewHolder> {
    Context context;
    List<OrderBean> list;
    String Stramount;
    String Stringqty;
    String Strmuliply ;
    public AdapterOrderSummary(Context context, List<OrderBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AdapterOrderSummary.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adatper_ordersummary, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrderSummary.ViewHolder holder, int position) {
        OrderBean modal_order = list.get(position);
        Log.e("ghghh11", list.size() + "");
        holder.tv_menu_name.setText(modal_order.getMenu_name());
        holder.tv_qunatity.setText(modal_order.getItemQty());
        holder.tv_price.setText(" x "+ "\u20B9" +modal_order.getItemAmt());


    }

    @Override
    public int getItemCount() {
        Log.e("size", "");
        return list.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu_name;
        TextView tv_qunatity;
        TextView tv_price;



        //ImageView restimage;

        public ViewHolder(final View itemView) {
            super(itemView);

            tv_menu_name = itemView.findViewById(R.id.tv_menu_name);
            tv_qunatity = itemView.findViewById(R.id.tv_qunatity);
            tv_price = itemView.findViewById(R.id.tv_price);



        }
    }
}