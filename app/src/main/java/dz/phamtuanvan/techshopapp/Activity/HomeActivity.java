package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dz.phamtuanvan.techshopapp.Fragment.AccountFragment;
import dz.phamtuanvan.techshopapp.Fragment.CartFragment;
import dz.phamtuanvan.techshopapp.Fragment.HomeFragment;
import dz.phamtuanvan.techshopapp.Fragment.NotificationFragment;
import dz.phamtuanvan.techshopapp.R;
import dz.phamtuanvan.techshopapp.SharedPreference.DataLocalManager;

public class HomeActivity extends AppCompatActivity {
    public static String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        loadFragment(new HomeFragment());
        Intent intent = getIntent();
        pass = intent.getStringExtra("pass");

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.myNotify:
                        loadFragment(new NotificationFragment());
                        break;

                    case R.id.myHome:
                        loadFragment(new HomeFragment());
                        break;

                    case R.id.myCart:
                        loadFragment(new CartFragment());
                        break;

                    case R.id.myAccount:
                        loadFragment(new AccountFragment());
                        break;
                }

                return true;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}