package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import dz.phamtuanvan.techshopapp.Adapter.CastFilmAdapter;
import dz.phamtuanvan.techshopapp.Adapter.CatFilmListAdapter;
import dz.phamtuanvan.techshopapp.Adapter.EspoideListAdapter;
import dz.phamtuanvan.techshopapp.Adapter.PhotoSliderAdapter;
import dz.phamtuanvan.techshopapp.Domain.PhotoSlider;
import dz.phamtuanvan.techshopapp.Model.CastFilmModel;
import dz.phamtuanvan.techshopapp.Model.CatFilmList;
import dz.phamtuanvan.techshopapp.Model.EspoideList;
import dz.phamtuanvan.techshopapp.Model.FavoriteFilm;
import dz.phamtuanvan.techshopapp.Model.PushFavoriteFilm;
import dz.phamtuanvan.techshopapp.R;
import dz.phamtuanvan.techshopapp.SharedPreference.DataLocalManager;
import me.relex.circleindicator.CircleIndicator;

public class ProductDetail extends AppCompatActivity {
    RecyclerView listEp,rvCatFilm,rvListCast;
    TextView tvNameFilm,tvDescribeFilm;
    private FirebaseUser user;
    ArrayList<EspoideList> espoideListArrayList;
    public static String uid,img_film,epFilm;
    ScrollView scrollView;
    ImageView img_Film,img_Film1, imgAddFavoriteFilm;
    String imageFilm;
    public static String nameFilm;
    boolean isTouch = false, isClick =false;
    PlayerView playerView;
    ExoPlayer exoPlayer;
    ProgressBar progressBar;
    LinearLayout layout_video;
    ImageView bt_fullscreen, bt_lockscreen;
    boolean isFullScreen = false, isLock = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        scrollView = findViewById(R.id.scrollView_1);
        imgAddFavoriteFilm = findViewById(R.id.imgAddFavoriteFilm);
        img_Film = findViewById(R.id.image_Film);
        img_Film1 = findViewById(R.id.image_Film1);
        rvListCast = findViewById(R.id.rv_list_Cast);
        rvCatFilm = findViewById(R.id.rv_category_film);
        tvNameFilm = findViewById(R.id.tvnameofFilm);
        listEp = findViewById(R.id.rv_list_Ep);
        tvDescribeFilm = findViewById(R.id.tv_describe_film);
        playerView = findViewById(R.id.player);
        layout_video = findViewById(R.id.layout_video);
        progressBar = findViewById(R.id.progress_bar);
        bt_fullscreen = findViewById(R.id.bt_full_screen);
        bt_lockscreen = findViewById(R.id.exo_lock);
        Intent intent = getIntent();
        uid = intent.getStringExtra("key");
        img_film = intent.getStringExtra("imgFilm");
        Glide.with(this)
            .load(img_film)
            .into(img_Film);

        espiedList();


        coverImage();
        catFilm();
        describeFilm();
        castFilm();
        nameFilm();
        nameFilm = tvNameFilm.getText().toString();
        imgAddFavoriteFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tvNameFilm.getText().toString();
                String imageCover = imageFilm;
                String imgSmall = img_film;
                PushFavoriteFilm pushFavoriteFilm = new PushFavoriteFilm(name,imageCover,imgSmall);
                addFavoriteFilm(pushFavoriteFilm);
            }
        });
//        fullScreen();
        epFilm = "1";
    }
//    private void fullScreen() {
//        bt_fullscreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isFullScreen){
//                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_fullscreen_exit));
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
////                    ViewGroup.LayoutParams params = layout_video.getLayoutParams();
////                    params.height = 1080;
////
////                    layout_video.setLayoutParams(params);
//                }else {
//                    bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_fullscreen));
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
////                    ViewGroup.LayoutParams params = layout_video.getLayoutParams();
////                    params.height = 600;
////                    layout_video.setLayoutParams(params);
//                }
//                isFullScreen = !isFullScreen;
//            }
//        });
//        bt_lockscreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isLock){
//                    bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_lock));
//                }
//                else
//                {
//                    bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_outline_lock_open));
//                }
//                isLock = !isLock;
//                lockScreen(isLock);
//            }
//        });
//        exoPlayer = new ExoPlayer.Builder(this)
//                .setSeekBackIncrementMs(5000)
//                .setSeekForwardIncrementMs(5000)
//                .build();
//        playerView.setPlayer(exoPlayer);
//        playerView.setKeepScreenOn(true);
//        exoPlayer.addListener(new Player.Listener() {
//            @Override
//            public void onPlaybackStateChanged(int playbackState) {
//                if (playbackState == Player.STATE_BUFFERING){
//                    progressBar.setVisibility(View.VISIBLE);
//
//                }else if (playbackState == Player.STATE_READY){
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//        });
//
//
//            FirebaseDatabase database1 = FirebaseDatabase.getInstance();
//        DatabaseReference myRef1 = database1.getReference().child("Video");
//        myRef1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                    Uri uri = Uri.parse(snapshot.child(uid).child("ep").child(epFilm = DataLocalManager.getPassWord()).child("link").getValue().toString());
//                    MediaItem media = MediaItem.fromUri(uri);
//                    exoPlayer.setMediaItem(media);
//                    exoPlayer.prepare();
//                    exoPlayer.play();
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (isLock) return;
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
//        {
//            bt_fullscreen.performClick();
//        }
//        else super.onBackPressed();
//    }
//    @Override
//    protected void onStop()
//    {
//        super.onStop();
//        exoPlayer.stop();
//    }
//
//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//        exoPlayer.release();
//    }
//
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        exoPlayer.pause();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//
//    }
//
//    private void lockScreen(boolean lock) {
//        LinearLayout sec_mid = findViewById(R.id.sec_controlvid1);
//        LinearLayout sec_bottom = findViewById(R.id.sec_controlvid2);
//        if (lock){
//            sec_mid.setVisibility(View.INVISIBLE);
//            sec_bottom.setVisibility(View.INVISIBLE);
//        }
//        else {
//            sec_mid.setVisibility(View.VISIBLE);
//            sec_bottom.setVisibility(View.VISIBLE);
//        }
//    }

    private void nameFilm() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Video").child(uid).child("name");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    tvNameFilm.setText(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    private void addFavoriteFilm(PushFavoriteFilm pushFavoriteFilm) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("FavouriteList").child(user.getUid());
        myRef.child(uid).setValue(pushFavoriteFilm, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(ProductDetail.this,"Đã thêm vào danh sách ưa thích",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void coverImage() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference().child("CoverImage").child(uid);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Glide.with(getApplicationContext())
                    .load(snapshot.getValue().toString())
                    .into(img_Film1);
                img_Film1.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_animation));
                imageFilm = snapshot.getValue().toString();

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

    private void castFilm() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rvListCast.setLayoutManager(linearLayoutManager);
        ArrayList<CastFilmModel> castFilmModelArrayList = new ArrayList<>();
        CastFilmAdapter castFilmAdapter = new CastFilmAdapter(this,castFilmModelArrayList);
        rvListCast.setAdapter(castFilmAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Cast").child(uid);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    CastFilmModel castFilmModel = new CastFilmModel();
                    castFilmModel.setNameCast(snapshot.getKey());

                    castFilmModel.setImage(snapshot.child("image").getValue().toString());


                    castFilmModelArrayList.add(castFilmModel);
                    castFilmAdapter.notifyDataSetChanged();

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


    private void describeFilm() {
        tvDescribeFilm.setText("Xem Thêm");
        tvDescribeFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTouch == true){
                    tvDescribeFilm.setText("Xem Thêm");
                    isTouch = false;
                }
                else if(isTouch == false){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference myRef = database.getReference("Video").child(uid).child("describe");
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        tvDescribeFilm.setText(snapshot.getValue().toString());
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
            isTouch = true;
                }

            }

        });

    }

    private void catFilm() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rvCatFilm.setLayoutManager(linearLayoutManager);
        ArrayList<CatFilmList> catFilmLists = new ArrayList<>();
        CatFilmListAdapter catFilmListAdapter = new CatFilmListAdapter(this,catFilmLists);
        rvCatFilm.setAdapter(catFilmListAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Video").child(uid).child("category");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    CatFilmList catFilmList = new CatFilmList();
                    catFilmList.setNameCatFilm(snapshot.getKey());
                    catFilmLists.add(catFilmList);
                    catFilmListAdapter.notifyDataSetChanged();

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

    private void espiedList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        listEp.setLayoutManager(linearLayoutManager);
        espoideListArrayList = new ArrayList<>();
        EspoideListAdapter espoideListAdapter = new EspoideListAdapter(this,espoideListArrayList);
        listEp.setAdapter(espoideListAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Video").child(uid).child("ep");
/*        espoideListArrayList.add(new EspoideList("https://cdn.animevietsub.pro/data/poster/2023/01/26/animevsub-LwVFjShv8d.png","duna"));
        espoideListArrayList.add(new EspoideList("https://cdn.animevietsub.pro/data/poster/2023/01/26/animevsub-LwVFjShv8d.png","duna"));
        espoideListArrayList.add(new EspoideList("https://cdn.animevietsub.pro/data/poster/2023/01/26/animevsub-LwVFjShv8d.png","duna"));

        espoideListAdapter.notifyDataSetChanged();*/
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                EspoideList list = new EspoideList();
                list.setNameEspoide(snapshot.getKey());
                list.setImage(snapshot.child("imgEspoide").getValue().toString());
                espoideListArrayList.add(list);
                espoideListAdapter.notifyDataSetChanged();

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

}