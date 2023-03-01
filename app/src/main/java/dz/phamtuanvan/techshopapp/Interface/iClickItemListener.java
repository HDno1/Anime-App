package dz.phamtuanvan.techshopapp.Interface;

import android.view.View;
import android.widget.ImageView;

import dz.phamtuanvan.techshopapp.Model.Movie;

public interface iClickItemListener {

    void onClickItem(Movie movie, ImageView imageView);
}
