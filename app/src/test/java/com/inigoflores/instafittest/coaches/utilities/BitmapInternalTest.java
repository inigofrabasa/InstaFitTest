package com.inigoflores.instafittest.coaches.utilities;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by inigo on 04/12/17.
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class BitmapInternalTest {

    @Test
    public void saveToInternalStorage() throws Exception {

        Bitmap originalBitmap = create("Original bitmap", 10, 10);

        String imageName = "myBitmap";
        Context context = RuntimeEnvironment.application;
        Boolean expected = true;

        Boolean output = false;
        BitmapInternal bitmapInternal = new BitmapInternal();
        String resultPath = bitmapInternal.saveToInternalStorage(originalBitmap, imageName, context);
        if(resultPath.toCharArray().length > 0 )
        {
            output = true;
        }

        assertEquals(expected, output);
    }

    @Test
    public void loadImageFromStorage() throws Exception {
    }

    private static Bitmap create(String name, int width, int height) {
        Bitmap bitmap = Shadow.newInstanceOf(Bitmap.class);
        shadowOf(bitmap).appendDescription(name);
        return bitmap;
    }

}