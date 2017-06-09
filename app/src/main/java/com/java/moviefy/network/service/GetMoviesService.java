package com.java.moviefy.network.service;

import com.java.moviefy.entities.Result;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by swapnil on 6/8/17.
 */

public interface GetMoviesService {

    @GET("discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22/550&api_key=a30dce60f5a5371622bab0e560ac8394")
    Observable<Result> getMovieList();

}
