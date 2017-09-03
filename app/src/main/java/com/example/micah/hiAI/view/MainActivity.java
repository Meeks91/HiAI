package com.example.micah.hiAI.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.micah.hiAI.Application;
import com.example.micah.hiAI.MainActivityPresenter;
import com.example.micah.hiAI.R;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityViewDelegate {

    private final String TAG = MainActivity.class.getSimpleName();
    @Inject
    MainActivityPresenter mainActivityPresenter;
    @BindView(R.id.gifImageView) ImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Application.getAppComponent(this).inject(this);

        initPermissions();
    }

    //MARK: --------------- INITIALISATION METHODS

    /**
     * request audio recording permission for api 23+
     */
    private void initPermissions() {

        //check if we have audio recording permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            //request recording audio permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
        }
    }

    //MARK: --------------- INITIALISATION METHODS

    //MARK: --------------- UPDATE UI METHODS

    @Override
    public void onGifRetrieved(final String gifUrl) {

        //update the gif that's playing
        runOnUiThread(() -> Glide.with(MainActivity.this).asGif().load(gifUrl).into(gifImageView));
    }

    @Override
    public void onSpeechEnded() {

        //stop the gif playing
        runOnUiThread(() -> Glide.with(MainActivity.this).load(null).into(gifImageView));
    }

    //MARK: ---------------  USER INPUT ROUTING METHODS

    /**
     * Called as onClick to start the AI service listening
     * for speech. It also requests the presenter to
     * get a random gif
     *
     * @param view - the view which triggers this onClick
     */
    public void startTheAiServiceListeningForSpeech(View view) {

        //start the ai service listening for speech
        mainActivityPresenter.startTheAiServiceListeningForSpeech();

        //get a gif now that we're listening for to audio
        mainActivityPresenter.getRandomGif();
    }

    //MARK: ---------------  USER INPUT ROUTING METHODS
}


