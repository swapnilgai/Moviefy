package com.java.moviefy;

import com.java.moviefy.mvp.presenter.MoviePresenter;

import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Inject
    MoviePresenter moviePresenter;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkPopularMovies(){
        assertNotEquals(null,  moviePresenter);
    }

}