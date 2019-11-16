package com.iqbal.mymoviecatalogue4.favorite.favmovies;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iqbal.mymoviecatalogue4.R;
import com.iqbal.mymoviecatalogue4.database.AppDatabase;
import com.iqbal.mymoviecatalogue4.favorite.Favorite;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMoviesFragment extends Fragment {
    private FavoriteMoviesAdapter adapter;
    private ArrayList<Favorite> favorites;
    private RecyclerView recyclerView;
    private AppDatabase db;

    public FavoriteMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "favoritedb").build();
        favorites = new ArrayList<>();
        adapter = new FavoriteMoviesAdapter();
        recyclerView = view.findViewById(R.id.rv_fav_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        getFavoriteMovies();
    }

    @SuppressLint("StaticFieldLeak")
    private void getFavoriteMovies() {
        new AsyncTask<Void, Void, ArrayList<Favorite>>() {

            @Override
            protected ArrayList<Favorite> doInBackground(Void... voids) {
                favorites = (ArrayList<Favorite>) db.favoriteDAO().selectMovieFavorite();
                return favorites;
            }

            @Override
            protected void onPostExecute(ArrayList<Favorite> fav) {
                adapter.setFavorites(fav);
                adapter.notifyDataSetChanged();
                Log.d("movieData", String.valueOf(favorites));
                recyclerView.setAdapter(adapter);
            }
        }.execute();
    }
}
