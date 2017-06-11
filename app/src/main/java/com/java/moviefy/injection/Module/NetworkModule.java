package com.java.moviefy.injection.Module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.java.moviefy.network.service.GetMoviesService;
import com.java.moviefy.network.service.SearchMoviesService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by swapnil on 6/8/17.
 */


@Module
public class NetworkModule {

    private  final String url;

    public NetworkModule(String url){
        this.url = url;
    }

    @Provides @Singleton
    public Retrofit getRetrofit(Context context){
        return new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
    }

    @Provides @Singleton
    public GetMoviesService getMoviesService(Retrofit retrofit){
        return retrofit.create(GetMoviesService.class);
    }

    @Provides @Singleton
    public SearchMoviesService getSearchMoviesService(Retrofit retrofit){
        return retrofit.create(SearchMoviesService.class);
    }

    @Provides
    public  Observable<Boolean> isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo !=null)
            return Observable.just(activeNetworkInfo.isAvailable());
        else
          return  Observable.just(false);
    }

}
