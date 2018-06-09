package com.svmexample.rxjava.di.component;


import android.content.Context;

import com.svmexample.rxjava.di.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = ApiModule.class)
@Singleton
public interface ApiComponent {

    Retrofit exposeRetrofit();

    Context exposeContext();
}
