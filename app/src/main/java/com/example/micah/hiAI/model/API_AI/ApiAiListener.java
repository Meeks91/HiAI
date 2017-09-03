package com.example.micah.hiAI.model.API_AI;

import android.util.Log;

import com.example.micah.hiAI.view.MainActivityViewDelegate;
import ai.api.AIListener;
import ai.api.model.AIError;
import ai.api.model.AIResponse;

/**
 * Created by Micah on 27/05/2017.
 */

public class ApiAiListener implements AIListener
{
    private final String TAG = ApiAiListener.class.getSimpleName();
    private SpeechMaker speechMaker;
    private MainActivityViewDelegate mainActivityViewDelegate;

     public ApiAiListener(SpeechMaker speechMaker, MainActivityViewDelegate mainActivityViewDelegate){

         //set the global speeckMaker
         this.speechMaker = speechMaker;

         //set the global onSpeechFinishedDelegate
         this.mainActivityViewDelegate = mainActivityViewDelegate;
    }

    @Override
    public void onResult(AIResponse result) {

        Log.d(TAG, "onResult and result is: " + result.getResult());

            //pass the result to the speechMaker so it can decide what should be spoken depending on the result's intent
            speechMaker.routeAiResponseIntoSpeech(result);
        }


    @Override
    public void onError(AIError error) {

        Log.v(TAG, "onError and error is: " + error);

        mainActivityViewDelegate.onSpeechEnded();
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {


    }

    @Override
    public void onListeningCanceled() {

        mainActivityViewDelegate.onSpeechEnded();
    }

    @Override
    public void onListeningFinished() {

    }
}
