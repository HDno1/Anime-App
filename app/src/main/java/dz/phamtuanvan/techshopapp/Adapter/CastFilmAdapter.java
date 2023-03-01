package dz.phamtuanvan.techshopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dz.phamtuanvan.techshopapp.Model.CastFilmModel;
import dz.phamtuanvan.techshopapp.R;

public class CastFilmAdapter extends RecyclerView.Adapter<CastFilmAdapter.ViewHolder>{
    Context context;
    ArrayList<CastFilmModel> CastList;

    public CastFilmAdapter(Context context, ArrayList<CastFilmModel> castList) {
        this.context = context;
        CastList = castList;
    }

    @NonNull
    @Override
    public CastFilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cast_film ,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CastFilmAdapter.ViewHolder holder, int position) {
        CastFilmModel castFilmModel = CastList.get(position);
        holder.castName.setText(castFilmModel.getNameCast());

            Glide.with(context)
            .load(castFilmModel.getImage())
            .into(holder.imgCast);

    }

    @Override
    public int getItemCount() {
        return CastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView castName;
        CircleImageView imgCast;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            castName = itemView.findViewById(R.id.tv_name_cast);
            imgCast = itemView.findViewById(R.id.img_cast_film);
        }
    }
}
