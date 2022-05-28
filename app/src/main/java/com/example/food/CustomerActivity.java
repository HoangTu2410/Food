package com.example.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.food.adapter.CustomerViewPagerAdapter;
import com.example.food.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomerActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton button;
    private User myaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);
        button = findViewById(R.id.fab);
        Intent intent = getIntent();
        myaccount = (User) intent.getSerializableExtra("myaccount");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CustomerActivity.this,CartActivity.class);
                intent1.putExtra("myaccount",myaccount);
                startActivity(intent1);
            }
        });
        CustomerViewPagerAdapter adapter = new CustomerViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
                    case 1: navigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.mNotification).setChecked(true);
                        break;
                    case 3: navigationView.getMenu().findItem(R.id.mSetting).setChecked(true);
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
                    case R.id.mSearch: viewPager.setCurrentItem(1);
                        break;
                    case R.id.mNotification: viewPager.setCurrentItem(2);
                        break;
                    case R.id.mSetting: viewPager.setCurrentItem(3);
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
}