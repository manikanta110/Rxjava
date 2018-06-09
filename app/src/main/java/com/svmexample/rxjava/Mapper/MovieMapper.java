package com.svmexample.rxjava.Mapper;


import com.svmexample.rxjava.Mvp.model.Movie;
import com.svmexample.rxjava.Mvp.model.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieMapper {

    @Inject

    public MovieMapper() {
    }


    public List<Result> mapMovies(Movie response) {
        List<Result> cakeList = new ArrayList<>();

        if (response != null) {
            List<Result> responseCakes = response.getResults();
            if (responseCakes != null) {
                for (Result movie : responseCakes) {
                   Result myresult = new Result();
                   myresult.setAdult(movie.getAdult());
                   myresult.setBackdropPath(movie.getBackdropPath());
                   myresult.setGenreIds(movie.getGenreIds());
                   myresult.setId(movie.getId());
                   myresult.setOriginalLanguage(movie.getOriginalLanguage());
                   myresult.setOriginalTitle(movie.getOriginalTitle());
                   myresult.setPopularity(movie.getPopularity());
                   myresult.setOverview(movie.getOverview());
                   myresult.setReleaseDate(movie.getReleaseDate());
                   myresult.setTitle(movie.getTitle());
                   myresult.setPosterPath(movie.getPosterPath());
                   myresult.setVideo(movie.getVideo());
                   myresult.setVoteAverage(movie.getVoteAverage());
                   myresult.setVoteCount(movie.getVoteCount());

                    cakeList.add(myresult);
                }
            }
        }
        return cakeList;
    }




}
