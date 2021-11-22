package com.example.shoestoreapp.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoestoreapp.Models.Admin;
import com.example.shoestoreapp.Provider.SqliteDbProvider;

public class AdminRepository implements IAdminRepository {
    private Context context;

    public AdminRepository(Context context) {
        this.context = context;
    }


    @Override
    public Admin get() {
        SqliteDbProvider provider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = provider.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Admin Where Username LIKE ?", new String[]{"admin"});
        cursor.moveToNext();
        Admin admin = new Admin(cursor.getString(cursor.getColumnIndex("Username")),
                cursor.getString(cursor.getColumnIndex("Password")),
                cursor.getInt(cursor.getColumnIndex("LoginStatus")) == 1

        );
        cursor.close();
        sqLiteDatabase.close();
        return admin;
    }

    @Override
    public boolean updateStatus(boolean newStatus) {
        SqliteDbProvider provider = SqliteDbProvider.getProviderInstance(context);
        SQLiteDatabase sqLiteDatabase = provider.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LoginStatus", newStatus ? 1 : 0);
        long result = sqLiteDatabase.update("Admin", contentValues, "Username LIKE ?", new String[]{"admin"});
        return result != -1;
    }
}
