package com.java.moviefy.injection.Component;

import com.java.moviefy.MainActivity;
import com.java.moviefy.injection.Module.ContextModule;
import com.java.moviefy.injection.Module.NetworkModule;
import com.java.moviefy.injection.Module.RecycleAdapterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by swapnil on 6/8/17.
 */

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, RecycleAdapterModule.class})
    public interface ContextComponent {

            void injectActivity(MainActivity activity);
    }
