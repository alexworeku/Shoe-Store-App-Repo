package com.example.shoestoreapp.Helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;

public class ImageConverter {
    public static Bitmap toBitmap(byte[] imageData) {

        return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
    }

    public static byte[] fromBitmap(Bitmap image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

}
