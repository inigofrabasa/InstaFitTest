package com.inigoflores.instafittest.coaches.utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by inigo on 03/12/17.
 */

public class BitmapInternal {

    public String saveToInternalStorage(Bitmap bitmapImage, String imageName, Context context){

        ContextWrapper cw = new ContextWrapper(context);
        // path: /data/data/InstaFit/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        File mypath = new File(directory,imageName + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch(NullPointerException ex){}
        }
        return directory.getAbsolutePath();
    }

    public Bitmap loadImageFromStorage(String path, String imageName)
    {
        Bitmap bitmap = null;
        try {
            File f = new File(path, imageName + ".jpg");
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return bitmap;
    }
}
