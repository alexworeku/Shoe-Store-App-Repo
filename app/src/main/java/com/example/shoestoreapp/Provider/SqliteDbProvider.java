package com.example.shoestoreapp.Provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shoestoreapp.MainActivity;

public class SqliteDbProvider extends SQLiteOpenHelper {
    private static SqliteDbProvider instance;

    private SqliteDbProvider(Context context) {
        super(context, "shoestore.db", null, 1);
    }

    public static SqliteDbProvider getProviderInstance(Context context) {
        if (instance == null) {
            instance = new SqliteDbProvider(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create tables
        sqLiteDatabase.execSQL("CREATE TABLE Admin ( Id INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT NOT NULL, Password TEXT NOT NULL,LoginStatus INTEGER NOT NULL) ");
        sqLiteDatabase.execSQL("CREATE TABLE Brand ( Id INTEGER PRIMARY KEY AUTOINCREMENT,BrandName TEXT NOT NULL, BrandImage BLOB NOT NULL )");
        sqLiteDatabase.execSQL("CREATE TABLE Shoe ( Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT NOT NULL,Price REAL NOT NULL,Quantity INTEGER NOT NULL,Brand TEXT NOT NULL,Description TEXT NOT NULL,StorePhoneNumber TEXT NOT NULL,StoreLocation TEXT NOT NULL,Image BLOB NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE CartItem( Id INTEGER PRIMARY KEY AUTOINCREMENT,Quantity INTEGER NOT NULL,ShoeId INTEGER NOT NULL, FOREIGN KEY(ShoeId) REFERENCES Shoe(Id) ON UPDATE CASCADE ON DELETE CASCADE )");

        //Seeding data
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", "admin");
        contentValues.put("Password", "admin@123");
        contentValues.put("LoginStatus", 0);
      sqLiteDatabase.insert("Admin",null,contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
