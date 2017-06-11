package com.java.moviefy.mvp.model;

import java.util.List;

/**
 * Created by swapnil on 6/10/17.
 */

public interface MovieDAO {

    public List<Movies> getMoviesFromMoviesInDB();

    public void storeMoviesInDB(Result result);
}
