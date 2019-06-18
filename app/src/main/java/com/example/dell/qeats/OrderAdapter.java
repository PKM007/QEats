package com.example.dell.qeats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    private Context mCtx;
    private List<Order> orderList;

    public OrderAdapter(Context mCtx, List<Order> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_layout,null);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        Order order=orderList.get(i);

        orderViewHolder.restaurantName.setText(order.getRestaurantName());
        orderViewHolder.location.setText(order.getLocation());
        orderViewHolder.items.setText(order.getItems());
        orderViewHolder.date1.setText(order.getDate());
        orderViewHolder.total.setText(order.getTotal());
        orderViewHolder.status.setText(order.getStatus());
        Picasso.with(mCtx)
                .load(order.getImageURL())
                .resize(130,100).into(orderViewHolder.imageURL);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{

        ImageView imageURL;

        TextView restaurantName,location,items,date1,total,status;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageURL=itemView.findViewById(R.id.restaurantImage);
            restaurantName=itemView.findViewById(R.id.restaurantName);
            location=itemView.findViewById(R.id.location);
            items=itemView.findViewById(R.id.items);
            date1=itemView.findViewById(R.id.date1);
            total=itemView.findViewById(R.id.total);
            status=itemView.findViewById(R.id.status);
        }
    }
}
