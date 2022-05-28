package com.example.food.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.CustomerActivity;
import com.example.food.DetailFoodActivity;
import com.example.food.R;
import com.example.food.adapter.CategoryAdapter;
import com.example.food.adapter.FoodAdapter;
import com.example.food.database.SQLiteHelper;
import com.example.food.model.Category;
import com.example.food.model.Food;
import com.example.food.model.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchCustomer extends Fragment implements CategoryAdapter.ItemListener,FoodAdapter.ItemListener{
    private SearchView searchView;
    private RecyclerView recyclerViewCategory, recyclerView;
    private CategoryAdapter categoryAdapter;
    private FoodAdapter adapter;
    private SQLiteHelper db;
    private List<Category> listCategory;
    private List<Food> listFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_customer,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchView);
        recyclerViewCategory = view.findViewById(R.id.recycleViewCategory);
        recyclerView = view.findViewById(R.id.recycleView);
        db = new SQLiteHelper(getContext());
        categoryAdapter = new CategoryAdapter();
        listCategory = db.getAllCategory();
        categoryAdapter.setList(listCategory);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewCategory.setLayoutManager(manager1);
        recyclerViewCategory.setAdapter(categoryAdapter);
        categoryAdapter.setItemListener(this);

        adapter = new FoodAdapter();
        listFood = db.getAllFood();
        adapter.setList(listFood);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Food> list1 = db.searchByName(newText);
                adapter.setList(list1);
                return true;
            }
        });
    }

    @Override
    public void onItemCategoryClick(View view, int position) {
        List<Food> list2 = db.searchByCategory(listCategory.get(position).getId());
        adapter.setList(list2);
    }

    @Override
    public void onItemFoodClick(View view, int position) {
        Intent intent = new Intent(getActivity(), DetailFoodActivity.class);
        CustomerActivity customerActivity = (CustomerActivity) getActivity();
        intent.putExtra("myaccount",customerActivity.getMyaccount());
        intent.putExtra("food",listFood.get(position));
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        listFood = db.getAllFood();
        adapter.setList(listFood);
    }
}
