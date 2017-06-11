package com.java.moviefy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.java.moviefy.injection.Component.SearchActivityComponent;
import com.java.moviefy.mvp.model.Movies;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationActivity extends Activity {

    private final String MOVIE_OBJECT = "MOVIE_OBJECT";
    private Movies movies;

    SearchActivityComponent searchActivityComponent;

    @BindView(R.id.movie_image)
    ImageView imageView;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.vote)
    TextView vote;

    @BindView(R.id.overview)
    TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        movies  = (Movies) getIntent().getSerializableExtra(MOVIE_OBJECT);

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w640_and_h360_bestv2"+movies.getPoster_path())
                .into(imageView);

        if(movies.getName()!=null)
            name.setText(movies.getName());
        else
            name.setText(movies.getTitle());

        vote.setText("Votes : "+movies.getVote_count());

        overview.setText(movies.getOverview());
    }
}
