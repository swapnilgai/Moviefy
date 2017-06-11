package com.java.moviefy.mvp.view;

import com.java.moviefy.mvp.model.Movies;

import java.util.List;

/**
 * Created by swapnil on 6/10/17.
 */

public interface MainActivityView {

    public void setSwipeToRefreshOnRefreshing();

    public void setSwipeToRefreshOffRefreshing();

    public void refreshData(List<Movies> moviesDataList);

    public void getShowErrorMessage();

    public void setNetworkFlag(String flag);

    public String getNetworkFlag();



}
