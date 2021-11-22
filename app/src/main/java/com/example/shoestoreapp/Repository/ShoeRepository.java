package com.example.shoestoreapp.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.Provider.SqliteDbProvider;

import java.util.ArrayList;
import java.util.List;

public class ShoeRepository implements IShoeRepository {
    private Context context;

    public ShoeRepository(Context context) {
        this.context = context;
    }

    @Override
    public boolean add(Shoe shoe) {
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", shoe.getName());
        contentValues.put("Price", shoe.getPrice());
        contentValues.put("Quantity", shoe.getQuantity());
        contentValues.put("Description", shoe.getDescription());
        contentValues.put("Brand", shoe.getBrand());
        contentValues.put("Image", shoe.getImage());
        contentValues.put("StorePhoneNumber", shoe.getStorePhoneNumber());
        contentValues.put("StoreLocation", shoe.getStoreLocation());

        long result = sqLiteDatabase.insert("Shoe", null, contentValues);
        sqLiteDatabase.close();
        return result != -1;
    }

    @Override
    public boolean remove(int id) {

        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();
        long result = sqLiteDatabase.delete("Shoe", "Id = ?", new String[]{Integer.toString(id)});
        sqLiteDatabase.close();
        return result != -1;
    }

    @Override
    public boolean update(Shoe shoe) {
        return false;
    }

    @Override
    public Shoe get(int id) {
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Shoe where Id= ?", new String[]{Integer.toString(id)});
        Shoe shoe = null;
        while (cursor.moveToNext()) {
            shoe = new Shoe(
                    cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getDouble(cursor.getColumnIndex("Price")),
                    cursor.getInt(cursor.getColumnIndex("Quantity")),
                    cursor.getString(cursor.getColumnIndex("Brand")),
                    cursor.getString(cursor.getColumnIndex("Description")),
                    cursor.getBlob(cursor.getColumnIndex("Image")),
                    cursor.getString(cursor.getColumnIndex("StorePhoneNumber")),
                    cursor.getString(cursor.getColumnIndex("StoreLocation"))
            );
        }
        cursor.close();
        sqLiteDatabase.close();
        return shoe;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<Shoe> getAll() {
        List<Shoe> shoesList = new ArrayList<>();
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Shoe", null);
        while (cursor.moveToNext()) {
            shoesList.add(
                    new Shoe(
                            cursor.getInt(cursor.getColumnIndex("Id")),
                            cursor.getString(cursor.getColumnIndex("Name")),
                            cursor.getDouble(cursor.getColumnIndex("Price")),
                            cursor.getInt(cursor.getColumnIndex("Quantity")),
                            cursor.getString(cursor.getColumnIndex("Brand")),
                            cursor.getString(cursor.getColumnIndex("Description")),
                            cursor.getBlob(cursor.getColumnIndex("Image")),
                            cursor.getString(cursor.getColumnIndex("StorePhoneNumber")),
                            cursor.getString(cursor.getColumnIndex("StoreLocation"))
                    )
            );
        }
        cursor.close();
        sqLiteDatabase.close();
        return shoesList;

    }


    @Override
    public List<Shoe> getByBrand(String brandName) {

        List<Shoe> shoesList = new ArrayList<>();
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Shoe where Brand=? ", new String[]{brandName});
        while (cursor.moveToNext()) {
            shoesList.add(
                    new Shoe(
                            cursor.getInt(cursor.getColumnIndex("Id")),
                            cursor.getString(cursor.getColumnIndex("Name")),
                            cursor.getDouble(cursor.getColumnIndex("Price")),
                            cursor.getInt(cursor.getColumnIndex("Quantity")),
                            cursor.getString(cursor.getColumnIndex("Brand")),
                            cursor.getString(cursor.getColumnIndex("Description")),
                            cursor.getBlob(cursor.getColumnIndex("Image")),
                            cursor.getString(cursor.getColumnIndex("StorePhoneNumber")),
                            cursor.getString(cursor.getColumnIndex("StoreLocation"))
                    )
            );
        }
        cursor.close();
        sqLiteDatabase.close();
        return shoesList;

    }

    @Override
    public List<Shoe> findWith(String name) {
        List<Shoe> shoeList = new ArrayList<>();

        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Shoe where Name like ? ", new String[]{name});
        while (cursor.moveToNext()) {

            shoeList.add(new Shoe(
                    cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getDouble(cursor.getColumnIndex("Price")),
                    cursor.getInt(cursor.getColumnIndex("Quantity")),
                    cursor.getString(cursor.getColumnIndex("Brand")),
                    cursor.getString(cursor.getColumnIndex("Description")),
                    cursor.getBlob(cursor.getColumnIndex("Image")),
                    cursor.getString(cursor.getColumnIndex("StorePhoneNumber")),
                    cursor.getString(cursor.getColumnIndex("StoreLocation"))
            ));
        }
        cursor.close();
        sqLiteDatabase.close();
        return shoeList;
    }
}
