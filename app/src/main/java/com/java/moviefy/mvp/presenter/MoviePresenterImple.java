package com.java.moviefy.mvp.presenter;

import com.java.moviefy.mvp.model.MovieDAO;
import com.java.moviefy.mvp.model.MovieModel;
import com.java.moviefy.mvp.model.Movies;
import com.java.moviefy.mvp.model.Result;
import com.java.moviefy.mvp.view.MainActivityView;
import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by swapnil on 6/10/17.
 */

public class MoviePresenterImple implements MoviePresenter {

    MainActivityView view;

    Result resultObj;

    Result resultSearchObject;

    MovieDAO modelDao;

    HashMap<String, String> queryMapGetMovie;

    HashMap<String, String> queryMapSearchMovie;

    MovieModel movieModel;

    public MoviePresenterImple(GetMoviesService getMoviesService, SearchMoviesService searchMoviesService,
                               MainActivityView view, HashMap<String, String> queryMapGetMovie
            , HashMap<String, String> queryMapSearchMovie, MovieDAO modelDao, MovieModel movieModel) {

        this.view = view;
        this.queryMapGetMovie = queryMapGetMovie;
        this.queryMapSearchMovie = queryMapSearchMovie;
        this.modelDao = modelDao;
        this.movieModel = movieModel;
    }

    @Override
    public void getUpcomingMovieData() {

        movieModel.getNetworkInfo()
                .doOnNext(networkAvailable -> {
                    if(!networkAvailable){
                        resultObj = modelDao.getMoviesFromMoviesInDB();
                        if(resultObj!=null){
                            view.refreshData(resultObj.getResults());
                        }
                    }
                })
                .filter(networkAvailable ->networkAvailable == true)
                .flatMap(networkAvailable -> movieModel.getGetMoviesService().getUpcomingMovieList(queryMapGetMovie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        view.setSwipeToRefreshOffRefreshing();
                        view.refreshData(resultObj.getResults());
                        modelDao.storeMoviesInDB(resultObj);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(resultObj == null){
                            resultObj = modelDao.getMoviesFromMoviesInDB();
                            view.refreshData(resultObj.getResults());
                        }
                        view.getShowErrorMessage();
                        view.setSwipeToRefreshOffRefreshing();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getTopRatedMovieData() {

        movieModel.getNetworkInfo()
                .doOnNext(networkAvailable -> {
                    if(!networkAvailable){
                        resultObj = modelDao.getMoviesFromMoviesInDB();
                        if(resultObj!=null){
                            view.refreshData(resultObj.getResults());
                        }
                    }
                })
                .filter(networkAvailable ->networkAvailable == true)
                .flatMap(networkAvailable -> movieModel.getGetMoviesService().getTopRatedMovieList(queryMapGetMovie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        view.setSwipeToRefreshOffRefreshing();
                        view.refreshData(resultObj.getResults());
                        modelDao.storeMoviesInDB(resultObj);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(resultObj == null){
                            resultObj = modelDao.getMoviesFromMoviesInDB();
                            view.refreshData(resultObj.getResults());
                        }
                        view.getShowErrorMessage();
                        view.setSwipeToRefreshOffRefreshing();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getNowPlayingMovieData() {
        movieModel.getNetworkInfo()
                .doOnNext(networkAvailable -> {
                    if(!networkAvailable){
                        resultObj = modelDao.getMoviesFromMoviesInDB();
                        if(resultObj!=null){
                            view.refreshData(resultObj.getResults());
                        }
                    }
                })
                .filter(networkAvailable ->networkAvailable == true)
                .flatMap(networkAvailable -> movieModel.getGetMoviesService().getNoePlayingMovielist(queryMapGetMovie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        view.setSwipeToRefreshOffRefreshing();
                        view.refreshData(resultObj.getResults());
                        modelDao.storeMoviesInDB(resultObj);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(resultObj == null){
                            resultObj = modelDao.getMoviesFromMoviesInDB();
                            view.refreshData(resultObj.getResults());
                        }
                        view.getShowErrorMessage();
                        view.setSwipeToRefreshOffRefreshing();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getPopularMovieData() {
        movieModel.getNetworkInfo()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        resultObj = modelDao.getMoviesFromMoviesInDB();
                        if (resultObj != null) {
                            view.refreshData(resultObj.getResults());
                        }
                    }
                })
                .filter(networkAvailable -> networkAvailable == true)
                .flatMap(networkAvailable -> movieModel.getGetMoviesService().getPopularMovieList(queryMapGetMovie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        view.setSwipeToRefreshOffRefreshing();
                        view.refreshData(resultObj.getResults());
                        modelDao.storeMoviesInDB(resultObj);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (resultObj == null) {
                            resultObj = modelDao.getMoviesFromMoviesInDB();
                            view.refreshData(resultObj.getResults());
                        }
                        view.getShowErrorMessage();
                        view.setSwipeToRefreshOffRefreshing();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public List<Movies> getPopularTvShowsData() {

        movieModel.getNetworkInfo()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        resultObj = modelDao.getMoviesFromMoviesInDB();
                        if (resultObj != null) {
                            view.refreshData(resultObj.getResults());
                        }
                    }
                })
                .filter(networkAvailable -> networkAvailable == true)
                .flatMap(networkAvailable -> movieModel.getGetMoviesService().getPopularTvShows(queryMapGetMovie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        view.setSwipeToRefreshOffRefreshing();
                        view.refreshData(resultObj.getResults());
                        modelDao.storeMoviesInDB(resultObj);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (resultObj == null) {
                            resultObj = modelDao.getMoviesFromMoviesInDB();
                            view.refreshData(resultObj.getResults());
                        }
                        view.getShowErrorMessage();
                        view.setSwipeToRefreshOffRefreshing();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });

        return null;
    }

    @Override
    public void getTopRatedTvShowsData() {

        movieModel.getNetworkInfo()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        resultObj = modelDao.getMoviesFromMoviesInDB();
                        if (resultObj != null) {
                            view.refreshData(resultObj.getResults());
                        }
                    }
                })
                .filter(networkAvailable -> networkAvailable == true)
                .flatMap(networkAvailable -> movieModel.getGetMoviesService().getTopRatedTvShow(queryMapGetMovie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        view.setSwipeToRefreshOffRefreshing();
                        view.refreshData(resultObj.getResults());
                        modelDao.storeMoviesInDB(resultObj);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (resultObj == null) {
                            resultObj = modelDao.getMoviesFromMoviesInDB();
                            view.refreshData(resultObj.getResults());
                        }
                        view.getShowErrorMessage();
                        view.setSwipeToRefreshOffRefreshing();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getSearchForMovies(final String query) {

        queryMapSearchMovie.put("query", query);
        if(query.length()>0){

        movieModel.getNetworkInfo()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        resultSearchObject = modelDao.getMoviesFromMoviesInDB();
                        if (resultObj != null) {
                            view.refreshData(resultSearchObject.getResults());
                        }
                    }
                })
                .filter(networkAvailable -> networkAvailable == true)
                .flatMap(networkAvailable -> movieModel.getSearchMoviesService().getSearchedMovieList(queryMapSearchMovie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onCompleted() {
                        view.setSwipeToRefreshOffRefreshing();
                        view.refreshData(resultSearchObject.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (resultSearchObject == null) {
                            resultSearchObject = modelDao.getMoviesFromMoviesInDB();
                            view.refreshData(resultSearchObject.getResults());
                        }
                        view.getShowErrorMessage();
                        view.setSwipeToRefreshOffRefreshing();
                    }

                    @Override
                    public void onNext(Result result) {
                        resultSearchObject = result;
                    }
                });
        }else{
            view.refreshData(resultObj.getResults());
        }

    }

    @Override
    public Result getResult() {
        return resultObj;
    }

    @Override
    public void setResult(Result result) {
        resultObj = result;

    }

    @Override
    public List<Movies> getMovieLst() {
        if(resultObj!=null)
         return resultObj.getResults();

        return null;
    }

    @Override
    public void setMovieLst(ArrayList<Movies> movieLst) {
        resultObj.setResults(movieLst);
    }

    @Override
    public void onResumePresenter() {
        if(getMovieLst() == null)
            getUpcomingMovieData();
        else {
            view.refreshData(getMovieLst());
        }
    }

}
