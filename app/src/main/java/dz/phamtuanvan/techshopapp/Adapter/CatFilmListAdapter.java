package dz.phamtuanvan.techshopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dz.phamtuanvan.techshopapp.Model.CatFilmList;
import dz.phamtuanvan.techshopapp.R;

public class CatFilmListAdapter extends RecyclerView.Adapter<CatFilmListAdapter.ViewHolder> {
    Context context;
    ArrayList<CatFilmList> list;

    public CatFilmListAdapter(Context context, ArrayList<CatFilmList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CatFilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cat_film_list ,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CatFilmListAdapter.ViewHolder holder, int position) {
        CatFilmList catFilmList = list.get(position);
        holder.tv_name_catFilm.setText(catFilmList.getNameCatFilm());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_catFilm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_catFilm = itemView.findViewById(R.id.tv_name_catFilm);
        }
    }
}
