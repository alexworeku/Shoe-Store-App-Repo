package com.example.shoestoreapp.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoestoreapp.Models.Brand;
import com.example.shoestoreapp.Provider.SqliteDbProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BrandRepository implements IBrandRepository {
    private Context context;

    public BrandRepository(Context context) {
        this.context = context;
    }

    @Override
    public boolean add(Brand brand) {
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BrandName", brand.getBrandName());
        contentValues.put("BrandImage", brand.getBrandImage());
        long result = sqLiteDatabase.insert("Brand", null, contentValues);
        sqLiteDatabase.close();
        return result != -1;
    }

    @Override
    public boolean remove(int brandId) {
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getWritableDatabase();
        long result = sqLiteDatabase.delete("Brand", "Id = ?", new String[]{Integer.toString(brandId)});
        sqLiteDatabase.close();
        return result != -1;
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> brands = new ArrayList<>();
        SqliteDbProvider dbProvider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = dbProvider.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Brand", null);
        while (cursor.moveToNext()) {
            brands.add(new Brand(
                    cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("BrandName")),
                    cursor.getBlob(cursor.getColumnIndex("BrandImage")
                    )));
        }
        cursor.close();
        sqLiteDatabase.close();
        return brands;
    }
}
