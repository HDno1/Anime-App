package dz.phamtuanvan.techshopapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import dz.phamtuanvan.techshopapp.Activity.ProductDetail;
import dz.phamtuanvan.techshopapp.R;
import dz.phamtuanvan.techshopapp.Model.FavoriteFilm;

public class FavoriteFilmAdapter extends RecyclerView.Adapter<FavoriteFilmAdapter.ViewHolder> {
    Context context;
    List<FavoriteFilm> favoriteFilmList;

    public FavoriteFilmAdapter(Context context, List<FavoriteFilm> favoriteFilmList) {
        this.context = context;
        this.favoriteFilmList = favoriteFilmList;
    }

    @NonNull
    @Override
    public FavoriteFilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_favorite_film ,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFilmAdapter.ViewHolder holder, int position) {
        FavoriteFilm favoriteFilm = favoriteFilmList.get(position);
        holder.tvFavoFilm.setText(favoriteFilm.getName());
        Glide.with(context)
            .load(favoriteFilm.getImage())
            .into(holder.imgFavoFilm);
    }

    @Override
    public int getItemCount() {
        return favoriteFilmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFavoFilm;
        TextView tvFavoFilm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavoFilm = itemView.findViewById(R.id.img_favorite_film_item);
            tvFavoFilm = itemView.findViewById(R.id.name_favorite_film_item);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getPosition();
                    FavoriteFilm favoriteFilm = favoriteFilmList.get(pos);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Alert!!")
                            .setMessage("Bạn muốn xóa phim này?")
                            .setCancelable(true)
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    DatabaseReference myRef = database.getReference("FavouriteList").child(user.getUid()).child(favoriteFilm.getId());
                                    myRef.removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            Toast.makeText(context,"Đã xóa",Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    favoriteFilmList.remove(pos);
                                    notifyItemRemoved(pos);
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getPosition();
                    FavoriteFilm favoriteFilm = favoriteFilmList.get(pos);
                    String id = favoriteFilm.getId();
                    String img = favoriteFilm.getImageSmall();
                    Intent intent = new Intent(v.getContext(), ProductDetail.class);
                    intent.putExtra("key",id);
                    intent.putExtra("imgFilm",img);
                    context.startActivity(intent);
                }
            });
        }
    }
}
