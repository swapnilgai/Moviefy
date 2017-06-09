package com.java.moviefy.network.service;

import com.java.moviefy.entities.Result;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by swapnil on 6/8/17.
 */

public interface SearchMoviesService {

    // Ger search result only for movies
    @GET("/search/movie")
    Observable<Result> getSearchedMovieList(@QueryMap Map<String, String> map);

}
