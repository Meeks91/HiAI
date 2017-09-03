package com.example.micah.hiAI.dagger;

import android.content.Context;

import com.example.micah.hiAI.model.API_AI.ApiAiListener;
import com.example.micah.hiAI.model.API_AI.SpeechMaker;
import com.example.micah.hiAI.model.API_AI.SpeechSynthesiser;
import com.example.micah.hiAI.model.weatherApi.WeatherApiHelper;
import com.example.micah.hiAI.view.MainActivityViewDelegate;

import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Micah on 28/05/2017.
 */

@Module
public class SpeechModule {

    @Provides
    public SpeechSynthesiser provideSpeechSynthesiser(Context context){

        return new SpeechSynthesiser(context);
    }

    @Provides
    public SpeechMaker provideSpeechMaker(SpeechSynthesiser speechSynthesiser, WeatherApiHelper weatherRetriever, Context mainActivityViewDelegate){

        return new SpeechMaker(speechSynthesiser, weatherRetriever, (MainActivityViewDelegate) mainActivityViewDelegate);
    }

    @Provides
    public AIConfiguration provideAIConfiguration(){

        return new AIConfiguration("81380a0dba334d66addfa61d97e508a7",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
    }

    @Provides
    public AIService provideAIService(Context context, AIConfiguration aiConfiguration){

        return AIService.getService(context, aiConfiguration);
    }

    @Provides
    public ApiAiListener provideApiAiOperator(SpeechMaker speechMaker, Context context){

        return new ApiAiListener(speechMaker, (MainActivityViewDelegate) context);
    }
}
