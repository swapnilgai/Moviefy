package com.java.moviefy.injection.Module;

import android.content.Context;

import com.java.moviefy.adapter.LandingPageRecycleAdapter;
import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/8/17.
 */

@Module(includes = {ContextModule.class, ImageModule.class})
public class RecycleAdapterModule {

    @Provides @ActivityScope
    public LandingPageRecycleAdapter getLandingPageRecycleAdapter(Context context, Picasso picasso){
        return new LandingPageRecycleAdapter(context, picasso);
    }

}
