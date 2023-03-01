package dz.phamtuanvan.techshopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import dz.phamtuanvan.techshopapp.Activity.ProductDetail;
import dz.phamtuanvan.techshopapp.Domain.PhotoSlider;
import dz.phamtuanvan.techshopapp.R;

public class PhotoSliderAdapter extends PagerAdapter {
    Context context;
    List<PhotoSlider> photoSliderList;
    public String idMovieSlide;

    public PhotoSliderAdapter(Context context, List<PhotoSlider> photoSliderList) {
        this.context = context;
        this.photoSliderList = photoSliderList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imageView = view.findViewById(R.id.imgPhotoSLider);
        LinearLayout layout_itemPhoto = view.findViewById(R.id.layout_itemPhoto);
        PhotoSlider photoSlider = photoSliderList.get(position);
        if (photoSlider != null){
            Glide.with(context)
                    .load(photoSlider.getResource())
                    .into(imageView);
        }
        layout_itemPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idMovieSlide = photoSlider.getId();
                onClickGotoMovie(photoSlider);
            }
        });
        //Add view to viewgroup
        container.addView(view);
        return view;
    }

    private void onClickGotoMovie(PhotoSlider photoSlider) {
        Intent intent = new Intent(context, ProductDetail.class);
        intent.putExtra("key",idMovieSlide);
        context.startActivity(intent);
    }

    @Override
    public int getCount() {
        if (photoSliderList != null){
            return photoSliderList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
