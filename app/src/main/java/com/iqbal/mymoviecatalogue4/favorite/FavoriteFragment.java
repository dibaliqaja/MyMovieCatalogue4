package com.iqbal.mymoviecatalogue4.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.iqbal.mymoviecatalogue4.R;
import com.iqbal.mymoviecatalogue4.favorite.favmovies.FavoriteMoviesFragment;
import com.iqbal.mymoviecatalogue4.favorite.favtv.FavoriteTVFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.tablay_fav);
        ViewPager viewPager = view.findViewById(R.id.viewpager_fav);

        ViewPagerFavoriteAdapter adapter = new ViewPagerFavoriteAdapter(getChildFragmentManager());
        adapter.addFragment(new FavoriteMoviesFragment(), getResources().getString(R.string.movies));
        adapter.addFragment(new FavoriteTVFragment(), getResources().getString(R.string.tv_show));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
