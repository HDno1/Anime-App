package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dz.phamtuanvan.techshopapp.Adapter.FavoriteFilmAdapter;
import dz.phamtuanvan.techshopapp.Model.FavoriteFilm;
import dz.phamtuanvan.techshopapp.R;

public class FavoriteFilmActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_film);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFavoriteFilm);

        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.tvTitleToolbar);
        textView.setText("Danh sách phim yêu thích");
        user = FirebaseAuth.getInstance().getCurrentUser();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        recyclerView = findViewById(R.id.rv_favorite_film);
        getFavoFilm();
    }

    private void getFavoFilm() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<FavoriteFilm> favoriteFilmArrayList = new ArrayList<>();
        FavoriteFilmAdapter favoriteFilmAdapter = new FavoriteFilmAdapter(this,favoriteFilmArrayList);
        recyclerView.setAdapter(favoriteFilmAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("FavouriteList").child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    FavoriteFilm favoriteFilm = new FavoriteFilm();
                    favoriteFilm.setId(dataSnapshot.getKey());
                    favoriteFilm.setImage(dataSnapshot.child("imageCover").getValue().toString());
                    favoriteFilm.setName(dataSnapshot.child("name").getValue().toString());
                    favoriteFilm.setImageSmall(dataSnapshot.child("imgSmall").getValue().toString());
                    favoriteFilmArrayList.add(favoriteFilm);
                    favoriteFilmAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}