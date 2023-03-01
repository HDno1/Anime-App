package dz.phamtuanvan.techshopapp.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.C;

import java.util.ArrayList;

import dz.phamtuanvan.techshopapp.Activity.ProductDetail;
import dz.phamtuanvan.techshopapp.Fragment.HomeFragment;
import dz.phamtuanvan.techshopapp.Interface.MovieItemClickListener;
import dz.phamtuanvan.techshopapp.MainActivity;
import dz.phamtuanvan.techshopapp.Model.Movie;
import dz.phamtuanvan.techshopapp.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private ArrayList<Movie> mMovie;
    Context Context;
    public String uid;
    public void setData(Context context,ArrayList<Movie> list){
        Context = context;
        this.mMovie = list;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMovie.get(position);

        Glide.with(Context)
            .load(movie.getImage())
            .into(holder.imageMovie);
//        holder.txt_test.setText(movie.getId());

//        holder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uid = movie.getId();
////                onClickGotoMovie(movie);
//                Intent intent = new Intent(Context, ProductDetail.class);
//        intent.putExtra("key",uid);
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Context,,"sharedName");
//        Context.startActivity(intent/*, options.toBundle()*/);
//
//            }
//        });

    }

    private void onClickGotoMovie(Movie movie) {


    }

    @Override
    public int getItemCount() {
        return mMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageMovie;
        private TextView txt_test;
        private CardView layout;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMovie = itemView.findViewById(R.id.img_movie);
            layout = itemView.findViewById(R.id.layout_itemmovie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getPosition();
                    Movie list = mMovie.get(pos);
                    uid = list.getId();
                    Intent intent = new Intent(v.getContext(), ProductDetail.class);
                    intent.putExtra("key",uid);
                    intent.putExtra("imgFilm",list.getImage());
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) Context,imageMovie,"sharedName");
//                    intent.putExtra("key2",epFilm);
                    Context.startActivity(intent, options.toBundle());
                }
            });
        }
    }
}
