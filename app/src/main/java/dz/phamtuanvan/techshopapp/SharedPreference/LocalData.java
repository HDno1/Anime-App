package dz.phamtuanvan.techshopapp.SharedPreference;

import android.app.Application;

public class LocalData extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
