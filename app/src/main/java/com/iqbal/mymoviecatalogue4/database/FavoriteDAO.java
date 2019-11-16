package com.iqbal.mymoviecatalogue4.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iqbal.mymoviecatalogue4.favorite.Favorite;

import java.util.List;

@Dao
public interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFavorite(Favorite favorite);

    @Query("SELECT * FROM tbFavorite WHERE isMovie = 1")
    List<Favorite> selectMovieFavorite();

    @Query("SELECT * FROM tbFavorite WHERE isMovie = 2")
    List<Favorite> selectTvShowFavorite();

    @Query("SELECT title FROM tbFavorite WHERE id = :id_fav")
    String checkIsFavorit(int id_fav);

    @Query("DELETE FROM tbFavorite WHERE id=:id")
    int deleteFavorite(int id);
}
