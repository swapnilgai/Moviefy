package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.injection.CustomeScope.SearchActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/10/17.
 */

@Module
public class ImageSearchModule {

    @Provides
    @SearchActivityScope
    Picasso getPicasso(Context context){
        return new  Picasso.Builder(context).build();
    }
}
