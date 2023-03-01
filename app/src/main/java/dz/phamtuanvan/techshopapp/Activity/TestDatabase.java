package dz.phamtuanvan.techshopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dz.phamtuanvan.techshopapp.Database.ConnectHelper;
import dz.phamtuanvan.techshopapp.R;

public class TestDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

    }

    Connection connection;
    String conResult="";

    public void DisplayDatabase(View v) throws SQLException {
        EditText Id = findViewById(R.id.etId);
        TextView tx1 = findViewById(R.id.txName);
        TextView tx2 = findViewById(R.id.txPic);

        ConnectHelper conhc = new ConnectHelper();
        connection = conhc.connectionClass();
        if (connection != null)
        {
            String q="Select * from CatProduct where Id= '"+Id.getText().toString()+"'";
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(q);

            while (resultSet.next())
            {
                tx1.setText(resultSet.getString(2));
                tx2.setText(resultSet.getString(3));
            }
        }
        else {
            conResult= "Check connection";
        }
    }
}