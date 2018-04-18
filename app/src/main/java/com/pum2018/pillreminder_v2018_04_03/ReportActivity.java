package com.pum2018.pillreminder_v2018_04_03;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingAdapter;
import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingsPlanAdapter;
import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingsPlanViewForAdapter;
import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Taking;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    TabHost tabHost;

    private DataBaseManager dbm;
    // MulitiLine adapter:
    private TakingAdapter mAdapter2;
    //variables to access listView objects:
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        TabHost host = (TabHost)findViewById(R.id.tabHostReport);

        host.setup();

        //Tab 1 - Raport 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1); //R.id.tab1 - to LinearLayout który zawiera listView
        spec.setIndicator("Raport 1");
        host.addTab(spec);

        //Tab 2 -  Raport 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Raport 2");
        host.addTab(spec);

        //Tab 3 -  Raport 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Raport 3");
        host.addTab(spec);


        //Ustawienie wielkości liter na poszczególnych sekcjach:
        Integer iSize = 16;
        //Section 0:
        TextView napisTab1 = (TextView) host.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        napisTab1.setTextSize(iSize);
        //Section 1:
        TextView napisTab2 = (TextView) host.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        napisTab2.setTextSize(iSize);
        //Section 2:
        TextView napisTab3 = (TextView) host.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        napisTab3.setTextSize(iSize);


        //initialize DataBase Manager for Activity:
        dbm = new DataBaseManager(this);

        //initialize variables to access activity objects:
        lv = (ListView)findViewById(R.id.listView_Raport1);

        updateUI2();

    }

    // Update User Interface-new version:
    private void updateUI2() {
        ArrayList<String> scheduleList = new ArrayList<>();
        SQLiteDatabase db = dbm.getReadableDatabase();

        //jako parametr do wyświetlenia potrzebna jest ArrayList-a obiektów TakingsPlanViewForAdapter (to są te scheduler-y):
        //ArrayList<Medicine> values = dbm.dbGetAllMedicines2();
        ArrayList<Taking> values = dbm.dbGetAllTakings();
        //Jest to lista wygenerowana z jednej tabelki)

        //Sprawdzone - działa!
        //mAdapter2 = new MedicineAdapter(this, values);
        //lv.setAdapter(mAdapter2);

        if (mAdapter2 == null) {
            //mAdapter2 = new MedicineAdapter_Check(this, values);
            mAdapter2 = new TakingAdapter(this, values);
            lv.setAdapter(mAdapter2);
        } else {
            mAdapter2.clear();
            mAdapter2.addAll(values);
            mAdapter2.notifyDataSetChanged();
        }
        db.close();
    }

    @Override
    protected void onResume() {
        // Menu: Code\Generate\Override methods\-Wskazujemy metodę OnResume
        super.onResume();
        //For update ListView after Update records:
        mAdapter2.notifyDataSetChanged();
        updateUI2();
    }


}
