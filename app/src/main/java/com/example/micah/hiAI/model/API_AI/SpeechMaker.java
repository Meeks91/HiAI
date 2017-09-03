package com.example.micah.hiAI.model.API_AI;

import android.speech.tts.UtteranceProgressListener;

import com.example.micah.hiAI.model.weatherApi.WeatherApiHelper;
import com.example.micah.hiAI.view.MainActivityViewDelegate;

import ai.api.model.AIResponse;

/**
 * Created by Micah on 28/05/2017.
 */

public class SpeechMaker {

    private final String TAG = SpeechMaker.class.getSimpleName();
    private final String GET_THE_WEATHER_TOKEN = "GET_THE_WEATHER";
    private SpeechSynthesiser speechSynthesiser;
    private WeatherApiHelper weatherRetriever;
    private MainActivityViewDelegate mainActivityViewDelegate;

    public SpeechMaker(SpeechSynthesiser speechSynthesiser, WeatherApiHelper weatherRetriever, MainActivityViewDelegate mainActivityViewDelegate){

        this.mainActivityViewDelegate = mainActivityViewDelegate;

        this.speechSynthesiser = speechSynthesiser;

        this.weatherRetriever = weatherRetriever;
    }

    /**
     * routes the speech contained in the result to the speech synthesiser.
     * It passes a completion handler to fetch weather data which is then spoken
     * if the @result's intent's name == GET_THE_WEATHER_TOKEN
     *
     * @param result - an AIResponse which contains what speech to synthesis
     *                 and an intent name which notifies us if we should
     *                 fetch and speak weather data
     */
    public void routeAiResponseIntoSpeech(AIResponse result) {

        //check if we should fetch the weather
        boolean shouldFetchWeather = result.getResult().getMetadata().getIntentName().equals(GET_THE_WEATHER_TOKEN);

        //speak the current speech and provide a callback value
        speechSynthesiser.convertTextToSpeech(result.getResult().getFulfillment().getSpeech(), createUtterListener(shouldFetchWeather));
    }

    //provides a listener for the speechevents when the speech is handling weather retrieval (i.e. "I am fetching weather").
    // When the speech is done it fetches the weather data and speak it out
    private UtteranceProgressListener createUtterListener(final boolean getAndSpeakWeatherOnDone){

        return new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
              }

            @Override
            public void onDone(String utteranceId) {

                if (getAndSpeakWeatherOnDone == true) {

                    //get the weather data and then speak it once it has been retrieved
                    getAndSpeakWeatherData();
                }

                mainActivityViewDelegate.onSpeechEnded();
            }

            @Override
            public void onError(String utteranceId) {
            }
        };
    }

    /**
     * gets the weather data and speaks it once it has been retrieved
     */
    private void getAndSpeakWeatherData(){

        weatherRetriever.getWeather(weatherInfo -> {

                speechSynthesiser.convertTextToSpeech(weatherInfo.main.getSpeechRepresentation(), null);
            });
    }
}
