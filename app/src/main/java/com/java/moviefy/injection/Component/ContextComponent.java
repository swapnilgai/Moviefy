package com.java.moviefy.injection.Component;

import com.java.moviefy.MainActivity;
import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.java.moviefy.injection.Module.ContextModule;
import com.java.moviefy.injection.Module.DatabaseModule;
import com.java.moviefy.injection.Module.MovieModelDAOModule;
import com.java.moviefy.injection.Module.MoviePresenterModule;
import com.java.moviefy.injection.Module.NetworkModule;
import com.java.moviefy.injection.Module.RecycleAdapterModule;

import dagger.Component;

/**
 * Created by swapnil on 6/8/17.
 */

@ActivityScope
@Component( modules = {ContextModule.class, NetworkModule.class, RecycleAdapterModule.class, DatabaseModule.class,
        MoviePresenterModule.class, MovieModelDAOModule.class})
    public interface ContextComponent {

            void injectActivity(MainActivity activity);

    }
