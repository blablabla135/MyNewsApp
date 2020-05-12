package com.gmail.mynewsapp.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract NewsDao newsDao();
}
