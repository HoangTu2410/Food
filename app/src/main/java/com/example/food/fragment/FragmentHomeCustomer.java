package com.example.food.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;
import com.example.food.adapter.FoodAdapter;
import com.example.food.database.SQLiteHelper;
import com.example.food.model.Cart;
import com.example.food.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FragmentHomeCustomer extends Fragment {
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private SQLiteHelper db;
    private List<Food> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_customer,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleViewPopular);
        db = new SQLiteHelper(getContext());
        list = new ArrayList<>();
        adapter = new FoodAdapter();
        List<Cart> listCart = db.getAllCart();
        for(Cart i: listCart){
            boolean exist = false;
            for(Food j: list){
                if(j.getId()==i.getFood().getId()){
                    exist = true;
                    break;
                }
            }
            if(!exist){
                list.add(i.getFood());
            }
        }
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
