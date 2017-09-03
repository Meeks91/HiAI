package com.example.micah.hiAI.dagger;

import com.example.micah.hiAI.gifRetrieval.GifJsonParser;
import com.example.micah.hiAI.gifRetrieval.GifRetriever;
import com.example.micah.hiAI.model.weatherApi.WeatherApiHelper;
import com.example.micah.hiAI.model.weatherApi.jsonParsing.WeatherJsonParser;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Micah on 28/05/2017.
 */

@Module
public class NetModule {

    @Provides
    public OkHttpClient providesSomething(){

        return new OkHttpClient.Builder().build();
    }

    @Provides
    public GifRetriever providesGifRetriever(OkHttpClient okHttpClient, GifJsonParser gifJsonParser){

        return new GifRetriever(okHttpClient, gifJsonParser);
    }

    @Provides
    public WeatherApiHelper provideWeatherRetriever(OkHttpClient okHttpClient, WeatherJsonParser weatherJsonParser){

        return new WeatherApiHelper(okHttpClient, weatherJsonParser);
    }

    @Provides WeatherJsonParser provideWeatherJsonParser(){

        return new WeatherJsonParser();
    }

    @Provides GifJsonParser provideGifJsonParser(){

        return new GifJsonParser();
    }
}
