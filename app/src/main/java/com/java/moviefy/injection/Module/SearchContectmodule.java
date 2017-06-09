package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.injection.CustomeScope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/9/17.
 */

@Module
public class SearchContectmodule {

    private  final Context context;

    public SearchContectmodule(Context context){
        this.context = context;
    }

    @Provides
    @ActivityScope
    public Context getContext(){
        return context;
    }

}
