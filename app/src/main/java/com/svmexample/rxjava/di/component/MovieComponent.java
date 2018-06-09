package com.svmexample.rxjava.di.component;

import com.svmexample.rxjava.MainActivity;
import com.svmexample.rxjava.di.module.MovieModule;
import com.svmexample.rxjava.di.scope.PerActivity;

import dagger.Component;

@Component(modules = MovieModule.class,dependencies = ApiComponent.class)
@PerActivity
public interface MovieComponent {

    void inject(MainActivity activity);
}
