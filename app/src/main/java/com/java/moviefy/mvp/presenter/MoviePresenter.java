package com.java.moviefy.mvp.presenter;

import com.java.moviefy.mvp.model.Movies;
import com.java.moviefy.mvp.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnil on 6/10/17.
 */

public interface MoviePresenter {

    public void getUpcomingMovieData();

    public void getTopRatedMovieData();

    public void getNowPlayingMovieData();

    public void getPopularMovieData();

    public void getPopularTvShowsData();

    public void getTopRatedTvShowsData();

    public void getSearchForMovies(String query);

    public Result getResult();

    public void setResult(Result result);

    public List<Movies> getMovieLst();

    public void setMovieLst(ArrayList<Movies> movieLst);

    public void onResumePresenter();

}
