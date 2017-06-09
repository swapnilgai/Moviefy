package com.java.moviefy.injection.Module;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.java.moviefy.database.helper.DatabaseHelper;
import com.java.moviefy.injection.CustomeScope.ActivityScope;

import java.sql.SQLException;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/9/17.
 */

@Module(includes = {ContextModule.class})
public class DatabaseModule {

    @Provides @ActivityScope
   public  DatabaseHelper getDatabaseHelper(Context context){

       return  new DatabaseHelper(context);
   }

    @Provides @ActivityScope
   public Dao getUserDAO(DatabaseHelper databaseHelper){
       try {
           return   databaseHelper.getUserDao();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
   }
}
