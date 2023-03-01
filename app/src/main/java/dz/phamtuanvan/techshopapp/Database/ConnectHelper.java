package dz.phamtuanvan.techshopapp.Database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectHelper {
    Connection con;
    String username, pass, ip, port, database;
    @SuppressLint("NewApi")
    public Connection connectionClass(){
        ip = "192.168.56.1";
        port = "1433";
        database = "DoAnDatabase";

        StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(pol);
        Connection connection = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String connectionURL="jdbc:jtds:sqlserver://192.168.56.1:1433/DoAnDatabase;user=ptv;password=123;";
            connection = DriverManager.getConnection(connectionURL);

        }
        catch (Exception e)
        {
            Log.e("Error",e.getMessage());
        }
        return connection;
    }
}
