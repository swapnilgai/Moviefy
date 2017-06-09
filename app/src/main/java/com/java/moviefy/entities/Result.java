package com.java.moviefy.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by swapnil on 6/8/17.
 */

public class Result implements Serializable {

    private  int page;
    private int total_results;
    private  int total_pages;
    private  List<Movies> results;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movies> getResults() {
        return results;
    }

    public void setResults(List<Movies> results) {
        this.results = results;
    }

}
