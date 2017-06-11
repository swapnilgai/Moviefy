package com.java.moviefy.mvp.model;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by swapnil on 6/10/17.
 */

public class MovieDAOImple implements  MovieDAO{


    Dao userDao;

    public  MovieDAOImple (Dao userDao){
        this.userDao = userDao;
    }

    @Override
    public void storeMoviesInDB(Result result) {
        try {
            userDao.create(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Movies> getMoviesFromMoviesInDB() {
        try {
            List<Result> resultLst = userDao.queryForAll();
            resultLst.stream().findFirst().map(e->e.getResults());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
