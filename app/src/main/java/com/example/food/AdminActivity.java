package com.example.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.food.adapter.AdminViewPagerAdapter;
import com.example.food.database.SQLiteHelper;
import com.example.food.model.Category;
import com.example.food.model.Food;
import com.example.food.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private User myaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);
        initCategory();
        Intent intent = getIntent();
        myaccount = (User) intent.getSerializableExtra("myaccount");
        AdminViewPagerAdapter adapter = new AdminViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.mStatistic).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.mSetting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mHome: viewPager.setCurrentItem(0);
                        break;
                    case R.id.mStatistic: viewPager.setCurrentItem(1);
                        break;
                    case R.id.mSetting: viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    public User getMyaccount() {
        return myaccount;
    }

    public void setMyaccount(User myaccount) {
        this.myaccount = myaccount;
    }

    private void initCategory(){
        SQLiteHelper db = new SQLiteHelper(this);
        List<Category> list = db.getAllCategory();
        if(list.size()==0){
            db.addCategory(new Category(R.drawable.category_1,"Pizza"));
            db.addCategory(new Category(R.drawable.category_2,"Burger"));
            db.addCategory(new Category(R.drawable.category_3,"Hotdog"));
            db.addCategory(new Category(R.drawable.category_4,"Drink"));
            db.addCategory(new Category(R.drawable.category_5,"Donut"));
        }
    }
}