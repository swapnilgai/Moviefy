package com.java.moviefy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.java.moviefy.R;
import com.java.moviefy.entities.Movies;
import com.java.moviefy.utils.ArrayRecyclerAdapter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by swapnil on 6/8/17.
 */

public class LandingPageRecycleAdapter extends ArrayRecyclerAdapter<Movies, ViewHolder> {

    Picasso picasso;

    Context context;

    private final int VIEW_NO_DATA = 1;
    private final int VIEW_MOVIE_DATA = 0;
    private final int VIEW_NO_NETWORK = -1;

    public LandingPageRecycleAdapter(Context context, Picasso picasso){
        this.context =context;
        this.picasso = picasso;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DataResultHolder(LayoutInflater.from(context).inflate(R.layout.movie_adapter_recycleview, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(holder instanceof  DataResultHolder){
            Movies movies = getItem(position);

            // Setting movie image
                picasso.with(context)
                        .load("https://image.tmdb.org/t/p/w640_and_h360_bestv2"+movies.getPoster_path())
                        .fit()
                        .centerCrop()
                        .into(((DataResultHolder) holder).movieImage);

            // set movie title
            ((DataResultHolder)holder).movieTitle.setText(movies.getTitle());
            // set movie release date
            ((DataResultHolder) holder).movieDate.setText(movies.getRelease_date());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_MOVIE_DATA;

    }

    public class DataResultHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.movie_image)
        ImageView movieImage;

        @BindView(R.id.movie_date)
        TextView movieDate;

        @BindView(R.id.movie_title)
        TextView movieTitle;

        public DataResultHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }



}
