package com.example.skidush622.myapplication.activities;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;

import com.example.skidush622.myapplication.R;
import com.example.skidush622.myapplication.utils.*;
import com.example.skidush622.myapplication.downloader.DownloadWithAsyncTask;

import java.io.IOException;

public class DownloadActivity extends LifecycleLoggingActivity {

    //debug tag for logging debug output to Logcat
    final static String TAG = DownloadActivity.class.getSimpleName();

    //default download Url
    final static String mDefaultUrl = "http://www.dre.vanderbilt.edu/~schmidt/ka.png";

    //User's selection of URL to download
    private EditText mUrlEditText;

    //Image that's been downloaded
    private ImageView mImageView;
    //

    /**
     * method that initialize the activity when it is first created
     *
     * @param savedInstanceState
     *        previous activity state, if there was one
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Sets the content view specified in the
        // activity_download.xml file.
        setContentView(R.layout.activity_download);

        mUrlEditText = (EditText)findViewById(R.id.mUrlEditText);
        mImageView = (ImageView)findViewById(R.id.mImageView);
    }


    /**
     * Called when a user clicks a button to download an image via an
     * an asynctask.
     *
     * @param view
     *            The "Start Downloading" button
     */
    public void runAsyncTask(View view) {
        // The the URL for the image to download.
        final String url = getUrlString();

        UiUtils.hideKeyboard(this, mUrlEditText.getWindowToken());

        // Execute the download using an asynctask.
        new DownloadWithAsyncTask(this).execute(url);
    }

    /**
     * Download a bitmap image from the URL provided by the user.
     *
     * @param url
     *            The url where a bitmap image is located
     * @return the image bitmap or null if there was an error
     */
    public Bitmap downloadBitmap(String url) {
        // Use the default URL if the user doesn't supply one.
        final String finalUrl = url.equals("") ? mDefaultUrl : url;

        try {
            Bitmap bitmap = UiUtils.downloadAndDecodeImage(finalUrl);

            if (bitmap == null)
                // Post error reports to the UI Thread.
                runOnUiThread(
                        // Use a Toast to inform user that
                        // something has gone wrong.
                        new Runnable() {
                            @Override
                            public void run() {
                                UiUtils.showToast(DownloadActivity.this,
                                        "Error downloading image,"
                                                + " please recheck URL "
                                                + finalUrl);
                            }
                        });

            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Display a downloaded bitmap image if it's non-null; otherwise,
     * it reports an error via a Toast.
     *
     * @param image
     *            The bitmap image
     */
    public void displayBitmap(Bitmap image) {
        if (mImageView == null)
            UiUtils.showToast(this, "Problem with the app," + " please contact the developer.");
        else if (image != null)
            mImageView.setImageBitmap(image);
        else
            UiUtils.showToast(this, "image is corrupted," + " please check the requested URL.");
    }

    /**
     * Called when a user clicks a button to reset an image to
     * default.
     *
     * @param view
     *            The "Reset Image" button
     */
    public void resetImage(View view)
    {
        mImageView.setImageResource(R.mipmap.ic_launcher);
    }

    public String getUrlString()
    {
        return mUrlEditText.getText().toString();
    }

}
