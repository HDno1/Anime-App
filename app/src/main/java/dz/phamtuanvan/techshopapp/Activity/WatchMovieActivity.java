package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dz.phamtuanvan.techshopapp.R;

public class WatchMovieActivity extends AppCompatActivity {
    PlayerView playerView;
    ExoPlayer exoPlayer;
    ProgressBar progressBar;
    LinearLayout layout_video;
    ImageView bt_fullscreen, bt_lockscreen;
    boolean isFullScreen = false, isLock = false;
    public String uid,epFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movie);
        playerView = findViewById(R.id.player);
        layout_video = findViewById(R.id.layout_video);
        progressBar = findViewById(R.id.progress_bar);
        bt_fullscreen = findViewById(R.id.bt_full_screen);
        bt_lockscreen = findViewById(R.id.exo_lock);
        Intent intent = getIntent();
        uid = intent.getStringExtra("key2");
        epFilm = intent.getStringExtra("key1");
        fullScreen();
//        getTimeWatched();
    }


    private void fullScreen() {
        bt_fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFullScreen){
                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_fullscreen_exit));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
//                    ViewGroup.LayoutParams params = layout_video.getLayoutParams();
//                    params.height = 1080;
//
//                    layout_video.setLayoutParams(params);
                }else {
                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_fullscreen));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    ViewGroup.LayoutParams params = layout_video.getLayoutParams();
//                    params.height = 600;
//                    layout_video.setLayoutParams(params);
                }
                isFullScreen = !isFullScreen;
            }
        });
        bt_lockscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLock){
                    bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_lock));
                }
                else
                {
                    bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_outline_lock_open));
                }
                isLock = !isLock;
                lockScreen(isLock);
            }
        });
        exoPlayer = new ExoPlayer.Builder(this)
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build();
        playerView.setPlayer(exoPlayer);
        playerView.setKeepScreenOn(true);
        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);

                }else if (playbackState == Player.STATE_READY){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


            FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database1.getReference().child("Video");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    Uri uri = Uri.parse(snapshot.child(uid).child("ep").child(epFilm).child("link").getValue().toString());
                    MediaItem media = MediaItem.fromUri(uri);
                    exoPlayer.setMediaItem(media);
                    exoPlayer.prepare();
                    exoPlayer.play();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        if (isLock) return;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            bt_fullscreen.performClick();
        }
        else super.onBackPressed();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        exoPlayer.stop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        exoPlayer.release();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        exoPlayer.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    private void lockScreen(boolean lock) {
        LinearLayout sec_mid = findViewById(R.id.sec_controlvid1);
        LinearLayout sec_bottom = findViewById(R.id.sec_controlvid2);
        if (lock){
            sec_mid.setVisibility(View.INVISIBLE);
            sec_bottom.setVisibility(View.INVISIBLE);
        }
        else {
            sec_mid.setVisibility(View.VISIBLE);
            sec_bottom.setVisibility(View.VISIBLE);
        }
    }
}