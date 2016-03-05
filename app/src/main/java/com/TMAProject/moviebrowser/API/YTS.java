package com.TMAProject.moviebrowser.API;

import com.TMAProject.moviebrowser.API.models.ListData;
import com.TMAProject.moviebrowser.API.models.response.ListResponse;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tarekkma on 2/21/16.
 */
public interface YTS {
    @GET("list_movies.json?limit=30")
    Call<ListResponse> getListByPage(@Query("page")int p);

    @GET("movie_details.json?with_images=true&with_cast=true")
    Call<ListResponse> getMovieDetiles(@Query("movie_id")int id);
    
}
