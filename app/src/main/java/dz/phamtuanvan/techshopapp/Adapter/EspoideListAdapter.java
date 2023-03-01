package dz.phamtuanvan.techshopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dz.phamtuanvan.techshopapp.Activity.ProductDetail;
import dz.phamtuanvan.techshopapp.Activity.WatchMovieActivity;
import dz.phamtuanvan.techshopapp.Model.EspoideList;
import dz.phamtuanvan.techshopapp.Model.WatchedFilm;
import dz.phamtuanvan.techshopapp.R;
import dz.phamtuanvan.techshopapp.SharedPreference.DataLocalManager;

public class EspoideListAdapter extends RecyclerView.Adapter<EspoideListAdapter.ViewHolder> {
    Context context;
    ExoPlayer exoPlayer;
    ArrayList<EspoideList> espoideLists;
    public String epFilm,epFilm2;
    public boolean isClick;

    public EspoideListAdapter(Context context, ArrayList<EspoideList> espoideLists) {
        this.context = context;
        this.espoideLists = espoideLists;
    }

    @NonNull
    @Override
    public EspoideListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.config_product_item ,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull EspoideListAdapter.ViewHolder holder, int position) {
        EspoideList list = espoideLists.get(position);
        holder.nameEp.setText(list.getNameEspoide());
        Glide.with(context)
                .load(espoideLists.get(position).getImage())
                .into(holder.imgEp);
//        holder.layout_itemEspoideFilm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                epFilm = list.getNameEspoide();
//                isClick = true;
//                Toast.makeText(v.getContext(), "You clicked "+ epFilm,Toast.LENGTH_SHORT).show();
//                onClickGotoMovie(list);
//            }
//        });
    }

    private void onClickGotoMovie(EspoideList list) {
        Intent intent = new Intent(context, ProductDetail.class);
        intent.putExtra("epFilm",epFilm);
        intent.putExtra("isClick",isClick);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return espoideLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameEp;
        ImageView imgEp;
        LinearLayout layout_itemEspoideFilm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_itemEspoideFilm = itemView.findViewById(R.id.layout_itemEspoideFilm);
            nameEp = itemView.findViewById(R.id.tv_nameEp);
            imgEp = itemView.findViewById(R.id.img_Ep);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getPosition();
                    EspoideList list = espoideLists.get(pos);
                    epFilm2 = list.getNameEspoide();
                    epFilm = ProductDetail.uid;
//                    String nameFilm = ProductDetail.nameFilm;
//                    WatchedFilm watchedFilm = new WatchedFilm();
//                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference myRef = database.getReference("FavouriteList").child(user.getUid());
//                    myRef.child(epFilm).setValue(watchedFilm, new DatabaseReference.CompletionListener() {
//                        @Override
//                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                            Toast.makeText(context,"Đã thêm vào danh sách ưa thích",Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    Intent intent = new Intent(v.getContext(), WatchMovieActivity.class);
                    intent.putExtra("key1",epFilm2);
                    intent.putExtra("key2",epFilm);
                    context.startActivity(intent);
                }
            });
        }
    }
}
