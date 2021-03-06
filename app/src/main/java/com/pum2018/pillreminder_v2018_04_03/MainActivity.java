package com.pum2018.pillreminder_v2018_04_03;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;

import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;
import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.get_yyyyMMddFromDate;
import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.get_HH_MM_fromDate;

public class MainActivity extends AppCompatActivity {

    DataBaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating Manager of DataBase:
        DataBaseManager dbm = new DataBaseManager(this);

        //Testowe ładowanie danych gdy nowa wersja bazy:
        dbm.TEST_LoadDataToDataBase();

        //TEST - kontrolne wypisanie zawartości tabeli Medicines
        dbm.TEST_TypeTableMedicines();       //Write to LOG: Content of table
        //TEST - kontrolne wypisanie zawartości tabeli TaingsPlan:
        dbm.TEST_TypeTableTakongsPlan();       //Write to LOG: Content of table
        //TEST - kontrolne wypisanie zawartości tabeli Taking:
        dbm.TEST_TypeTableTakings();       //Write to LOG: Content of table

        //Cwiczenia ze zmiennymi doobslugi daty:
        Log.d("DB", "---------------");
        Log.d("DB", "Date and time :");
        Log.d("DB", "---------------");
        Date date = new Date();
        Log.d("DB", "Date(0.ToString():" + date.toString());
        String sData = get_yyyyMMddFromDate(date);
        Log.d("DB", "get_yyyyMMddFromDate(date):" + sData);
        String sTime = get_HH_MM_fromDate(date);
        Log.d("DB", "get_HH_MM_fromDate(date):" + sTime);

        Log.d("DB", "----------------------------");
        int a=1;

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
