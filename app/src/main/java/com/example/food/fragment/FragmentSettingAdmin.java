package com.example.food.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food.AdminActivity;
import com.example.food.ChangePasswordActivity;
import com.example.food.EditInforActivity;
import com.example.food.R;
import com.example.food.database.SQLiteHelper;
import com.example.food.model.User;

public class FragmentSettingAdmin extends Fragment {
    private TextView tvName, tvProfile, tvChangePassword, tvLogout;
    private final static int REQUEST_CODE_CHANGEPASSWORD = 1000;
    private final static int REQUEST_CODE_EDITINFOR = 1001;
    private AdminActivity myAdminActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_admin,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tvName);
        tvProfile = view.findViewById(R.id.tvProfile);
        tvChangePassword = view.findViewById(R.id.tvChangePassword);
        tvLogout = view.findViewById(R.id.tvLogout);
        myAdminActivity = (AdminActivity) getActivity();
        tvName.setText(myAdminActivity.getMyaccount().getName());
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                intent.putExtra("myaccount",myAdminActivity.getMyaccount());
                startActivityForResult(intent,REQUEST_CODE_CHANGEPASSWORD);
            }
        });
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditInforActivity.class);
                intent.putExtra("myaccount",myAdminActivity.getMyaccount());
                startActivityForResult(intent,REQUEST_CODE_EDITINFOR);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_CHANGEPASSWORD && resultCode==RESULT_OK){
            if(data!=null) {
                User myAccount = (User) data.getSerializableExtra("myaccount");
                myAdminActivity.setMyaccount(myAccount);
            }
        }
        if(requestCode==REQUEST_CODE_EDITINFOR && resultCode==RESULT_OK){
            if(data!=null) {
                User myAccount = (User) data.getSerializableExtra("myaccount");
                myAdminActivity.setMyaccount(myAccount);
            }
        }
    }
}
