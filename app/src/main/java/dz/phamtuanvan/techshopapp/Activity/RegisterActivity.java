package dz.phamtuanvan.techshopapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import dz.phamtuanvan.techshopapp.MainActivity;
import dz.phamtuanvan.techshopapp.R;


public class RegisterActivity extends AppCompatActivity {
    TextView haveAccount,tvRegister;
    EditText inputEmail,inputPassword, inputConfirmPass;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvRegister = findViewById(R.id.tvRegister);
        progressDialog = new ProgressDialog(this);
        inputConfirmPass = findViewById(R.id.inputConfirmPassword);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        haveAccount = findViewById(R.id.tvHaveAccount);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });

    }

    private void PerforAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPass = inputConfirmPass.getText().toString();

        if (!email.matches(emailPattern))
        {
            inputEmail.setError("Ch??a ????ng ?????nh d???ng Email. Vui l??ng nh???p l???i");
        }else if (password.isEmpty() || password.length()< 6)
        {
            inputPassword.setError("Vui l??ng nh???p l???i m???t kh???u");
        } else if (!confirmPass.equals(password)) {
            inputConfirmPass.setError("Password Not Match Both Feild");
        }else {
            progressDialog.setMessage("??ang ????ng K??...");
            progressDialog.setTitle("????ng k??");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNext();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("User").child(user.getUid());
                        Map<String, String> profile = new HashMap<>();
                        profile.put("Phone","");
                        profile.put("Sex","");
                        profile.put("age","");
                        profile.put("fullName","");
                        reference.setValue(profile, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(RegisterActivity.this,"Th??m th??ng tin th??nh c??ng",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(RegisterActivity.this,"????ng k?? t??i kho???n th??nh c??ng",Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNext() {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}