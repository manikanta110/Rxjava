package com.svmexample.rxjava.Mvp.view;

import com.svmexample.rxjava.Mvp.model.Result;

import java.util.List;

public interface MainView extends BaseView{

    void onMoviesLoaded(List<Result> cakes);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearMovies();


}
