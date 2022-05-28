package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food.database.SQLiteHelper;
import com.example.food.model.User;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eName,eBirthday,eAddress,eUsername,ePassword,eRePassword;
    private Button btSignUp;
    private TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        tvLogin.setOnClickListener(this);
        eBirthday.setOnClickListener(this);
        btSignUp.setOnClickListener(this);
    }

    private void initView() {
        eName = findViewById(R.id.eName);
        eBirthday = findViewById(R.id.eBirthday);
        eAddress = findViewById(R.id.eAddress);
        eUsername = findViewById(R.id.eUsername);
        ePassword = findViewById(R.id.ePassword);
        eRePassword = findViewById(R.id.eRePassword);
        btSignUp = findViewById(R.id.btSignUp);
        tvLogin = findViewById(R.id.tvLogin);
    }

    @Override
    public void onClick(View view) {
        if(view==tvLogin){
            finish();
        }
        if(view==eBirthday){
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m>8){
                        date = d+"/"+(m+1)+"/"+y;
                    } else {
                        date = d+"/0"+(m+1)+"/"+y;
                    }
                    eBirthday.setText(date);
                }
            },y,m,d);
            dialog.show();
        }
        if(view==btSignUp){
            String name = eName.getText().toString();
            String birthday = eBirthday.getText().toString();
            String address = eAddress.getText().toString();
            String username = eUsername.getText().toString();
            String password = ePassword.getText().toString();
            String repassword = eRePassword.getText().toString();
            if(name.equals("")||birthday.equals("")||address.equals("")||username.equals("")||
                    password.equals("")||repassword.equals("")){
                Toast.makeText(RegisterActivity.this, "You need to fill in all the information!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!password.equals(repassword)){
                Toast.makeText(RegisterActivity.this, "Enter password again!", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User(name,birthday,address,username,password,User.CUSTOMER);
            SQLiteHelper db = new SQLiteHelper(RegisterActivity.this);
            db.addUser(user);
            Toast.makeText(RegisterActivity.this, "SignUp successful!", Toast.LENGTH_SHORT).show();
            eName.setText(""); eBirthday.setText(""); eAddress.setText("");
            eUsername.setText(""); ePassword.setText(""); eRePassword.setText("");
        }
    }
}