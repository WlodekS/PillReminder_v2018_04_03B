package com.pum2018.pillreminder_v2018_04_03;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;

import static android.widget.Toast.LENGTH_SHORT;



public class MainActivity extends AppCompatActivity {

    DataBaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating Manager of DataBase:
        DataBaseManager dbm = new DataBaseManager(this);

        //TEST - kontrolne wypisanie zawartości tabeli Medicines
        dbm.TEST_TypeTableMedicines();       //Write to LOG: Content of table

    }

    /**
     *
     * @param view
     */
    public void onStoreClick(View view){
        //Toast.makeText(this,"You pressed Store",LENGTH_SHORT).show();
        Intent storeIntent = new Intent(this,StoreActivity.class) ;
        startActivity(storeIntent);
    }

    /**
     *
     * @param view
     */
    public void onSettingsClick(View view){
        //Toast.makeText(this,"You pressed Settings", LENGTH_SHORT).show();
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    /**
     *
     * @param view
     */
    public void onReportClick(View view){
        //Toast.makeText(this,"You pressed Report", LENGTH_SHORT).show();
        Intent reportIntent = new Intent(this, com.pum2018.pillreminder_v2018_04_03.ReportActivity.class);
        startActivity(reportIntent);
    }

    /**
     *
     * @param view
     */
    public void onScheduleClick(View view){
        //Toast.makeText(this,"You pressed Schedule", LENGTH_SHORT).show();
        Intent scheduleIntent = new Intent(this,ScheduleActivity.class);
        startActivity(scheduleIntent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dbm.close();
        finish();
        System.exit(0);
    }
}
