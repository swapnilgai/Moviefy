package com.java.moviefy.injection.Module;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/8/17.
 */


@Module(includes = {ContextModule.class})
public class ImageModule {

    @Provides @Singleton
    public Picasso getPicasso(Context context){
      return new  Picasso.Builder(context).build();
    }
}
