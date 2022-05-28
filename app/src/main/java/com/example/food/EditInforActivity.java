package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.food.database.SQLiteHelper;
import com.example.food.model.User;

import java.util.Calendar;

public class EditInforActivity extends AppCompatActivity {
    private ImageView imgBack;
    private EditText eName,eBirthday,eAddress;
    private Button btChangeInfor;
    private SQLiteHelper db;
    private User myAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_infor);
        intView();
        Intent intent = getIntent();
        myAccount = (User) intent.getSerializableExtra("myaccount");
        eName.setText(myAccount.getName());
        eBirthday.setText(myAccount.getBirthday());
        eAddress.setText(myAccount.getAddress());
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
        eBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(EditInforActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        });
        btChangeInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString();
                String birthday = eBirthday.getText().toString();
                String address = eAddress.getText().toString();
                if(name.equals("")||birthday.equals("")||address.equals("")){
                    Toast.makeText(EditInforActivity.this, "You need to fill in all the information!", Toast.LENGTH_SHORT).show();
                } else {
                    myAccount.setName(name);
                    myAccount.setBirthday(birthday);
                    myAccount.setAddress(address);
                    db.updateUser(myAccount);
                    Toast.makeText(EditInforActivity.this, "Change information successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intView() {
        imgBack = findViewById(R.id.imgBack);
        eName = findViewById(R.id.eName);
        eBirthday = findViewById(R.id.eBirthday);
        eAddress = findViewById(R.id.eAddress);
        btChangeInfor = findViewById(R.id.btChangeInfor);
    }
}