package com.iqbal.mymoviecatalogue4.favorite.favtv;


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
public class FavoriteTVFragment extends Fragment {
    FavoriteTVAdapter adapter;
    ArrayList<Favorite> favorites;
    RecyclerView recyclerView;
    private AppDatabase appDatabase;

    public FavoriteTVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "favoritedb").build();
        favorites = new ArrayList<>();
        adapter = new FavoriteTVAdapter();
        recyclerView = view.findViewById(R.id.rv_fav_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        getFavTv();
    }

    @SuppressLint("StaticFieldLeak")
    private void getFavTv() {
        new AsyncTask<Void, Void, ArrayList<Favorite>>() {

            @Override
            protected ArrayList<Favorite> doInBackground(Void... voids) {
                favorites = (ArrayList<Favorite>) appDatabase.favoriteDAO().selectTvShowFavorite();
                return favorites;
            }

            @Override
            protected void onPostExecute(ArrayList<Favorite> favor) {
                adapter.setTvFav(favor);
                adapter.notifyDataSetChanged();
                Log.d("movieData", String.valueOf(favorites));
                recyclerView.setAdapter(adapter);
            }
        }.execute();
    }
}
