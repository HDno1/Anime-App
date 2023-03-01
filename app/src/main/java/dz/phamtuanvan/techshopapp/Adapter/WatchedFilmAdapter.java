package dz.phamtuanvan.techshopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dz.phamtuanvan.techshopapp.Model.WatchedFilm;
import dz.phamtuanvan.techshopapp.R;

public class WatchedFilmAdapter extends RecyclerView.Adapter<WatchedFilmAdapter.ViewHolder> {
    Context context;
    List<WatchedFilm> watchedFilmList;

    public WatchedFilmAdapter(Context context, List<WatchedFilm> watchedFilmList) {
        this.context = context;
        this.watchedFilmList = watchedFilmList;
    }

    @NonNull
    @Override
    public WatchedFilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_watched_film,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchedFilmAdapter.ViewHolder holder, int position) {
        WatchedFilm watchedFilm = watchedFilmList.get(position);
        holder.tvNameWatchedFilm.setText(watchedFilm.getName());
        holder.tvNameEPWatched.setText(watchedFilm.getNameEp());
        Glide.with(context)
            .load(watchedFilm.getImage())
            .into(holder.imgWatchedFilm);
    }

    @Override
    public int getItemCount() {
        return watchedFilmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgWatchedFilm;
        TextView tvNameWatchedFilm,tvNameEPWatched;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWatchedFilm = itemView.findViewById(R.id.img_watched_film_item);
            tvNameWatchedFilm = itemView.findViewById(R.id.nameWatchedFilm);
            tvNameEPWatched = itemView.findViewById(R.id.nameEPwatched);
        }
    }
}
