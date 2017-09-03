package com.example.micah.hiAI.model.weatherApi;

import com.example.micah.hiAI.model.weatherApi.jsonParsing.WeatherJsonModel.WeatherInfo;
import com.example.micah.hiAI.model.weatherApi.jsonParsing.WeatherJsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Micah on 28/05/2017.
 */

public class WeatherApiHelper {

    private final String TAG = WeatherApiHelper.class.getSimpleName();
    private final String API_KEY = "4c4af81b73d7c5aab0d36279be747391";
    private final String BASE_API_URL = "http://samples.openweathermap.org/data/2.5/weather";
    private final OkHttpClient okHttpClient;
    private final WeatherJsonParser weatherJsonParser;

    public WeatherApiHelper(OkHttpClient okHttpClient, WeatherJsonParser weatherJsonParser){

        this.okHttpClient = okHttpClient;

        this.weatherJsonParser = weatherJsonParser;
    }

    /**
     *
     *
     * @param weatherInfoCallback
     */
    public void getWeather(final WeatherApiCallback weatherInfoCallback){

            //create the request to get weather
            Request request = new Request.Builder().url(BASE_API_URL+"?q=London,uk&appid="+API_KEY).build();

            //execute the weather retrieval request
            okHttpClient.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {

                    //pass null to signify error
                    weatherInfoCallback.onWeatherRetrieved(null);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    //extract the weather info
                    WeatherInfo weatherInfo = weatherJsonParser.exctractWeatherInfoFrom(response.body().string());

                    //send the parsed weather info to the weatherInfoCallback
                    weatherInfoCallback.onWeatherRetrieved(weatherInfo);
                }
            });
    }
}