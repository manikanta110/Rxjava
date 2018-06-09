package com.svmexample.rxjava.Base;

import com.svmexample.rxjava.Mvp.view.BaseView;
import com.svmexample.rxjava.Mvp.view.MainView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends BaseView> {

    @Inject protected  V mView;

    public V getmView() {
        return mView;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer){
      observable.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(observer);

    }



}
