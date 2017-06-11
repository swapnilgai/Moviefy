package com.java.moviefy;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.java.moviefy.adapter.LandingPageRecycleAdapter;
import com.java.moviefy.injection.Component.ContextComponent;
import com.java.moviefy.injection.Component.DaggerContextComponent;
import com.java.moviefy.injection.Module.ContextModule;
import com.java.moviefy.injection.Module.NetworkModule;
import com.java.moviefy.mvp.model.Movies;
import com.java.moviefy.mvp.presenter.MoviePresenter;
import com.java.moviefy.mvp.view.MainActivityView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



public class MainActivity extends AppCompatActivity  implements MainActivityView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.recycler_landing_page)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_landing_page)
    SwipeRefreshLayout swipeRefreshLayout;

    Unbinder unbinder;

    @Inject
    LandingPageRecycleAdapter adapter;

    @Inject
    MoviePresenter moviePresenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    String networkFlag;

    ContextComponent contextComponent;

    ActionBarDrawerToggle toggle;

    private final String baseUrl = "https://api.themoviedb.org/3/";


    private final String ERROR_MESSAGE = "Error, while loading data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        contextComponent = DaggerContextComponent.builder()
                            .contextModule(new ContextModule(this, this))
                            .networkModule(new NetworkModule(baseUrl))
                            .build();

        contextComponent.injectActivity(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setRefreshing(true);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.navigation_drawer_opened, R.string.navigation_drawer_closed);
        drawerLayout.setDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

        toggle.syncState();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getNetworkCallResultForOnSwipToRefresh();
            }
        });

        networkFlag = getString(R.string.top_rated_movies);
    }

    @Override
    public void setSwipeToRefreshOnRefreshing() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void setSwipeToRefreshOffRefreshing() {
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshData(List<Movies> moviesDataList) {
        if (adapter != null){
            adapter.clear();
            adapter.addAll(moviesDataList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getShowErrorMessage() {
        Toast.makeText(this, ERROR_MESSAGE, Toast.LENGTH_LONG);
    }

    @Override
    public void setNetworkFlag(String flag) {
        networkFlag = flag;
    }

    @Override
    public String getNetworkFlag() {
        return networkFlag;
    }

    public void getNetworkCallResultForOnSwipToRefresh(){

        switch (networkFlag){
            case "top_rated_movies":
                moviePresenter.getTopRatedMovieData();
                break;
            case "upcoming_movies":
                moviePresenter.getUpcomingMovieData();
                break;
            case "now_playing_movies":
                moviePresenter.getNowPlayingMovieData();
                break;
            case "popular_movies":
               moviePresenter.getPopularMovieData();
                break;
            case "popular_tv_shows":
                moviePresenter.getPopularTvShowsData();
                break;
            case "top_rated_tv_shows":
               moviePresenter.getPopularTvShowsData();
                break;
            default:
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        swipeRefreshLayout.setRefreshing(false);

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.top_rated:
                moviePresenter.getTopRatedMovieData();
                break;
            case R.id.upcoming:
                moviePresenter.getUpcomingMovieData();
                break;
            case R.id.now_playing:
                moviePresenter.getNowPlayingMovieData();
                break;
            case R.id.popular_movies:
                moviePresenter.getPopularMovieData();
                break;
            case R.id.popular_TV_shows:
                moviePresenter.getPopularTvShowsData();
                break;
            case R.id.top_rated_TV_shows:
                moviePresenter.getTopRatedTvShowsData();
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    moviePresenter.getSearchForMovies(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //Store activity state for rotation
        outState.putSerializable(getString(R.string.result_data_object), (Serializable) moviePresenter.getMovieLst());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        //Restore activity state on rotation
        moviePresenter.setMovieLst((ArrayList<Movies>) savedInstanceState.getSerializable(getString(R.string.result_data_object)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviePresenter.onResumePresenter();
    }
}
