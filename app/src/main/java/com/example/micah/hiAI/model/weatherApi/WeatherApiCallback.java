package com.example.micah.hiAI.model.weatherApi;

import com.example.micah.hiAI.model.weatherApi.jsonParsing.WeatherJsonModel.WeatherInfo;

public interface WeatherApiCallback {

    public void onWeatherRetrieved(WeatherInfo weatherInfo);
}
