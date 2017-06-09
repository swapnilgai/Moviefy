package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/8/17.
 */


@Module(includes = {ContextModule.class})
public class ImageModule {

    @Provides @ActivityScope
    public Picasso getPicasso(Context context){
      return new  Picasso.Builder(context).build();
    }
}
