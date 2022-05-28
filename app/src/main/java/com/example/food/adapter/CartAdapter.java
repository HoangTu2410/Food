package com.example.food.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;
import com.example.food.database.SQLiteHelper;
import com.example.food.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> list;
    private Context context;
    private SQLiteHelper db;
    private int amount;

    public CartAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
        db = new SQLiteHelper(context);
    }

    public void setList(List<Cart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cart cart = list.get(position);
        holder.imgFood.setImageResource(cart.getFood().getImg());
        holder.tvName.setText(cart.getFood().getName());
        holder.tvPrice.setText("$"+cart.getFood().getPrice());
        amount = cart.getAmount();
        float priceTotal = amount*cart.getFood().getPrice();
        holder.tvNumber.setText(""+cart.getAmount());
        holder.tvPriceTotal.setText("$"+priceTotal);

        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount++;
                holder.tvNumber.setText(amount+"");
                cart.setAmount(amount);
                db.updateCart(cart);
                list.set(position,cart);
                notifyDataSetChanged();
            }
        });
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount<=1){
                    Toast.makeText(context, "Số lượng phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                    return;
                }
                amount--;
                holder.tvNumber.setText(amount+"");
                cart.setAmount(amount);
                db.updateCart(cart);
                list.set(position,cart);
                notifyDataSetChanged();
            }
        });
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteCart(cart.getId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFood,imgRemove,imgAdd;
        private TextView tvName,tvNumber,tvPrice,tvPriceTotal;
        private Button btRemove;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            imgRemove = itemView.findViewById(R.id.imgRemove);
            imgAdd = itemView.findViewById(R.id.imgAdd);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPriceTotal = itemView.findViewById(R.id.tvPriceTotal);
            btRemove = itemView.findViewById(R.id.btRemove);
        }
    }
}
