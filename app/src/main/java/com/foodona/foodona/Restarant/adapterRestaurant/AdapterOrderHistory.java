package com.foodona.foodona.Restarant.adapterRestaurant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodona.foodona.R;
import com.foodona.foodona.Restarant.Bean.OrderBean;
import com.foodona.foodona.Restarant.Bean.UserOrderHistoryBean;

import java.util.List;

public class AdapterOrderHistory extends RecyclerView.Adapter<AdapterOrderHistory.ViewHolder> {
    Context context;
    String StrStatus;
    //ArrayList<OrderResponse> list;
    List<UserOrderHistoryBean> list;


    public AdapterOrderHistory(Context context, List<UserOrderHistoryBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AdapterOrderHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_order_history, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterOrderHistory.ViewHolder holder, int position) {
        //OrderResponse modal_order = list.get(position);
        UserOrderHistoryBean modal_order = list.get(position);
        StrStatus = modal_order.getStatus();
        Log.e("ghghh", list.size() + "");
        holder.tv_restname.setText(modal_order.getResta_name());
        holder.tv_restlocation.setText(modal_order.getAddress());
        if (StrStatus.equals("0")) {
            holder.confirm.setText("Order Pending");
            holder.confirm.setTextColor(Color.parseColor("#EE2C24"));
        }
        if (StrStatus.equals("4")) {
            holder.confirm.setText("Order Confirm");
            holder.confirm.setTextColor(Color.parseColor("#3CCA57"));

        }
        //holder.confirm.setText(modal_order.getStatus());
        // holder.dishtime.setText(modal_order.getMenu_name()+"("+modal_order.getItemQty()+")");
        //  holder.amount.setText(""+modal_order.getItemAmt());
        //      Log.e("itemmenuname", modal_order.getMenu_name());
//        Log.e("itemamt", modal_order.getItemAmt());

        //holder.order.setText("Order Number :" + modal_order.getOrder_id());
      /*  holder.tdrivewr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, apsm.class);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        Log.e("size", "");
        return list.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_restname;
        TextView tv_restlocation;
        TextView confirm;
        TextView tv_deliverytime;
        ImageView rest_image;

        public ViewHolder(final View itemView) {
            super(itemView);

            tv_restname = itemView.findViewById(R.id.tv_restname);
            tv_restlocation = itemView.findViewById(R.id.tv_restlocation);
            confirm = itemView.findViewById(R.id.confirm);
            tv_deliverytime = itemView.findViewById(R.id.tv_deliverytime);
            rest_image = itemView.findViewById(R.id.rest_image);


        }
    }
}