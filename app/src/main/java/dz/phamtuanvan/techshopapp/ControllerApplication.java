package dz.phamtuanvan.techshopapp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class ControllerApplication extends Application {

    private FirebaseDatabase mFirebaseDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
    }
}
