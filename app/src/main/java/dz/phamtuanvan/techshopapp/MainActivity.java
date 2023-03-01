package dz.phamtuanvan.techshopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dz.phamtuanvan.techshopapp.Activity.HomeActivity;
import dz.phamtuanvan.techshopapp.Activity.RegisterActivity;
import dz.phamtuanvan.techshopapp.Fragment.AccountFragment;
import dz.phamtuanvan.techshopapp.Fragment.CartFragment;
import dz.phamtuanvan.techshopapp.Fragment.HomeFragment;
import dz.phamtuanvan.techshopapp.Fragment.NotificationFragment;
import dz.phamtuanvan.techshopapp.SharedPreference.DataLocalManager;

public class MainActivity extends AppCompatActivity {
    TextView noAccount;
    private String password;
    EditText inputEmail,inputPassword;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    TextView button;
    FirebaseUser firebaseUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        inputEmail = findViewById(R.id.inputEmail1);
        inputPassword = findViewById(R.id.inputPassword1);
        progressDialog = new ProgressDialog(this);
        button = findViewById(R.id.btnLG);
        noAccount = findViewById(R.id.noAccount);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });
    }
    private void PerforAuth() {
        String email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if (!email.matches(emailPattern))
        {
            inputEmail.setError("Vui lòng nhập lại Email");
        }else if (password.isEmpty() || password.length()< 6)
        {
            inputPassword.setError("Vui lòng nhập lại mật khẩu");
        } else {
            progressDialog.setMessage("Đang Đăng Nhập. Vui Lòng Chờ...");
            progressDialog.setTitle("Đăng Nhập");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            DataLocalManager.setPassWord(password);
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNext();
                        Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNext() {
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}