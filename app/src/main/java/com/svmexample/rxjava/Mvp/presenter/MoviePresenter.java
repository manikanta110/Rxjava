package com.svmexample.rxjava.Mvp.presenter;

import com.svmexample.rxjava.Api.Service;
import com.svmexample.rxjava.Mvp.model.Movie;
import com.svmexample.rxjava.Mvp.model.Result;
import com.svmexample.rxjava.Base.BasePresenter;
import com.svmexample.rxjava.Mapper.MovieMapper;
import com.svmexample.rxjava.Mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MoviePresenter extends BasePresenter<MainView>implements Observer<Movie> {

    @Inject
    Service service;
    @Inject
    MovieMapper movieMapper;

    @Inject public MoviePresenter() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Movie movie) {
        List<Result> results = movieMapper.mapMovies(movie);
        getmView().onClearMovies();
        getmView().onMoviesLoaded(results);


    }

    @Override
    public void onError(Throwable e) {
        getmView().onHideDialog();
        getmView().onShowToast("Loading failed  "+e.getMessage());

    }

    @Override
    public void onComplete() {
        getmView().onHideDialog();
        getmView().onShowToast("Movies Loading Complete");


    }

    public void getMovies() {
        getmView().onShowDialog("Loading cakes....");
        Observable<Movie> cakesResponseObservable = service.getpopularmovies("81c4047a8486904dd6cf0787b4b47dc9",1);
        subscribe(cakesResponseObservable, this);
    }
}
