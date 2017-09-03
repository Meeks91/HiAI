package com.example.micah.hiAI.dagger;

import android.content.Context;


import com.example.micah.hiAI.gifRetrieval.GifRetriever;
import com.example.micah.hiAI.MainActivityPresenter;
import com.example.micah.hiAI.model.API_AI.ApiAiListener;
import com.example.micah.hiAI.view.MainActivityViewDelegate;

import ai.api.android.AIService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Micah on 27/05/2017.
 */

@Module
public class MainActivityModule {

    private final Context context;

    public MainActivityModule(Context context) {

        this.context = context;
    }

    @Provides
    public Context provideContext(){

        return context;
    }

    @Provides
    public MainActivityPresenter providesMainActivityPresenter(Context context, GifRetriever gifRetriever, AIService aiService, ApiAiListener apiAiListener ){

        return new MainActivityPresenter((MainActivityViewDelegate) context, gifRetriever, aiService, apiAiListener);
    }
}
