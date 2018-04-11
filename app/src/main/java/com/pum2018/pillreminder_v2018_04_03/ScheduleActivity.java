package com.pum2018.pillreminder_v2018_04_03;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter_Check;
import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingsPlanAdapter;
import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingsPlanViewForAdapter;
import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {


    private DataBaseManager dbm;
    // MulitiLine adapter:
    private TakingsPlanAdapter mAdapter2;

    //variables to access listView objects:
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //initialize DataBase Manager for Activity:
        dbm = new DataBaseManager(this);

        //initialize variables to access activity objects:
        lv = (ListView)findViewById(R.id.listView_Schedule);

        updateUI2();

    }

    // Update User Interface-new version:
    private void updateUI2() {
        ArrayList<String> scheduleList = new ArrayList<>();
        SQLiteDatabase db = dbm.getReadableDatabase();

        //jako parametr do wyświetlenia potrzebna jest ArrayList-a obiektów TakingsPlanViewForAdapter (to są te scheduler-y):
        //ArrayList<Medicine> values = dbm.dbGetAllMedicines2();
        ArrayList<TakingsPlanViewForAdapter> values = dbm.dbGetAllTakingsPlansForListView();
        //Jest to lista wygenerowana zdówch tabelek)

        //Sprawdzone - działa!
        //mAdapter2 = new MedicineAdapter(this, values);
        //lv.setAdapter(mAdapter2);

        if (mAdapter2 == null) {
            //mAdapter2 = new MedicineAdapter_Check(this, values);
            mAdapter2 = new TakingsPlanAdapter(this, values);
            lv.setAdapter(mAdapter2);
        } else {
            mAdapter2.clear();
            mAdapter2.addAll(values);
            mAdapter2.notifyDataSetChanged();
        }
        db.close();
    }

}
