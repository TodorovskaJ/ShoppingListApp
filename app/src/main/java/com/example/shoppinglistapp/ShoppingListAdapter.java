package com.example.shoppinglistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>{

        ArrayList<ShoppingList> shoppingLists;
        ShoppingListInterface listener;


public ShoppingListAdapter (ArrayList<ShoppingList> shoppingLists, ShoppingListInterface listener) {
        this.shoppingLists = shoppingLists;
        this.listener = listener;
        }

    @NonNull
    @Override
    public ShoppingListAdapter.ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list,parent,false);

        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ShoppingListViewHolder holder, int position) {
        ShoppingList shoppingList = shoppingLists.get(position);
        holder.tvName.setText(shoppingList.getName());
        holder.tvSize.setText(shoppingList.getSize());
        holder.tvPrice.setText(shoppingList.getPrice() + " denari");

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onShoppingListLongPress(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }
    class ShoppingListViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvSize, tvPrice;
        ConstraintLayout container;
        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tVName);
            tvSize = itemView.findViewById(R.id.tVSize);
            tvPrice = itemView.findViewById(R.id.tVPrice);
            container = itemView.findViewById(R.id.layoutContainer);

        }
    }
}
