package com.example.micah.hiAI;

import android.content.Context;

import com.example.micah.hiAI.dagger.MainActivityModule;
import com.example.micah.hiAI.dagger.DaggerAppComponent;

/**
 * Created by Micah on 27/05/2017.
 */

public class Application extends android.app.Application {

    private DaggerAppComponent daggerAppComponent;

    /**
     * returns the daggerAppComponent as a singleton instance. It will create it
     * if it doesn't already exist.
     *
     * @param context
     * @return DaggerAppComponent
     */
    public static DaggerAppComponent getAppComponent(Context context) {


        if (((Application) context.getApplicationContext()).daggerAppComponent != null)

            return ((Application) context.getApplicationContext()).daggerAppComponent;

        ((Application)context.getApplicationContext()).daggerAppComponent =
                                                                      (DaggerAppComponent)DaggerAppComponent.builder()
                                                                                          .mainActivityModule(new MainActivityModule(context)).build();

        return ((Application) context.getApplicationContext()).daggerAppComponent;
    }
}

