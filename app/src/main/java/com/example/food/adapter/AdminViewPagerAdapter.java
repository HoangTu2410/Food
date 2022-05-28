package com.example.food.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.food.fragment.FragmentHomeAdmin;
import com.example.food.fragment.FragmentSettingAdmin;
import com.example.food.fragment.FragmentStatisticAdmin;

public class AdminViewPagerAdapter extends FragmentStatePagerAdapter {
    public AdminViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHomeAdmin();
            case 1: return new FragmentStatisticAdmin();
            case 2: return new FragmentSettingAdmin();
            default: return new FragmentHomeAdmin();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
