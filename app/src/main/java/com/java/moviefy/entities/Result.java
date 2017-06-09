package com.java.moviefy.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by swapnil on 6/8/17.
 */

@DatabaseTable
public class Result  implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private  int page;
    @DatabaseField
    private int total_results;
    @DatabaseField
    private  int total_pages;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<Movies> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public ArrayList<Movies> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movies> results) {
        this.results = results;
    }

}
