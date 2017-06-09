package com.java.moviefy.injection.Module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/8/17.
 */


@Module
public class ContextModule {

    private  final  Context context;

    public ContextModule(Context context){
            this.context = context;
    }

    @Provides @Singleton
    public Context getContext(){
        return context;
    }

}
