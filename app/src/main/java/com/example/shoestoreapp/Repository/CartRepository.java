package com.example.shoestoreapp.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoestoreapp.Models.CartItem;
import com.example.shoestoreapp.Models.Shoe;
import com.example.shoestoreapp.Provider.SqliteDbProvider;

import java.util.ArrayList;
import java.util.List;

public class CartRepository implements ICartRepository {
    private Context context;

    public CartRepository(Context context) {
        this.context = context;
    }

    @Override
    public boolean add(CartItem cartItem) {
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Quantity", cartItem.getQuantity());
        contentValues.put("ShoeId", cartItem.getShoeId());


        long result = sqLiteDatabase.insert("CartItem", null, contentValues);
        sqLiteDatabase.close();
        return result != -1;
    }

    @Override
    public boolean remove(int itemId) {
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();
        long result = sqLiteDatabase.delete("CartItem", "Id = ?", new String[]{Integer.toString(itemId)});
        sqLiteDatabase.close();
        return result != -1;
    }

    @Override
    public boolean removeAll() {

        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();
        long result = sqLiteDatabase.delete("CartItem", null, null);
        sqLiteDatabase.close();
        return result != -1;
    }

    @Override
    public boolean update(int itemId, int newValue) {
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Quantity", newValue);
        long result = sqLiteDatabase.update("CartItem", contentValues, "Id LIKE ?", new String[]{Integer.toString(itemId)});
        return result != -1;
    }


    @Override
    public List<CartItem> getAll() {
        List<CartItem> cartItemsList = new ArrayList<>();
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getReadableDatabase();
        Cursor cartItemCursor = sqLiteDatabase.rawQuery("SELECT * FROM CartItem", null);

        while (cartItemCursor.moveToNext()) {

            int shoeId = cartItemCursor.getInt(cartItemCursor.getColumnIndex("ShoeId"));
            Cursor shoeCursor = sqLiteDatabase.rawQuery("Select * from Shoe where Id= ?", new String[]{Integer.toString(shoeId)});
            shoeCursor.moveToFirst();

            Shoe shoeInCart = new Shoe(
                    shoeCursor.getInt(shoeCursor.getColumnIndex("Id")),
                    shoeCursor.getString(shoeCursor.getColumnIndex("Name")),
                    shoeCursor.getDouble(shoeCursor.getColumnIndex("Price")),
                    shoeCursor.getInt(shoeCursor.getColumnIndex("Quantity")),
                    shoeCursor.getString(shoeCursor.getColumnIndex("Brand")),
                    shoeCursor.getString(shoeCursor.getColumnIndex("Description")),
                    shoeCursor.getBlob(shoeCursor.getColumnIndex("Image")),
                    shoeCursor.getString(shoeCursor.getColumnIndex("StorePhoneNumber")),
                    shoeCursor.getString(shoeCursor.getColumnIndex("StoreLocation"))
            );

            cartItemsList.add(
                    new CartItem(
                            cartItemCursor.getInt(cartItemCursor.getColumnIndex("Id")),
                            cartItemCursor.getInt(cartItemCursor.getColumnIndex("Quantity")),
                            shoeInCart

                    ));

        }
        return cartItemsList;
    }

    @Override
    public boolean checkOut() {
        return removeAll();
    }
}
