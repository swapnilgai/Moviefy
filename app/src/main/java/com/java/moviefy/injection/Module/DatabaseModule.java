package com.java.moviefy.injection.Module;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.java.moviefy.database.helper.DatabaseHelper;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by swapnil on 6/9/17.
 */

@Module(includes = {ContextModule.class})
public class DatabaseModule {

    @Provides @Singleton
   public  DatabaseHelper getDatabaseHelper(Context context){

       return  new DatabaseHelper(context);
   }

    @Provides @Singleton
   public Dao getUserDAO(DatabaseHelper databaseHelper){
       try {
           return   databaseHelper.getUserDao();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
   }
}
