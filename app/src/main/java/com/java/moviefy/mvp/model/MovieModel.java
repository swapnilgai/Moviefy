package com.java.moviefy.mvp.model;

import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import rx.Observable;

/**
 * Created by swapnil on 6/10/17.
 */

public class MovieModel {

    Observable<Boolean>  networkInfo;

    GetMoviesService getMoviesService;

    SearchMoviesService searchMoviesService;

    public MovieModel(Observable<Boolean> networkInfo, GetMoviesService getMoviesService, SearchMoviesService searchMoviesService){
        this.networkInfo = networkInfo;
        this.getMoviesService = getMoviesService;
        this.searchMoviesService = searchMoviesService;
    }

    public Observable<Boolean> getNetworkInfo(){
        return networkInfo;
    }

    public GetMoviesService getGetMoviesService(){
        return  getMoviesService;
    }

    public SearchMoviesService getSearchMoviesService(){
        return  searchMoviesService;
    }

//    public Observable<Boolean> isNetworkAvailableObservable(NetworkInfo networkInfo) {
//        return Observable.just(networkInfo.isAvailable());
//    }

}
