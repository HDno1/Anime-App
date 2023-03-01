package dz.phamtuanvan.techshopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dz.phamtuanvan.techshopapp.Fragment.HomeFragment;
import dz.phamtuanvan.techshopapp.Model.MovieCategory;
import dz.phamtuanvan.techshopapp.R;

public class MovieCategoryAdapter extends RecyclerView.Adapter<MovieCategoryAdapter.CategoryViewHolder>{
    private Context context;
    private ArrayList<MovieCategory> movieCategoryArrayList;
    public String uid;
    public MovieCategoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieCategory> list){
        this.movieCategoryArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        MovieCategory movieCategory = movieCategoryArrayList.get(position);
        holder.tvNameCategory.setText(movieCategory.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rvMovie.setLayoutManager(linearLayoutManager);
        MovieAdapter movieAdapter = new MovieAdapter();
        movieAdapter.setData(context,movieCategory.getMovies());

        holder.rvMovie.setAdapter(movieAdapter);
    }
    private void onClickGotoMovie(MovieCategory movie) {

        Intent intent = new Intent(context, HomeFragment.class);
        intent.putExtra("key",uid);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return movieCategoryArrayList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameCategory;
        private RecyclerView rvMovie;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
            rvMovie = itemView.findViewById(R.id.rv_movie);

        }
    }
}
