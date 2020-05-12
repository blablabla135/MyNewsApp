package com.gmail.mynewsapp.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.gmail.mynewsapp.model.Repository;
import com.gmail.mynewsapp.model.room.MyDataBase;
import com.gmail.mynewsapp.model.room.NewsDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class DataModule {

    @Singleton
    @Provides
    public MyDataBase provideMyDataBase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                MyDataBase.class, "my_database").fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public NewsDao provideNewsDao(MyDataBase myDataBase) {
        return myDataBase.newsDao();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    public Repository repository(Retrofit retrofit, NewsDao newsDao) {
        return new Repository(retrofit, newsDao);
    }


}
