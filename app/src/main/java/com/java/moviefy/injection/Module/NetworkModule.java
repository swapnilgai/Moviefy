package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by swapnil on 6/8/17.
 */


@Module
public class NetworkModule {

    private  final String url;

    public NetworkModule(String url){
        this.url = url;
    }


    @Provides @ActivityScope
    public Retrofit getRetrofit(Context context){
        return new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
    }

    @Provides @ActivityScope
    public GetMoviesService getMoviesService(Retrofit retrofit){
        return retrofit.create(GetMoviesService.class);
    }

    @Provides @ActivityScope
    public SearchMoviesService getSearchMoviesService(Retrofit retrofit){
        return retrofit.create(SearchMoviesService.class);
    }
}
