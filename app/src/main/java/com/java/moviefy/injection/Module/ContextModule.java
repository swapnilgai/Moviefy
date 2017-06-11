package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.java.moviefy.mvp.view.MainActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/8/17.
 */


@Module
public class ContextModule {

    private Context context;
    private MainActivityView mainActivityView;

    public ContextModule(Context context, MainActivityView mainActivityView){
            this.context = context;
            this.mainActivityView = mainActivityView;
    }

    @Provides @ActivityScope
    public Context getContext(){
        return context;
    }

    @Provides @ActivityScope
    public MainActivityView getView(){
        return  mainActivityView;
    }


}
