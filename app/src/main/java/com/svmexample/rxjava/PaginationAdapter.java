package com.svmexample.rxjava;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.svmexample.rxjava.Mvp.model.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.MovieVH> {
    private static final String TAG = "PaginationAdapter";
    private LayoutInflater mLayoutInflater;

    public PaginationAdapter(LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    List<Result> resultList = new ArrayList<>();




    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new MovieVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVH holder, int position) {

        holder.bind(resultList.get(position));

    }


    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public Result getItem(int position) {
        return resultList.get(position);
    }


    public void addCakes(List<Result> results) {
        resultList.addAll(results);
        notifyDataSetChanged();
    }

    public void clearCakes() {
        resultList.clear();
        notifyDataSetChanged();
    }


    class MovieVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_title)
        TextView mMovieTitle;
        @BindView(R.id.movie_desc)
        TextView mMovieDesc;
        @BindView(R.id.movie_year)
        TextView mYear; // displays "year | language"
        @BindView(R.id.movie_poster)
        ImageView mPosterImg;
        @BindView(R.id.movie_progress)
        ProgressBar mProgress;

        private Context mContext;
        private Result result;

        public MovieVH(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);

        }

        public void bind(Result results) {

            result = results;


            mMovieTitle.setText(result.getTitle());

            String date = result.getReleaseDate().substring(0, 4)  // we want the year only
                    + " | "
                    + result.getOriginalLanguage().toUpperCase();


            mYear.setText(date);
            mMovieDesc.setText(result.getOverview());


            final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w150";

            String url = BASE_URL_IMG+result.getPosterPath();

            Log.d(TAG, "onBindViewHolder: " + url);


            Glide.with(mContext)
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            mProgress.setVisibility(View.GONE);

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            mProgress.setVisibility(View.GONE);

                            return false;
                        }
                    })
                    .into(mPosterImg);


        }

        @Override
        public void onClick(View v) {
            if (mCakeClickListener != null) {
                mCakeClickListener.onClick(mPosterImg, result, getAdapterPosition());
            }
        }
    }

    public void setCakeClickListener(PaginationAdapter.OnMovieClickListener listener) {
        mCakeClickListener = listener;
    }

    private PaginationAdapter.OnMovieClickListener mCakeClickListener;

    public interface OnMovieClickListener {

        void onClick(View v, Result result, int position);
    }
}







