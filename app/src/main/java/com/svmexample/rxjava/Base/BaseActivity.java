package com.svmexample.rxjava.Base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.svmexample.rxjava.MoviesApplication;
import com.svmexample.rxjava.di.component.ApiComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract  class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;


    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
      unbinder =   ButterKnife.bind(this);

      onViewReady(savedInstanceState,getIntent());


    }
    @CallSuper
    protected  void onViewReady(Bundle savedInstanceState, Intent intent){
        resolveDependency();

    }

    protected void resolveDependency() {
    }

    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();

    }

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    protected void showDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected ApiComponent getApiComponent(){

        return  ((MoviesApplication)getApplication()).getApiComponent();
    }





}
