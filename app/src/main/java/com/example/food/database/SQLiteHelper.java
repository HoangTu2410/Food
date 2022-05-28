package com.example.food.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.food.model.Cart;
import com.example.food.model.Category;
import com.example.food.model.Food;
import com.example.food.model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SaleFood.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE user("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, birthday TEXT, address TEXT," +
                "username TEXT, password TEXT, position INTEGER)";
        String sql1 = "CREATE TABLE category("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "img INTEGER, name TEXT)";
        String sql2 = "CREATE TABLE food("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "img INTEGER, name TEXT, description TEXT," +
                "price FLOAT, categoryid INTEGER)";
        String sql3 = "CREATE TABLE cart("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "amount INTEGER, userid INTEGER, foodid INTEGER)";
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public Object checkLogin(String username, String password){
        SQLiteDatabase st = getReadableDatabase();
        String whereClause = "username = ? AND password = ?";
        String[] whereArgs = {username,password};
        Cursor rs = st.query("user",null,whereClause,whereArgs,null,null,null);
        while(rs!=null&&rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String birthday = rs.getString(2);
            String address = rs.getString(3);
            int position = rs.getInt(6);
            User user = new User(id,name,birthday,address,username,password,position);
            return user;
        }
        return null;
    }

    public long addUser(User i){
        ContentValues values = new ContentValues();
        values.put("name",i.getName());
        values.put("birthday",i.getBirthday());
        values.put("address",i.getAddress());
        values.put("username",i.getUsername());
        values.put("password",i.getPassword());
        values.put("position",i.getPosition());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("user",null,values);
    }

    public int updateUser(User i){
        ContentValues values = new ContentValues();
        values.put("name",i.getName());
        values.put("birthday",i.getBirthday());
        values.put("address",i.getAddress());
        values.put("username",i.getUsername());
        values.put("password",i.getPassword());
        values.put("position",i.getPosition());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("user",values,whereClause,whereArgs);
    }

    public int deleteUser(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("user",whereClause,whereArgs);
    }

    public long addCategory(Category i){
        ContentValues values = new ContentValues();
        values.put("img",i.getImg());
        values.put("name",i.getName());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("category",null,values);
    }

    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("category",null,null,null,null,
                null,null);
        while (rs!=null&&rs.moveToNext()){
            int id = rs.getInt(0);
            int img = rs.getInt(1);
            String name = rs.getString(2);
            list.add(new Category(id,img,name));
        }
        return list;
    }

    public List<Food> searchByCategory(int categoryid){
        List<Food> list = new ArrayList<>();
        String whereClause = "categoryid = ?";
        String[] whereArgs = {Integer.toString(categoryid)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("food",null,whereClause,whereArgs,null,
                null,null,null);
        while (rs!=null&&rs.moveToNext()) {
            int id = rs.getInt(0);
            int img = rs.getInt(1);
            String name = rs.getString(2);
            String descriton = rs.getString(3);
            float price = rs.getFloat(4);

            String whereClause1 = "id = ?";
            String[] whereArgs1 = {Integer.toString(categoryid)};
            Cursor rs1 = st.query("category", null, whereClause1, whereArgs1, null,
                    null, null);
            Category cate = new Category();
            while (rs1 != null && rs1.moveToNext()) {
                cate.setId(rs1.getInt(0));
                cate.setImg(rs1.getInt(1));
                cate.setName(rs.getString(2));
            }
            list.add(new Food(id, img, name, descriton, price, cate));
        }
        return list;
    }

    public List<Food> searchByName(String key){
        List<Food> list = new ArrayList<>();
        String whereClause = "name like ?";
        String[] whereArgs = {"%"+key+"%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("food",null,whereClause,whereArgs,null,
                null,null,null);
        while (rs!=null&&rs.moveToNext()) {
            int id = rs.getInt(0);
            int img = rs.getInt(1);
            String name = rs.getString(2);
            String descriton = rs.getString(3);
            float price = rs.getFloat(4);
            int categoryid = rs.getInt(5);

            String whereClause1 = "id = ?";
            String[] whereArgs1 = {Integer.toString(categoryid)};
            Cursor rs1 = st.query("category", null, whereClause1, whereArgs1, null,
                    null, null);
            Category cate = new Category();
            while (rs1 != null && rs1.moveToNext()) {
                cate.setId(rs1.getInt(0));
                cate.setImg(rs1.getInt(1));
                cate.setName(rs.getString(2));
            }
            list.add(new Food(id, img, name, descriton, price, cate));
        }
        return list;
    }

    public List<Food> getAllFood(){
        List<Food> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("food",null,null,null,null,
                null,null);
        while (rs!=null&&rs.moveToNext()){
            int id = rs.getInt(0);
            int img = rs.getInt(1);
            String name = rs.getString(2);
            String descriton = rs.getString(3);
            float price = rs.getFloat(4);
            int categoryid = rs.getInt(5);

            String whereClause = "id = ?";
            String[] whereArgs = {Integer.toString(categoryid)};
            Cursor rs1 = st.query("category",null,whereClause,whereArgs,null,
                    null,null);
            Category category = new Category();
            while (rs1!=null&&rs1.moveToNext()){
                category.setId(rs1.getInt(0));
                category.setImg(rs1.getInt(1));
                category.setName(rs.getString(2));
            }
            list.add(new Food(id,img,name,descriton,price,category));
        }
        return list;
    }

    public long addFood(Food i){
        ContentValues values = new ContentValues();
        values.put("img",i.getImg());
        values.put("name",i.getName());
        values.put("description",i.getDescription());
        values.put("price",i.getPrice());
        values.put("categoryid",i.getCategory().getId());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("food",null,values);
    }

    public int updateFood(Food i){
        ContentValues values = new ContentValues();
        values.put("img",i.getImg());
        values.put("name",i.getName());
        values.put("description",i.getDescription());
        values.put("price",i.getPrice());
        values.put("categoryid",i.getCategory().getId());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("food",values,whereClause,whereArgs);
    }

    public int deleteFood(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("food",whereClause,whereArgs);
    }

    public long addCart(Cart i){
        ContentValues values = new ContentValues();
        values.put("amount",i.getAmount());
        values.put("userid",i.getCustomer().getId());
        values.put("foodid",i.getFood().getId());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("cart",null,values);
    }

    public int updateCart(Cart i){
        ContentValues values = new ContentValues();
        values.put("amount",i.getAmount());
        values.put("userid",i.getCustomer().getId());
        values.put("foodid",i.getFood().getId());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("cart",values,whereClause,whereArgs);
    }

    public int deleteCart(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("cart",whereClause,whereArgs);
    }

    public List<Cart> getCartByCustomer(int customerid){
        List<Cart> list = new ArrayList<>();
        String whereClause = "userid = ?";
        String[] whereArgs = {Integer.toString(customerid)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("cart",null,whereClause,whereArgs,null,
                null,null);
        while (rs!=null&&rs.moveToNext()){
            int id = rs.getInt(0);
            int amount = rs.getInt(1);
            int userid = rs.getInt(2);
            int foodid = rs.getInt(3);

            String whereClause1 = "id = ?";
            String[] whereArgs1 = {Integer.toString(userid)};
            Cursor rs1 = st.query("user",null,whereClause1,whereArgs1,null,
                    null,null);
            User user = new User();
            while (rs1!=null&&rs1.moveToNext()){
                user.setId(rs1.getInt(0));
                user.setName(rs1.getString(1));
                user.setBirthday(rs1.getString(2));
                user.setAddress(rs1.getString(3));
                user.setUsername(rs1.getString(4));
                user.setPassword(rs1.getString(5));
                user.setPosition(rs1.getInt(6));
            }

            String whereClause2 = "id = ?";
            String[] whereArgs2 = {Integer.toString(foodid)};
            Cursor rs2 = st.query("food",null,whereClause2,whereArgs2,null,
                    null,null);
            Food food = new Food();
            while (rs2!=null&&rs2.moveToNext()) {
                food.setId(rs2.getInt(0));
                food.setImg(rs2.getInt(1));
                food.setName(rs2.getString(2));
                food.setDescription(rs2.getString(3));
                food.setPrice(rs2.getFloat(4));
                int categoryid = rs2.getInt(5);

                String whereClause3 = "id = ?";
                String[] whereArgs3 = {Integer.toString(categoryid)};
                Cursor rs3 = st.query("category", null, whereClause3, whereArgs3, null,
                        null, null);
                Category category = new Category();
                while (rs3 != null && rs3.moveToNext()) {
                    category.setId(rs3.getInt(0));
                    category.setImg(rs3.getInt(1));
                    category.setName(rs3.getString(2));
                }
                food.setCategory(category);
            }
            list.add(new Cart(id,amount,user,food));
        }
        return list;
    }

    public List<Cart> getAllCart(){
        List<Cart> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("cart",null,null,null,null,
                null,null);
        while (rs!=null&&rs.moveToNext()){
            int id = rs.getInt(0);
            int amount = rs.getInt(1);
            int userid = rs.getInt(2);
            int foodid = rs.getInt(3);

            String whereClause1 = "id = ?";
            String[] whereArgs1 = {Integer.toString(userid)};
            Cursor rs1 = st.query("user",null,whereClause1,whereArgs1,null,
                    null,null);
            User user = new User();
            while (rs1!=null&&rs1.moveToNext()){
                user.setId(rs1.getInt(0));
                user.setName(rs1.getString(1));
                user.setBirthday(rs1.getString(2));
                user.setAddress(rs1.getString(3));
                user.setUsername(rs1.getString(4));
                user.setPassword(rs1.getString(5));
                user.setPosition(rs1.getInt(6));
            }

            String whereClause2 = "id = ?";
            String[] whereArgs2 = {Integer.toString(foodid)};
            Cursor rs2 = st.query("food",null,whereClause2,whereArgs2,null,
                    null,null);
            Food food = new Food();
            while (rs2!=null&&rs2.moveToNext()) {
                food.setId(rs2.getInt(0));
                food.setImg(rs2.getInt(1));
                food.setName(rs2.getString(2));
                food.setDescription(rs2.getString(3));
                food.setPrice(rs2.getFloat(4));
                int categoryid = rs2.getInt(5);

                String whereClause3 = "id = ?";
                String[] whereArgs3 = {Integer.toString(categoryid)};
                Cursor rs3 = st.query("category", null, whereClause3, whereArgs3, null,
                        null, null);
                Category category = new Category();
                while (rs3 != null && rs3.moveToNext()) {
                    category.setId(rs3.getInt(0));
                    category.setImg(rs3.getInt(1));
                    category.setName(rs3.getString(2));
                }
                food.setCategory(category);
            }
            list.add(new Cart(id,amount,user,food));
        }
        return list;
    }
}
