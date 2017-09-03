package com.example.micah.hiAI.model.API_AI;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import com.example.micah.hiAI.Application;

import java.util.Locale;

/**
 * Created by Micah on 28/05/2017.
 */

public class SpeechSynthesiser implements  Application.ActivityLifecycleCallbacks {

    private final String TAG = SpeechSynthesiser.class.getSimpleName();
    private TextToSpeech textToSpeechEngine;
    private Context context;

    public SpeechSynthesiser(Context context) {

        this.context = context;

        registerForLifecycleCallbacks();
    }

    //MARK: -------- INITIALISATION METHODS

    /**
     * registers the class to receive lifecycle callbacks.
     */
    private void registerForLifecycleCallbacks() {

        //register the class for callbacks via the Application class
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this);
    }

    /**
     * initiates the textToSpeechEngine and instantiates the TextToSpeech.OnInitListener
     */
    private void initTextToSpeechEngine() {

        Log.d(TAG, "initTextToSpeechEngine() called");

        //init the TextToSpeech and create the TextToSpeech.OnInitListener
        //Use application context to avoid textToSpeechEngine leaking out of the activity
        textToSpeechEngine = new TextToSpeech(context.getApplicationContext(), status -> {

                //check if the initialisation was a success
                if (status == TextToSpeech.SUCCESS) {

                    //set the textToSpeechEngine and get the result
                    int result = textToSpeechEngine.setLanguage(Locale.UK);

                    //check if we set the language successfully
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                        Log.e(TAG, "error: this Language is not supported");
                    }
                }

                //here we didn't successfully initialise the textToSpeechEngine
                else {

                    Log.e("error", "Initilization Failed!");
                }
            }
        );
    }

    //MARK: -------- INITIALISATION METHODS

    //MARK: -------- textToSpeechEngine OPERATIONAL METHODS

    /**
     * method which makes the textToSpeechEngine speak the specified textToSpeak
     * and sets a utteranceProgressListener to the textToSpeechEngine
     *
     * @param textToSpeak - the text that should be spoken by the textToSpeechEngine
     * @param utteranceProgressListener  - a callback for the textToSpeechEngine which provides progress updates
     */
    protected void convertTextToSpeech(String textToSpeak, UtteranceProgressListener utteranceProgressListener) {

        //set the utteranceProgressListener
        textToSpeechEngine.setOnUtteranceProgressListener(utteranceProgressListener);

        //speak the specified text
        textToSpeechEngine.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null,  "" + System.currentTimeMillis());
    }

    /**
     * stops the textToSpeechEngine to save resources
     */
    private void stopTheTextToSpeechEngine() {

        //stop the speaking occuring
        textToSpeechEngine.stop();

        //get back the resources:
        textToSpeechEngine.shutdown();
        textToSpeechEngine = null;
    }

    //MARK: -------- textToSpeechEngine OPERATIONAL METHODS

    //MARK: -------- LIFECYCLE CALLBACKS

    //stops the class from receiving lifeycle callbacks
    private void unregisterFomLifeLifecycleCallbacks(){

        //unregister the lifecycle callbacks
        ((Application) context.getApplicationContext()).unregisterActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {


        initTextToSpeechEngine();
    }

    @Override
    public void onActivityPaused(Activity activity) {

        Log.d(TAG, "onActivityPaused called()");

        //stop to the textToSpeechEngine
        stopTheTextToSpeechEngine();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        //unregisters the class from lifecycle callbacks. Need to unregister to stop double registration
        unregisterFomLifeLifecycleCallbacks();
    }
}