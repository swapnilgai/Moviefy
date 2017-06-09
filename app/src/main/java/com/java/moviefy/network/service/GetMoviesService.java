package com.java.moviefy.network.service;

import com.java.moviefy.entities.Result;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by swapnil on 6/8/17.
 */

public interface GetMoviesService {

    // Get Upcoming movies
    @GET("movie/upcoming")
    Observable<Result> getUpcomingMovieList(@QueryMap Map<String, String> map);

    // Get top rated movies
    @GET("movie/top_rated")
    Observable<Result> getTopRatedMovieList(@QueryMap Map<String, String> map);

    ///Get Now playing movies
    @GET("movie/now_playing")
    Observable<Result> getNoePlayingMovielist(@QueryMap Map<String, String> map);

    //@Get popular movies
    @GET("movie/popular")
    Observable<Result> getPopularMovieList(@QueryMap Map<String, String> map);

    // Get top rated tv show
    @GET("tv/popular")
    Observable<Result> getPopularTvShows(@QueryMap Map<String, String> map);

    // Get top rated tv show
    @GET("tv/top_rated")
    Observable<Result> getTopRatedTvShow(@QueryMap Map<String, String> map);

}
