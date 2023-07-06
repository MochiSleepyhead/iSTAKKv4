package com.capstone.istakk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> _id, product_name, product_quantity, product_price;
    private ItemClickListener itemClickListener;

    CustomAdapter(Context context, ArrayList<String> _id, ArrayList<String> product_name,
                  ArrayList<String> product_quantity, ArrayList<String> product_price) {
        this.context = context;
        this._id = _id;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_txt.setText(String.valueOf(_id.get(position)));
        holder.name_txt.setText(String.valueOf(product_name.get(position)));
        holder.quantity_txt.setText(String.valueOf(product_quantity.get(position)));
        holder.price_txt.setText(String.valueOf(product_price.get(position)));
    }

    @Override
    public int getItemCount() {
        return _id.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id_txt, name_txt, quantity_txt, price_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            quantity_txt = itemView.findViewById(R.id.quantity_txt);
            price_txt = itemView.findViewById(R.id.price_txt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
