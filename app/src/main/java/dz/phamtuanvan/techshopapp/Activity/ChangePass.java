package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dz.phamtuanvan.techshopapp.MainActivity;
import dz.phamtuanvan.techshopapp.R;
import dz.phamtuanvan.techshopapp.SharedPreference.DataLocalManager;

public class ChangePass extends AppCompatActivity {
    private FirebaseUser user;
    private String pass;
    ProgressDialog progressDialog;
    EditText etNewPass,etConfirmNewPass,etOldPass;
    TextView tvChangePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        etNewPass = findViewById(R.id.inputPasswordNew);
        progressDialog = new ProgressDialog(this);
        etOldPass = findViewById(R.id.inputPasswordOld);
        etConfirmNewPass = findViewById(R.id.inputConfirmPasswordNew);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarChangePass);
        tvChangePass = findViewById(R.id.tvChangePass);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.tvTitleToolbar);
        textView.setText("Đổi Mật Khẩu");
        user = FirebaseAuth.getInstance().getCurrentUser();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        pass = DataLocalManager.getPassWord();
        tvChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePass();
            }
        });



    }

    private void changePass() {
        String passwordNew = etNewPass.getText().toString();
        String confirmPassNew = etConfirmNewPass.getText().toString();
        String passWordOld = etOldPass.getText().toString();
        if (passWordOld.isEmpty()||!passWordOld.equals(pass)){
            etOldPass.setError("Bạn chưa nhập hoặc nhập sai mật khẩu, Vui lòng nhập lại");
        }
        else if (passwordNew.isEmpty() || passwordNew.length()< 6)
        {
            etNewPass.setError("Vui lòng nhập lại mật khảu");
        } else if (!confirmPassNew.equals(passwordNew)) {
            etConfirmNewPass.setError("Mật khẩu xác nhận lại chưa đúng. Vui lòng nhập lại");
        }else {
            progressDialog.setMessage("Đang Đổi Mật Khẩu...");
            progressDialog.setTitle("Đổi Mật Khẩu");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
            user1.updatePassword(passwordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        signOut();
                        Toast.makeText(ChangePass.this,"Đổi mật khẩu thành công. Vui lòng đăng nhập lại với mật khẩu mới",Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }else {
                        progressDialog.dismiss();
                        reAuthenticate();
                        Toast.makeText(ChangePass.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void reAuthenticate(){
        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(user1.getEmail(),pass);

        user1.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            changePass();
                        }else {

                        }
                    }
                });
    }
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ChangePass.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}