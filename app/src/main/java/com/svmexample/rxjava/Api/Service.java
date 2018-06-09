package com.svmexample.rxjava.Api;

import com.svmexample.rxjava.Mvp.model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular")
    Observable<Movie> getpopularmovies(@Query("api_key") String api, @Query("page") int page);
}
