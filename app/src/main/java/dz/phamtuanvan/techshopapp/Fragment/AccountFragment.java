package dz.phamtuanvan.techshopapp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import dz.phamtuanvan.techshopapp.Activity.ChangePass;
import dz.phamtuanvan.techshopapp.Activity.FavoriteFilmActivity;
import dz.phamtuanvan.techshopapp.Activity.ProfileActivity;
import dz.phamtuanvan.techshopapp.Activity.UpdateProfile;
import dz.phamtuanvan.techshopapp.MainActivity;
import dz.phamtuanvan.techshopapp.R;

public class AccountFragment extends Fragment {
    CircleImageView avatar,contactPhone,contactFB,contactInstagram;
    String pass1;
    private TextView tvUserID,tvEmailAccount,tvLogOut,tvProfile,tvFavoriteFilm,tvChangePass,tvUpdateProfile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        tvEmailAccount = view.findViewById(R.id.tvEmailAccount);
        tvUserID = view.findViewById(R.id.tvUserId);
        tvLogOut = view.findViewById(R.id.tvLogOut);
        contactFB = view.findViewById(R.id.cImgContactFB);
        contactInstagram = view.findViewById(R.id.cImgContactInstagram);
        contactPhone = view.findViewById(R.id.cImgContactPhone);
        tvProfile = view.findViewById(R.id.tvProfile);
        tvFavoriteFilm = view.findViewById(R.id.tvFavoriteFilm);
        tvUpdateProfile = view.findViewById(R.id.UpdateProfile);
        tvChangePass = view.findViewById(R.id.ChangePassWord);
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePass.class);
                startActivity(intent);
            }
        });
        tvUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
            }
        });
        contactPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivity = new Intent (Intent.ACTION_DIAL, Uri.parse( "tel:0339864774"));
                startActivity(myActivity);
            }
        });
        contactFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivity = new Intent(Intent.ACTION_VIEW,   Uri.parse("https://www.facebook.com/CallMeZawn/"));
                startActivity(myActivity);
            }
        });
        contactInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivity = new Intent(Intent.ACTION_VIEW,   Uri.parse("https://www.instagram.com/callmezawn_0105/"));
                startActivity(myActivity);
            }
        });
        showUserInfo();
        toFavoriteFilm();
        return view;
    }

    private void toFavoriteFilm() {
        tvFavoriteFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavoriteFilmActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showUserInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        String id = user.getUid();
        Uri photoUrl =  user.getPhotoUrl();
        tvUserID.setText(id);
        tvEmailAccount.setText(email);
    }
}