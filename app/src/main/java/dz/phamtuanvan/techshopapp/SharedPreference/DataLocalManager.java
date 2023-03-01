package dz.phamtuanvan.techshopapp.SharedPreference;

import android.content.Context;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static DataLocalManager instance;
    private MySharedPreference mySharedPreference;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreference = new MySharedPreference(context);

    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();

        }
        return instance;
    }

    public static void setPassWord(String passWord){
        DataLocalManager.getInstance().mySharedPreference.putStringValue(PREF_FIRST_INSTALL,passWord);
    }
    public static String getPassWord(){
        return DataLocalManager.getInstance().mySharedPreference.getStringValue(PREF_FIRST_INSTALL);
    }
    public static void setEspoide(String passWord){
        DataLocalManager.getInstance().mySharedPreference.putStringValue(PREF_FIRST_INSTALL,passWord);
    }
    public static String getEspoide(){
        return DataLocalManager.getInstance().mySharedPreference.getStringValue(PREF_FIRST_INSTALL);
    }
}
