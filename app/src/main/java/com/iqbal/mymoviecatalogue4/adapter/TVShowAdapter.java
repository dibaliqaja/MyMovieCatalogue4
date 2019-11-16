package com.iqbal.mymoviecatalogue4.adapter;

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
import com.iqbal.mymoviecatalogue4.model.TVShow;

import java.util.ArrayList;

import static com.iqbal.mymoviecatalogue4.details.DetailsActivity.EXTRA_ID;
import static com.iqbal.mymoviecatalogue4.details.DetailsActivity.EXTRA_TYPE;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewViewModel> {
    ArrayList<TVShow> listTV = new ArrayList<>();

    public void setData(ArrayList<TVShow> i) {
        listTV.clear();
        listTV.addAll(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new TVShowViewViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TVShowViewViewModel holder, int position) {
       holder.bind(listTV.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra(EXTRA_ID, listTV.get(holder.getAdapterPosition()).getId());
                intent.putExtra(EXTRA_TYPE, false);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTV.size();
    }

    public class TVShowViewViewModel extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvOverview;
        ImageView imgPhoto;

        public TVShowViewViewModel(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }

        void bind (TVShow tvShow) {
            String link = "https://image.tmdb.org/t/p/w185" + tvShow.getPhoto();
            tvName.setText(tvShow.getName());
            tvDate.setText(tvShow.getFirst_air_date());
            tvOverview.setText(tvShow.getOverview());
            Glide.with(itemView.getContext())
                    .load(link)
                    .dontAnimate()
                    .into(imgPhoto);
        }
    }
}
