package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.food.database.SQLiteHelper;
import com.example.food.model.Category;
import com.example.food.model.Food;

import java.util.ArrayList;
import java.util.List;

public class ModifyFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eImg,eName,eDescription,ePrice;
    private Button btUpdate,btRemove,btBack;
    private List<Category> list;
    private SQLiteHelper db;
    private Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_food);
        db = new SQLiteHelper(this);
        list = db.getAllCategory();
        initView();
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btBack.setOnClickListener(this);
        Intent intent = getIntent();
        food = (Food) intent.getSerializableExtra("food");
        String imgName = getResources().getText(food.getImg()).toString();
        while(imgName.charAt(imgName.length()-1)!='.'){
            imgName = imgName.substring(0,imgName.length()-1);
        }
        imgName = imgName.substring(0,imgName.length()-1);
        String img = "";
        while(imgName.charAt(imgName.length()-1)!='/'){
            img += imgName.charAt(imgName.length()-1);
            imgName = imgName.substring(0,imgName.length()-1);
        }
        StringBuilder builder = new StringBuilder(img);
        img = builder.reverse().toString();
        eImg.setText(img);
        eName.setText(food.getName());
        eDescription.setText(food.getDescription());
        ePrice.setText(food.getPrice()+"");
        int position = 0;
        for(int i=0; i<list.size(); i++){
            if(food.getCategory().getId()==list.get(i).getId()){
                position = i;
                break;
            }
        }
        sp.setSelection(position);
    }

    private void initView(){
        sp = findViewById(R.id.spCategory);
        eImg = findViewById(R.id.eImg);
        eName = findViewById(R.id.eName);
        eDescription = findViewById(R.id.eDescription);
        ePrice = findViewById(R.id.ePrice);
        btUpdate = findViewById(R.id.btUpdate);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        List<String> listName = new ArrayList<>();
        for(Category i:list){
            listName.add(i.getName());
        }
        String[] categories = listName.toArray(new String[listName.size()]);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,categories));
    }

    @Override
    public void onClick(View view) {
        if(view==btBack){
            finish();
        }
        if(view==btUpdate){
            String txtImg = eImg.getText().toString();
            String name = eName.getText().toString();
            String des = eDescription.getText().toString();
            String txtPrice = ePrice.getText().toString();
            if(name.equals("")||des.equals("")){
                Toast.makeText(this, "You need to fill full information!", Toast.LENGTH_SHORT).show();
                return;
            }
            int img = getResources().getIdentifier(txtImg,"drawable",getPackageName());
            float price;
            try {
                price = Float.parseFloat(txtPrice);
            } catch (NumberFormatException e){
                Toast.makeText(this, "Price incorrect!", Toast.LENGTH_SHORT).show();
                return;
            }
            String cateName = sp.getSelectedItem().toString();
            int position = 0;
            for(int i=0; i<list.size(); i++){
                if(cateName.equals(list.get(i).getName())){
                    position = i;
                    break;
                }
            }
            Food f = new Food(food.getId(),img,name,des,price,list.get(position));
            db.updateFood(f);
            finish();
        }
        if(view==btRemove){
            int id = food.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa "+food.getName()+" không?");
            builder.setIcon(R.drawable.img_remove);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.deleteFood(id);
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}