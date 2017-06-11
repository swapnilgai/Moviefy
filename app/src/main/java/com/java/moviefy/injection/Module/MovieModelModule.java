package com.java.moviefy.injection.Module;

import com.java.moviefy.mvp.model.MovieModel;
import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Created by swapnil on 6/10/17.
 */

@Module (includes = {NetworkModule.class, ContextModule.class})
public class MovieModelModule {

        @Provides @Singleton
        public MovieModel getMovieModule(Observable<Boolean> networkInfo, GetMoviesService getMoviesService, SearchMoviesService searchMoviesService){
            return new MovieModel(networkInfo, getMoviesService, searchMoviesService);
        }
}

