package com.gmail.mynewsapp.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news_table")
    List<News> getAllNews();

    @Query("DELETE FROM news_table")
    void deleteAllNews();

    @Query("SELECT * FROM news_table WHERE id LIKE :id")
    News getNews(int id);

    @Insert
    void insertNews(News news);
}
