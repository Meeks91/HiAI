package com.example.micah.hiAI.dagger;

import com.example.micah.hiAI.view.MainActivity;

import dagger.Component;

/**
 * Created by Micah on 27/05/2017.
 */


@Component (modules = {MainActivityModule.class, NetModule.class, SpeechModule.class})

public interface AppComponent {

    public void inject(MainActivity mainActivity);
}
