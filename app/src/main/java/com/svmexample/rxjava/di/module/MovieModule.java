package com.svmexample.rxjava.di.module;

import com.svmexample.rxjava.Api.Service;
import com.svmexample.rxjava.Mvp.view.MainView;
import com.svmexample.rxjava.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MovieModule {

    private MainView view;


    public MovieModule(MainView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    Service provideService(Retrofit retrofit){
        return retrofit.create(Service.class);
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return view;
    }
}
