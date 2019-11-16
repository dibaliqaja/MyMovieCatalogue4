package com.iqbal.mymoviecatalogue4.favorite.favtv;

import android.content.Intent;
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

public class FavoriteTVAdapter extends RecyclerView.Adapter<FavoriteTVAdapter.FavoriteTVViewViewModel> {
    private ArrayList<Favorite> tvFav = new ArrayList<>();

    public void setTvFav(ArrayList<Favorite> i) {
        tvFav.clear();
        tvFav.addAll(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteTVViewViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new FavoriteTVViewViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteTVViewViewModel holder, int position) {
        holder.tvName.setText(tvFav.get(position).getTitle());
        holder.tvDate.setText(tvFav.get(position).getDate());
        holder.tvOverview.setText(tvFav.get(position).getOverview());
        Glide.with(holder.itemView.getContext())
                .load(tvFav.get(position).getPoster())
                .into(holder.imgPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra(EXTRA_ID, tvFav.get(holder.getAdapterPosition()).getId());
                intent.putExtra(EXTRA_TYPE, false);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvFav.size();
    }

    public class FavoriteTVViewViewModel extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvOverview;
        ImageView imgPhoto;

        public FavoriteTVViewViewModel(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
