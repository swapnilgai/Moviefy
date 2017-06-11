package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.injection.CustomeScope.SearchActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/10/17.
 */

@Module
public class SearchContextModule {

    private  final Context context;

    public SearchContextModule(Context context){
        this.context = context;
    }

    @Provides
    @SearchActivityScope
    public Context getContext(){
        return context;
    }

}
