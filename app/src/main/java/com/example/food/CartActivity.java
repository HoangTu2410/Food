package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.adapter.CartAdapter;
import com.example.food.database.SQLiteHelper;
import com.example.food.model.Cart;
import com.example.food.model.Food;
import com.example.food.model.User;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView imgBack;
    private List<Cart> list;
    private User myAccount;
    private SQLiteHelper db;
    private CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.recycleView);
        imgBack = findViewById(R.id.imgBack);
        db = new SQLiteHelper(this);
        Intent intent = getIntent();
        myAccount = (User) intent.getSerializableExtra("myaccount");
        list = db.getCartByCustomer(myAccount.getId());
        adapter = new CartAdapter(this);
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Cart> list = db.getCartByCustomer(myAccount.getId());
        adapter.setList(list);
    }
}