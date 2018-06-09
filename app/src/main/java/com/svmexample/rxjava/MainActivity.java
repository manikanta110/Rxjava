package com.svmexample.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.svmexample.rxjava.Mvp.model.Result;
import com.svmexample.rxjava.Base.BaseActivity;
import com.svmexample.rxjava.Mvp.presenter.MoviePresenter;
import com.svmexample.rxjava.Mvp.view.MainView;
import com.svmexample.rxjava.Utils.NetworkUtils;
import com.svmexample.rxjava.di.component.DaggerMovieComponent;
import com.svmexample.rxjava.di.module.MovieModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.main_recycler)RecyclerView recyclerView;
    @Inject
    MoviePresenter mPresenter;

     PaginationAdapter adapter;




    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initlist();
        loadMovies();
    }

    private void loadMovies() {

        if(NetworkUtils.isNetAvailable(this)) {
            mPresenter.getMovies();
        } else {
           onShowToast("No Network");
        }

    }

    private void initlist() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new PaginationAdapter(getLayoutInflater());
        adapter.setCakeClickListener(mCakeClickListener);
        recyclerView.setAdapter(adapter);


    }


    @Override
    protected void resolveDependency() {
        super.resolveDependency();
        DaggerMovieComponent.builder()
                .apiComponent(getApiComponent())
                .movieModule(new MovieModule(this))
                .build().inject(this);



    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onMoviesLoaded(List<Result> results) {

    adapter.addCakes(results);


    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);


    }

    @Override
    public void onHideDialog() {
        hideDialog();

    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onClearMovies() {
        adapter.clearCakes();

    }

    private PaginationAdapter.OnMovieClickListener mCakeClickListener = new PaginationAdapter.OnMovieClickListener() {
        @Override
        public void onClick(View v, Result result, int position) {

            Toast.makeText(MainActivity.this, "You Clicked "+result.getOriginalTitle(), Toast.LENGTH_SHORT).show();

        }

       /* @Override
        public void onClick(View v, Cake cake, int position) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.CAKE, cake);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "cakeImageAnimation");
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }*/
    };
}
