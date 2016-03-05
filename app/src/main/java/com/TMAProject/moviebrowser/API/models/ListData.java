package com.TMAProject.moviebrowser.API.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarekkma on 2/21/16.
 */
public class ListData {
    @SerializedName("movie_count")
    @Expose
    private int count;
    @SerializedName("page_number")
    @Expose
    private int page_num;
    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("movies")
    @Expose
    private List<Movies> movies = new ArrayList<Movies>();
    @SerializedName("movie")
    @Expose
    private Movies movie;

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }
}
