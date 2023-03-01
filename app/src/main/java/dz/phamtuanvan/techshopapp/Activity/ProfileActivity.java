package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dz.phamtuanvan.techshopapp.Model.User;
import dz.phamtuanvan.techshopapp.R;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private Toolbar toolbar;
    private DatabaseReference reference;
    private String userId, name, age, sex;
    TextView tvUserEmail, tvUserfullName, tvUserAge, tvUserSex, tvProfileId, tvUserPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserfullName = findViewById(R.id.tvUserfullName);
        tvUserAge = findViewById(R.id.tvUserAge);
        tvUserSex = findViewById(R.id.tvUserSex);
        tvProfileId = findViewById(R.id.tvUserProfileId);
        tvUserPhone = findViewById(R.id.tvUserPhone);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();
        tvUserEmail.setText(user.getEmail());
        tvProfileId.setText(userId);
        reference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = new User();
                user1.setFullName(snapshot.child("fullName").getValue().toString());
                user1.setAge(snapshot.child("age").getValue().toString());
                user1.setSex(snapshot.child("Sex").getValue().toString());
                user1.setPhone(snapshot.child("Phone").getValue().toString());
                if (user1.getFullName().equals("")){
                    tvUserfullName.setText("Chưa có thông tin");
                }else {
                    tvUserfullName.setText(user1.getFullName());
                }
                if (user1.getAge().equals("")){
                    tvUserAge.setText("Chưa có thông tin");
                }
                else {
                    tvUserAge.setText(user1.getAge());
                }
                if (user1.getPhone().equals("")){
                    tvUserPhone.setText("Chưa có thông tin");
                }
                else {
                    tvUserPhone.setText(user1.getPhone());
                }
                if (user1.getSex().equals("")){
                    tvUserSex.setText("Chưa có thông tin");
                }
                else {
                    tvUserSex.setText(user1.getSex());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbarProfile);

        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.tvTitleToolbar);
        textView.setText("Thông tin tài khoản");

        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }
}