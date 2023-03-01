package dz.phamtuanvan.techshopapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dz.phamtuanvan.techshopapp.Activity.ProductDetail;
import dz.phamtuanvan.techshopapp.Adapter.MovieCategoryAdapter;
import dz.phamtuanvan.techshopapp.Adapter.PhotoSliderAdapter;
import dz.phamtuanvan.techshopapp.Domain.PhotoSlider;
import dz.phamtuanvan.techshopapp.Interface.MovieItemClickListener;
import dz.phamtuanvan.techshopapp.Model.Movie;
import dz.phamtuanvan.techshopapp.Model.MovieCategory;
import dz.phamtuanvan.techshopapp.R;
import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements MovieItemClickListener {
    RecyclerView recyclerViewList,listCategory;
    Connection connection;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    PhotoSliderAdapter photoSliderAdapter;
    List<PhotoSlider> photoSliderList;
    Timer timer;
    public String uid;
    public HomeFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circle_indicator);





        recyclerViewList = view.findViewById(R.id.recycleviewlist);


        imgSlide();
        autoSlide();
        recyclerViewCategory();


        return view;
    }

    private void autoSlide() {
//        if (photoSliderList == null || photoSliderList.isEmpty() || viewPager == null){
//            return;
//        }
        //Init timer
        if (timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int TotalItem = photoSliderList.size()-1;
                        if (currentItem < TotalItem){
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private void imgSlide() {

        photoSliderList = new ArrayList<>();
        photoSliderAdapter = new PhotoSliderAdapter(getContext(),photoSliderList);
        viewPager.setAdapter(photoSliderAdapter);
        circleIndicator.setViewPager(viewPager);
        photoSliderAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("MovieSlide");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PhotoSlider photoSlider1 = new PhotoSlider();
                photoSlider1.setResource(snapshot.getValue().toString());
                photoSlider1.setId(snapshot.getKey());
                photoSliderList.add(photoSlider1);
                photoSliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void recyclerViewCategory() {
        ArrayList<MovieCategory> movies = new ArrayList<>();
        ArrayList<Movie> movies1 = new ArrayList<>();
        ArrayList<Movie> movies2 = new ArrayList<>();
        ArrayList<Movie> movies3 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        MovieCategoryAdapter movieCategoryAdapter = new MovieCategoryAdapter(getActivity());

        movieCategoryAdapter.setData(movies);
        recyclerViewList.setAdapter(movieCategoryAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        movies.add(new MovieCategory("Top Anime Hay Nhất",movies1));
        movies.add(new MovieCategory("Xem nhiều nhất tháng 1/2023",movies2));
        movies.add(new MovieCategory("Xuyên qua thế giới khác",movies3));






        DatabaseReference myRef = database.getReference().child("CatMovie").child("1");
        DatabaseReference myRef2 = database.getReference().child("CatMovie").child("2");
        DatabaseReference myRef3 = database.getReference().child("CatMovie").child("3");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Movie movie2 = new Movie();
                movie2.setImage(snapshot.child("image").getValue().toString());
                movie2.setId(snapshot.getKey());
                movies1.add(movie2);
                movieCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Movie movie2 = new Movie();
                movie2.setImage(snapshot.child("image").getValue().toString());
                movie2.setId(snapshot.getKey());
                movies2.add(movie2);
                movieCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Movie movie3 = new Movie();
                movie3.setImage(snapshot.child("image").getValue().toString());
                movie3.setId(snapshot.getKey());
                movies3.add(movie3);
                movieCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClickItem(Movie movie, ImageView imageView) {
        Intent intent = new Intent(getActivity(), ProductDetail.class);
        intent.putExtra("key",movie.getId());
        intent.putExtra("imgUrl",movie.getImage());
        startActivity(intent);
    }
}