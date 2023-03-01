package dz.phamtuanvan.techshopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import dz.phamtuanvan.techshopapp.R;

public class WatchedFilm extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched_film);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarWatchedFilm);

        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.tvTitleToolbar);
        textView.setText("Phim đã xem");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}