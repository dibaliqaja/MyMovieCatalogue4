package com.iqbal.mymoviecatalogue4.favorite.favmovies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iqbal.mymoviecatalogue4.R;
import com.iqbal.mymoviecatalogue4.details.DetailsActivity;
import com.iqbal.mymoviecatalogue4.favorite.Favorite;

import java.util.ArrayList;

import static com.iqbal.mymoviecatalogue4.details.DetailsActivity.EXTRA_ID;
import static com.iqbal.mymoviecatalogue4.details.DetailsActivity.EXTRA_TYPE;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteMoviesViewViewModel> {
    private ArrayList<Favorite> favM = new ArrayList<>();

    public void setFavorites(ArrayList<Favorite> fav) {
        favM.clear();
        favM.addAll(fav);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteMoviesViewViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new FavoriteMoviesViewViewModel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final FavoriteMoviesViewViewModel holder, int position) {
        holder.tvTitle.setText(favM.get(position).getTitle());
        holder.tvDate.setText(favM.get(position).getDate());
        holder.tvOverview.setText(favM.get(position).getOverview());
        Glide.with(holder.itemView.getContext())
                .load(favM.get(position).getPoster())
                .dontAnimate()
                .into(holder.imgPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra(EXTRA_ID, favM.get(holder.getAdapterPosition()).getId());
                Log.d("idMovie", String.valueOf(favM.get(holder.getAdapterPosition()).getId()));
                intent.putExtra(EXTRA_TYPE, true);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favM.size();
    }

    public class FavoriteMoviesViewViewModel extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvTitle, tvDate, tvOverview;
        public FavoriteMoviesViewViewModel(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
