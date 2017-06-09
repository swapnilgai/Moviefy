package com.java.moviefy.injection.Component;

import com.java.moviefy.injection.CustomeScope.ActivityScope;
import com.java.moviefy.injection.Module.ContextModule;

import dagger.Component;

/**
 * Created by swapnil on 6/9/17.
 */

@ActivityScope
@Component(dependencies = ContextModule.class)
public interface SearchActivityComponent {

}
