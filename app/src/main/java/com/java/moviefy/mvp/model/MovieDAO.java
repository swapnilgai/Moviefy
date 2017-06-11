package com.java.moviefy.mvp.model;

/**
 * Created by swapnil on 6/10/17.
 */

public interface MovieDAO {

    public Result getMoviesFromMoviesInDB();

    public void storeMoviesInDB(Result result);
}
