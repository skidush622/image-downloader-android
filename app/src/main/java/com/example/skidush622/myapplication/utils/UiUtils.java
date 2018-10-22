package com.example.skidush622.myapplication.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *This utility class defines static methods shared by various Activities.
 */

public class UiUtils {
    /**
     * Debugging tag.
     */
    private static final String TAG = UiUtils.class.getCanonicalName();
    /**
     * Ensure this class is only used as a utility.
     */
    private UiUtils() {
        throw new AssertionError();
    }

    /**
     * Show a toast message which no user action need
     */
    public static void showToast(Context context,
                                 String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is used to hide a keyboard after a user has
     * finished typing the url.
     */
    public static void hideKeyboard(Activity activity,
                                    IBinder windowToken) {

        InputMethodManager mgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    /**
     * Download an image file from the URL provided by the user and
     * decode into a Bitmap.
     *
     * @param url
     *            The url where a bitmap image is located
     *
     * @return the image bitmap or null if there was an error
     */
    public static Bitmap downloadAndDecodeImage(String url)
            throws IOException {
        // Check to see if this thread has been interrupted.
        if (Thread.interrupted())
            return null;

        // Connect to a remote server, download the contents of
        // the image, and provide access to it via an Input
        // Stream.
        InputStream is = (InputStream) new URL(url).getContent();

        // Check to see if this thread has been interrupted.
        if (Thread.interrupted())
            return null;
        else
            // Decode an InputStream into a Bitmap.
            return BitmapFactory.decodeStream(is);
    }
}
