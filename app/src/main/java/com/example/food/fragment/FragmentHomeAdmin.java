package com.example.food.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.AddFoodActivity;
import com.example.food.ModifyFoodActivity;
import com.example.food.R;
import com.example.food.adapter.FoodAdapter;
import com.example.food.database.SQLiteHelper;
import com.example.food.model.Food;

import java.util.List;

public class FragmentHomeAdmin extends Fragment implements FoodAdapter.ItemListener {
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private Button btAddFood;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_admin,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        btAddFood = view.findViewById(R.id.btAddFood);
        adapter = new FoodAdapter();
        db = new SQLiteHelper(getContext());
        List<Food> list = db.getAllFood();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        btAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemFoodClick(View view, int position) {
        Intent intent = new Intent(getActivity(), ModifyFoodActivity.class);
        Food food = adapter.getFood(position);
        intent.putExtra("food",food);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Food> list = db.getAllFood();
        adapter.setList(list);
    }
}
