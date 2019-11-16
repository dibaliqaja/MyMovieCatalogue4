package com.iqbal.mymoviecatalogue4.details;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.iqbal.mymoviecatalogue4.R;
import com.iqbal.mymoviecatalogue4.database.AppDatabase;
import com.iqbal.mymoviecatalogue4.favorite.Favorite;

import java.util.ArrayList;

@SuppressLint("Registered")
public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_TYPE = "extra_type";
    public static final String EXTRA_ID = "extra_id";

    TextView tvTitle, tvRating, tvPopularity, tvOverview;
    ImageView imgPhoto;
    Favorite favorite;
    DetailsViewModel detailsViewModel;
    ProgressBar progressBar;
    boolean isMovie, isFavorite;
    private AppDatabase appDatabase;
    private Observer<ArrayList<Details>> getDetails = new Observer<ArrayList<Details>>() {
        @Override
        public void onChanged(ArrayList<Details> details) {
            if (details != null) {
                tvTitle.setText(details.get(0).getTitle());
                tvRating.setText(Double.toString(details.get(0).getRating()));
                tvPopularity.setText(Double.toString(details.get(0).getPopularity()));
                tvOverview.setText(details.get(0).getOverview());
                Glide.with(DetailsActivity.this)
                        .load(details.get(0).getImage())
                        .dontAnimate()
                        .into(imgPhoto);

                favorite = new Favorite();
                favorite.setId(details.get(0).getId());
                favorite.setTitle(details.get(0).getTitle());
                favorite.setPoster(details.get(0).getImage());
                favorite.setOverview(details.get(0).getOverview());
                if (isMovie) {
                    favorite.setIsMovie(1);
                } else {
                    favorite.setIsMovie(2);
                }
                showProgress(false);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        isMovie = getIntent().getBooleanExtra(EXTRA_TYPE, true);
        checkIsFavorite(id);
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.getDetails().observe(this, getDetails);
        detailsViewModel.setDetails(this, isMovie, id);
        showProgress(true);
    }

    private void init() {
        tvTitle = findViewById(R.id.tv_title);
        tvRating = findViewById(R.id.tv_rating);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvOverview = findViewById(R.id.tv_overview);
        progressBar = findViewById(R.id.progressBar);
        imgPhoto = findViewById(R.id.img_photo);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "favoritedb").build();
    }

    @SuppressLint("StaticFieldLeak")
    private void insertData(final Favorite favorite) {
        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                long stat = appDatabase.favoriteDAO().insertFavorite(favorite);
                Log.d("status_db", String.valueOf(stat));
                return stat;
            }

            @Override
            protected void onPostExecute(Long lo) {
                isFavorite = true;
                Toast.makeText(getApplicationContext(), "Berhasil Ditambahkan ke Favorite", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void checkIsFavorite(final int id) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                String checkIsFavorite = appDatabase.favoriteDAO().checkIsFavorit(id);
                isFavorite = checkIsFavorite != null;
                return isFavorite;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void deleteFavorite(final int id) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                int sta = appDatabase.favoriteDAO().deleteFavorite(id);
                return sta;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                isFavorite = false;
                Toast.makeText(getApplicationContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void showProgress(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
            if (isFavorite) {
                menu.getItem(0).setIcon(R.drawable.ic_favorite_black_24dp);
            }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_favorite) {
            if (isFavorite) {
                deleteFavorite(favorite.getId());
                item.setIcon(R.drawable.ic_favorite_border_black_24dp);
            } else {
                insertData(favorite);
                item.setIcon(R.drawable.ic_favorite_black_24dp);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
