package com.iqbal.mymoviecatalogue4;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iqbal.mymoviecatalogue4.favorite.FavoriteFragment;
import com.iqbal.mymoviecatalogue4.ui.movies.MoviesFragment;
import com.iqbal.mymoviecatalogue4.ui.tvshow.TVShowFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment fragment;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MoviesFragment();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(getResources().getString(R.string.movies));
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_lay, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_tvshow:
                    fragment = new TVShowFragment();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(getResources().getString(R.string.tv_show));
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_lay, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_favorite:
                    fragment = new FavoriteFragment();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(getResources().getString(R.string.favorite));
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_lay, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView viewnav = findViewById(R.id.nav_view);
        viewnav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            viewnav.setSelectedItemId(R.id.navigation_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.language_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
