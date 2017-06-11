package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.R;
import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.java.moviefy.mvp.model.MovieDAO;
import com.java.moviefy.mvp.presenter.MoviePresenter;
import com.java.moviefy.mvp.presenter.MoviePresenterImple;
import com.java.moviefy.mvp.view.MainActivityView;
import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import static com.java.moviefy.R.string.query;


/**
 * Created by swapnil on 6/10/17.
 */

@Module(includes = {ContextModule.class, MovieModelDAOModule.class, NetworkModule.class})
public class MoviePresenterModule {


    @Provides @Named("query_map") @ActivityScope
    public Map<String, String> getQueryMapGetMovie(Context context){
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(context.getString(R.string.api_key), context.getString(R.string.api_key_value));
        queryMap.put(context.getString(R.string.language), context.getString(R.string.en_US));
        queryMap.put(context.getString(R.string.page), "1");

        return queryMap;
    }

    @Provides @Named("query_mapSearch_movie") @ActivityScope
    public Map<String, String> getQueryMapSearchMovie(Context context){
        HashMap<String, String> queryMapSearchMovie = new HashMap<String, String>();
        queryMapSearchMovie.put(context.getString(R.string.api_key), context.getString(R.string.api_key_value));
        queryMapSearchMovie.put(context.getString(R.string.language), context.getString(R.string.en_US));
        queryMapSearchMovie.put(context.getString(query), "");
        return queryMapSearchMovie;
    }


    @Provides @ActivityScope
    public MoviePresenter getMoviePresenter(GetMoviesService getMoviesService, SearchMoviesService searchMoviesService,
                                            MainActivityView view, @Named("query_map") Map<String, String> queryMapGetMovie
                                            , @Named("query_mapSearch_movie") Map<String, String> queryMapSearchMovie,
                                            MovieDAO modelDao) {

     return new MoviePresenterImple(getMoviesService, searchMoviesService, view,
                                    queryMapGetMovie,queryMapSearchMovie, modelDao);
    }


}
