package com.iqbal.mymoviecatalogue4.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.iqbal.mymoviecatalogue4.favorite.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteDAO favoriteDAO();
}
