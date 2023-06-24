package com.example.shoppinglistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ShoppingListActivity extends AppCompatActivity implements ShoppingListInterface{
    TextView tvLogged;
    RecyclerView rvShoppingList;
    ImageButton btnadd;
    String username;
    AppHolder appholder;
    ShoppingListAdapter adapter;
    /*ArrayList <ShoppingList> shoppingLists = new ArrayList<>();*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        if (getIntent().getExtras() != null){
            username = getIntent().getExtras().getString("USERNAME");
        }
        tvLogged = findViewById(R.id.tvLogged);
        tvLogged.setText("Hello " + username);

        rvShoppingList = findViewById(R.id.rvShoppingList);
        btnadd = findViewById(R.id.btnAdd);
        adapter = new ShoppingListAdapter(AppHolder.appholder, this);

        ShoppingList shoppingList1 = new ShoppingList("T-Shirt","S", 150);
        ShoppingList shoppingList2 = new ShoppingList("Jacket","S - XL", 2200);
        ShoppingList shoppingList3 = new ShoppingList("Skirt","Standard", 750);

        AppHolder.appholder.add(shoppingList1);
        AppHolder.appholder.add(shoppingList2);
        AppHolder.appholder.add(shoppingList3);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvShoppingList.setLayoutManager(manager);
        rvShoppingList.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingListActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new ShoppingListAdapter(AppHolder.appholder,this);
        rvShoppingList.setAdapter(adapter);
    }

    @Override
    public void onShoppingListLongPress(int position) {
        AppHolder.appholder.remove(position);
        Toast.makeText(this," Item  is successfully deleted"  , Toast.LENGTH_SHORT).show();

        adapter.notifyDataSetChanged();
    }




}
