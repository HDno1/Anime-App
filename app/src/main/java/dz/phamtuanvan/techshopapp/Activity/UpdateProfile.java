package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import dz.phamtuanvan.techshopapp.Model.User;
import dz.phamtuanvan.techshopapp.R;

public class UpdateProfile extends AppCompatActivity {
    private EditText etUpdateFullName,etUpdatePhone, etUpdateSex,etUpdateAge,etUpdateBirthDay;
    private TextView tvUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        etUpdateFullName = findViewById(R.id.tvUpdateUserfullName);
        etUpdatePhone = findViewById(R.id.tvUpdateUserPhone);
        etUpdateSex = findViewById(R.id.tvUpdateUserSex);
        etUpdateAge = findViewById(R.id.tvUpdateUserAge);
        tvUpdate = findViewById(R.id.tvUpdateProfile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarUpdateProfile);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.tvTitleToolbar);
        textView.setText("Cập Nhật Thông Tin Cá Nhân");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
        loadHintProfile();
    }

    private void loadHintProfile() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference myRef1 = database.getReference("User");
        myRef1.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = new User();
                user1.setFullName(snapshot.child("fullName").getValue().toString());
                user1.setAge(snapshot.child("age").getValue().toString());
                user1.setSex(snapshot.child("Sex").getValue().toString());
                user1.setPhone(snapshot.child("Phone").getValue().toString());
                etUpdateFullName.setText(user1.getFullName());
                etUpdatePhone.setText(user1.getPhone());
                etUpdateSex.setText(user1.getSex());
                etUpdateAge.setText(user1.getAge());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void updateProfile() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String name = etUpdateFullName.getText().toString();
        String phone = etUpdatePhone.getText().toString();
        String sex = etUpdateSex.getText().toString();
        String age = etUpdateAge.getText().toString();

//        if (name ==null || phone == null || sex == null || age == null){
//            name = etUpdateFullName.getHint().toString();
//            phone = etUpdatePhone.getHint().toString();
//            sex = etUpdateSex.getHint().toString();
//            age = etUpdateAge.getHint().toString();
//            DatabaseReference myRef = database.getReference("User").child(user.getUid());
//            Map<String, String> updateProfile = new HashMap<>();
//            updateProfile.put("Phone", phone);
//            updateProfile.put("Sex", sex);
//            updateProfile.put("age", age);
//            updateProfile.put("fullName", name);
//
//            myRef.setValue(updateProfile, new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                    Toast.makeText(UpdateProfile.this,"Cập nhật thông tin cá nhân thành công", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        else {
//
//        }
        DatabaseReference myRef = database.getReference("User").child(user.getUid());
        Map<String, String> updateProfile = new HashMap<>();
        updateProfile.put("Phone", phone);
        updateProfile.put("Sex", sex);
        updateProfile.put("age", age);
        updateProfile.put("fullName", name);

        myRef.setValue(updateProfile, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(UpdateProfile.this,"Cập nhật thông tin cá nhân thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}