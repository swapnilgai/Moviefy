package com.java.moviefy.mvp.presenter;

import com.java.moviefy.mvp.model.MovieDAO;
import com.java.moviefy.mvp.model.Movies;
import com.java.moviefy.mvp.model.Result;
import com.java.moviefy.mvp.view.MainActivityView;
import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by swapnil on 6/10/17.
 */

public class MoviePresenterImple implements MoviePresenter {

    //network Service, database DAO

    GetMoviesService getMoviesService;

    SearchMoviesService searchMoviesService;

    MainActivityView view;

    Result resultObj;

    Result resultSearchObject;

    MovieDAO modelDao;

    Map<String, String> queryMapGetMovie;

    Map<String, String> queryMapSearchMovie;

    public MoviePresenterImple(GetMoviesService getMoviesService, SearchMoviesService searchMoviesService,
                               MainActivityView view, Map<String, String> queryMapGetMovie
            , Map<String, String> queryMapSearchMovie, MovieDAO modelDao) {
        this.getMoviesService = getMoviesService;
        this.searchMoviesService = searchMoviesService;
        this.view = view;
        this.queryMapGetMovie = queryMapGetMovie;
        this.queryMapSearchMovie = queryMapSearchMovie;
        this.modelDao = modelDao;
    }

    @Override
    public void getUpcomingMovieData() {

        getMoviesService.getUpcomingMovieList(queryMapGetMovie)
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
                        view.refreshData(modelDao.getMoviesFromMoviesInDB());
                        view.getShowErrorMessage();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getTopRatedMovieData() {

        getMoviesService.getTopRatedMovieList(queryMapGetMovie)
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
                        view.refreshData(modelDao.getMoviesFromMoviesInDB());
                        view.getShowErrorMessage();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });

    }

    @Override
    public void getNowPlayingMovieData() {
        getMoviesService.getNoePlayingMovielist(queryMapGetMovie)
                .subscribeOn(Schedulers.io())
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
                        view.refreshData(modelDao.getMoviesFromMoviesInDB());
                        view.getShowErrorMessage();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getPopularMovieData() {
        getMoviesService.getPopularMovieList(queryMapGetMovie)
                .subscribeOn(Schedulers.io())
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
                        view.refreshData(modelDao.getMoviesFromMoviesInDB());
                        view.getShowErrorMessage();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getPopularTvShowsData() {
        getMoviesService.getPopularTvShows(queryMapGetMovie)
                .subscribeOn(Schedulers.io())
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
                        view.refreshData(modelDao.getMoviesFromMoviesInDB());
                        view.getShowErrorMessage();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getTopRatedTvShowsData() {
        getMoviesService.getTopRatedTvShow(queryMapGetMovie)
                .subscribeOn(Schedulers.io())
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
                        view.refreshData(modelDao.getMoviesFromMoviesInDB());
                        view.getShowErrorMessage();
                    }

                    @Override
                    public void onNext(Result result) {
                        setResult(result);
                    }
                });
    }

    @Override
    public void getSearchForMovies(final String query) {

        if(query.length()>0) {

            queryMapSearchMovie.entrySet().stream();


            searchMoviesService.getSearchedMovieList(queryMapSearchMovie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onCompleted() {
                            view.setSwipeToRefreshOffRefreshing();
                            view.refreshData(resultSearchObject.getResults());
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.getShowErrorMessage();
                        }

                        @Override
                        public void onNext(Result result) {
                            resultSearchObject = result;
                        }
                    });
        } else{
                view.setSwipeToRefreshOffRefreshing();
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
            getPopularTvShowsData();
        else {
            view.refreshData(getMovieLst());
        }
    }
}
