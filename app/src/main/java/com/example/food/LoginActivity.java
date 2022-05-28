package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.database.SQLiteHelper;
import com.example.food.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText eUsername,ePassword;
    private Button btLogin;
    private TextView tvSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteHelper db = new SQLiteHelper(LoginActivity.this);
                Object login = db.checkLogin(eUsername.getText().toString().trim(),ePassword.getText().toString().trim());
                if(login==null) {
                    Toast.makeText(LoginActivity.this, "Incorrect account or password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = (User) login;
                if(user.getPosition()==User.CUSTOMER){
                    Intent intent = new Intent(LoginActivity.this,CustomerActivity.class);
                    intent.putExtra("myaccount",user);
                    startActivity(intent);
                }
                else if(user.getPosition()==User.ADMIN){
                    Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                    intent.putExtra("myaccount",user);
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {
        eUsername = findViewById(R.id.eUsername);
        ePassword = findViewById(R.id.ePassword);
        btLogin = findViewById(R.id.btLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
    }
}