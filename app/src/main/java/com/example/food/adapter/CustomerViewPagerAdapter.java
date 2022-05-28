package com.example.food.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.food.fragment.FragmentNotificationCustomer;
import com.example.food.fragment.FragmentSearchCustomer;
import com.example.food.fragment.FragmentHomeCustomer;
import com.example.food.fragment.FragmentSettingCustomer;

public class CustomerViewPagerAdapter extends FragmentStatePagerAdapter {
    public CustomerViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHomeCustomer();
            case 1: return new FragmentSearchCustomer();
            case 2: return new FragmentNotificationCustomer();
            case 3: return new FragmentSettingCustomer();
            default: return new FragmentHomeCustomer();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
