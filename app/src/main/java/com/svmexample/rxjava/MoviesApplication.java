package com.svmexample.rxjava;

import android.app.Application;

import com.svmexample.rxjava.di.component.ApiComponent;
import com.svmexample.rxjava.di.component.DaggerApiComponent;
import com.svmexample.rxjava.di.module.ApiModule;

public class MoviesApplication extends Application {

    ApiComponent apiComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        apiComponent = DaggerApiComponent.builder()
                       .apiModule(new ApiModule("https://api.themoviedb.org/3/",this))
                        .build();


    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
