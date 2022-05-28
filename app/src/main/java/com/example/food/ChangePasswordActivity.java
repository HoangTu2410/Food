package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.food.database.SQLiteHelper;
import com.example.food.model.User;

public class ChangePasswordActivity extends AppCompatActivity {
    private ImageView imgBack;
    private EditText eOldPassword,eNewPassword,eReNewPassword;
    private Button btChangePassword;
    private SQLiteHelper db;
    private User myAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        intView();
        Intent intent = getIntent();
        myAccount = (User) intent.getSerializableExtra("myaccount");
        db = new SQLiteHelper(this);
        Intent reIntent = new Intent();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reIntent.putExtra("myaccount",myAccount);
                setResult(RESULT_OK,reIntent);
                finish();
            }
        });
        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!eOldPassword.getText().toString().equals(myAccount.getPassword())){
                    Toast.makeText(ChangePasswordActivity.this, "Password incorrect!", Toast.LENGTH_SHORT).show();
                }
                else if(eNewPassword.getText().toString().equals("")||
                        !eNewPassword.getText().toString().equals(eReNewPassword.getText().toString())){
                    Toast.makeText(ChangePasswordActivity.this, "Enter password again!", Toast.LENGTH_SHORT).show();
                } else {
                    myAccount.setPassword(eNewPassword.getText().toString());
                    db.updateUser(myAccount);
                    Toast.makeText(ChangePasswordActivity.this, "Change password successful!", Toast.LENGTH_SHORT).show();
                    eNewPassword.setText("");
                    eReNewPassword.setText("");
                }
            }
        });
    }

    private void intView() {
        imgBack = findViewById(R.id.imgBack);
        eOldPassword = findViewById(R.id.eOldPassword);
        eNewPassword = findViewById(R.id.eNewPassword);
        eReNewPassword = findViewById(R.id.eReNewPassword);
        btChangePassword = findViewById(R.id.btChangePassword);
    }
}