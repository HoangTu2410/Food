package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.database.SQLiteHelper;
import com.example.food.model.Cart;
import com.example.food.model.Food;
import com.example.food.model.User;

public class DetailFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgFood,imgRemove,imgAdd,imgBack;
    private TextView tvName,tvPrice,tvNumber,tvDescription;
    private Button btAddToCart;
    private Food food;
    private User myAccount;
    private int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        Intent intent = getIntent();
        myAccount = (User) intent.getSerializableExtra("myaccount");
        food = (Food) intent.getSerializableExtra("food");
        initView();
        imgRemove.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        btAddToCart.setOnClickListener(this);
        imgFood.setImageResource(food.getImg());
        tvName.setText(food.getName());
        tvPrice.setText("Price: $"+food.getPrice());
        tvNumber.setText("0"); amount = 0;
        tvDescription.setText(food.getDescription());
    }

    private void initView() {
        imgFood = findViewById(R.id.imgFood);
        imgRemove = findViewById(R.id.imgRemove);
        imgAdd = findViewById(R.id.imgAdd);
        imgBack = findViewById(R.id.imgBack);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvNumber = findViewById(R.id.tvNumber);
        tvDescription = findViewById(R.id.tvDescription);
        btAddToCart = findViewById(R.id.btAddToCart);
    }

    @Override
    public void onClick(View view) {
        if(view==imgBack){
            finish();
        }
        if(view==imgRemove){
            if(amount>0){
                amount--;
                tvNumber.setText(amount+"");
            }
        }
        if(view==imgAdd){
            amount++;
            tvNumber.setText(amount+"");
        }
        if(view==btAddToCart){
            if(amount==0){
                Toast.makeText(this, "Số lượng phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                return;
            }
            Cart cart = new Cart(amount,myAccount,food);
            SQLiteHelper db = new SQLiteHelper(this);
            db.addCart(cart);
            finish();
        }
    }
}