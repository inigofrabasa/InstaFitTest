package com.inigoflores.instafittest.coaches.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.inigoflores.instafittest.application.InstaFitApplication;
import com.inigoflores.instafittest.coaches.repository.Coach;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import io.realm.Realm;

/**
 * Created by inigo on 02/12/17.
 */

public class DownloadAvatar extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;
    private Coach coach;
    private Realm realm;

    public DownloadAvatar(ImageView imageView, Coach coach) {
        imageViewReference = new WeakReference<>(imageView);
        this.coach = coach;
    }
    @Override
    protected Bitmap doInBackground(String... url) {
        Bitmap bmp = null;
        try {
            if (url[0] != "") {
                URL ulrn = new URL(url[0]);
                HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(bmp != null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            byte[] byteArray = stream.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            return getResizedBitmap(bitmap, 250, 250);
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        coach.setAvatarLocal(
                InstaFitApplication.bitmapInternal.saveToInternalStorage(bitmap, coach.getName(), InstaFitApplication.context));
        realm.insertOrUpdate(coach);
        realm.commitTransaction();

    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();

        return resizedBitmap;
    }
}
