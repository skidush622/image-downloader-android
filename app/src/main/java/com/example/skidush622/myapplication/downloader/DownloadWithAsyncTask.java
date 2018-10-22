package com.example.skidush622.myapplication.downloader;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.skidush622.myapplication.activities.DownloadActivity;

/**
 * This class downloads a bitmap image in the background using
 * an asynctask.
 */
public class DownloadWithAsyncTask
        extends AsyncTask<String, Integer, Bitmap> {
    /**
     * WeakReference enable garbage collection of Activity.
     */
    private WeakReference<DownloadActivity> mActivity;

    /**
     * Constructor initializes the field.
     */
    public DownloadWithAsyncTask(DownloadActivity activity) {
        mActivity = new WeakReference<>(activity);
    }

    /**
     * Called by the AsyncTask framework in the UI thread to perform
     * initialization actions.
     */
    protected void onPreExecute() {
        // Show the progress dialog before starting the download
        // in a Background Thread.
       System.out.println("downloading...");
        //if get() returns null = the activity is collected as garbage
    }

    /**
     * Downloads bitmap in an AsyncTask background thread.
     *
     * @param urls
     *            The url of a bitmap image
     */
    protected Bitmap doInBackground(String... urls) {

        return mActivity.get().downloadBitmap(urls[0]);
    }

    /**
     * Called after an operation executing in the background is
     * completed. It sets the bitmap image to an image view and
     * dismisses the progress dialog.
     *
     * @param image The bitmap image
     */
    protected void onPostExecute(Bitmap image) {

        // Display the downloaded image to the user.
        mActivity.get().displayBitmap(image);
    }
}
