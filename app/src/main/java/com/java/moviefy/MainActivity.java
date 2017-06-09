package com.java.moviefy;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.java.moviefy.adapter.LandingPageRecycleAdapter;
import com.java.moviefy.entities.Movies;
import com.java.moviefy.entities.Result;
import com.java.moviefy.injection.Component.ContextComponent;
import com.java.moviefy.injection.Component.DaggerContextComponent;
import com.java.moviefy.injection.Module.ContextModule;
import com.java.moviefy.injection.Module.NetworkModule;
import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.recycler_landing_page)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_landing_page)
    SwipeRefreshLayout swipeRefreshLayout;

    Unbinder unbinder;

    @Inject
    GetMoviesService getMoviesService;

    @Inject
    SearchMoviesService searchMoviesService;

    @Inject
    LandingPageRecycleAdapter adapter;

    @Inject
    Retrofit retrofit;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ContextComponent contextComponent;

    private final String baseUrl = "https://api.themoviedb.org/3/";

    private List<Movies> moviesDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        contextComponent = DaggerContextComponent.builder()
                            .contextModule(new ContextModule(this))
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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.navigation_drawer_opened, R.string.navigation_drawer_closed);
        drawerLayout.setDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

        toggle.syncState();

        getMoviesNetworkCall();


    }


    public void getMoviesNetworkCall(){



        getMoviesService.getMovieList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Result>() {
                            @Override
                            public void onCompleted() {
                                refreshData(moviesDataList);
                                swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("Error ", "While fetching data from server");
                                swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onNext(Result result) {
                                moviesDataList = result.getResults();
                            }


                        });

    }


    @UiThread
    private void refreshData(List<Movies> moviesDataList){

        if (adapter != null){
            adapter.clear();
            adapter.addAll(moviesDataList);
            adapter.notifyDataSetChanged();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.top_rated) {
            // Handle the home action
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    protected void onStop() {
        super.onStop();

        // Resources released on activity closed
        unbinder.unbind();
    }
}
