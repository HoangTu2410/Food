package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

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

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eImg,eName,eDescription,ePrice;
    private Button btAdd,btCancel;
    private List<Category> list;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        db = new SQLiteHelper(this);
        list = db.getAllCategory();
        initView();
        btAdd.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void initView() {
        sp = findViewById(R.id.spCategory);
        eImg = findViewById(R.id.eImg);
        eName = findViewById(R.id.eName);
        eDescription = findViewById(R.id.eDescription);
        ePrice = findViewById(R.id.ePrice);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        List<String> listName = new ArrayList<>();
        for(Category i:list){
            listName.add(i.getName());
        }
        String[] categories = listName.toArray(new String[listName.size()]);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,categories));
    }

    @Override
    public void onClick(View view) {
        if(view==btCancel){
            finish();
        }
        if(view==btAdd){
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
                }
            }
            Food food = new Food(img,name,des,price,list.get(position));
            db.addFood(food);
            finish();
        }
    }
}