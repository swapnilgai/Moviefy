package com.java.moviefy.injection.Module;

import com.j256.ormlite.dao.Dao;
import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.java.moviefy.mvp.model.MovieDAO;
import com.java.moviefy.mvp.model.MovieDAOImple;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/10/17.
 */

@Module(includes = {DatabaseModule.class})
public class MovieModelDAOModule {

    @Provides @ActivityScope
    public MovieDAO getMovieDAO(Dao userDao){
       return new MovieDAOImple(userDao);
    }
}
