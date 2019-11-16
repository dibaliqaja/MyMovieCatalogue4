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
import com.iqbal.mymoviecatalogue4.model.Movies;

import java.util.ArrayList;

import static com.iqbal.mymoviecatalogue4.details.DetailsActivity.EXTRA_ID;
import static com.iqbal.mymoviecatalogue4.details.DetailsActivity.EXTRA_TYPE;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewViewModel> {
    private ArrayList<Movies> listM = new ArrayList<>();
    public void setData(ArrayList<Movies> i) {
        listM.clear();
        listM.addAll(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MoviesViewViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesViewViewModel holder, int position) {
        holder.bind(listM.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra(EXTRA_ID, listM.get(holder.getAdapterPosition()).getId());
                intent.putExtra(EXTRA_TYPE, true);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listM.size();
    }

    public class MoviesViewViewModel extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvTitle, tvDate, tvOverview;

        public MoviesViewViewModel(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }

        void bind(Movies movies) {
            String link = "https://image.tmdb.org/t/p/w185" + movies.getImage();
            tvTitle.setText(movies.getTitle());
            tvDate.setText(movies.getDate());
            tvOverview.setText(movies.getOverview());
            Glide.with(itemView.getContext())
                    .load(link)
                    .dontAnimate()
                    .into(imgPhoto);
        }
    }
}
