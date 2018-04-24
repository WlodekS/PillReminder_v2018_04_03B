package com.pum2018.pillreminder_v2018_04_03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingsPlanAdapter;
import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingsPlanViewForAdapter;
import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;


import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {


    private DataBaseManager dbm;
    // MulitiLine adapter:
    private TakingsPlanAdapter mAdapter2;

    //variables to access listView objects:
    ListView lv;


    //POPRAWIC #####!!!!
    // Zmienna pomocnicza. ID wiersza do skasowania.
    // Zmienna lokalna (w deleteMedicineFromStore) - nie działa - nie jest dostępna.
    private int schedule_id_to_Delete;


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

    @Override
    protected void onResume() {
        // Menu: Code\Generate\Override methods\-Wskazujemy metodę OnResume
        super.onResume();
        //For update ListView after Update records:
        mAdapter2.notifyDataSetChanged();
        updateUI2();
    }

    public void ScheduleDelete(View view){
        Toast.makeText(this,"Schedule Delete", Toast.LENGTH_SHORT).show();

//        //Poprawić bo żywcem przekopiowane !!!  (z DeleteMedicine!!!)######:
        View parent = (View) view.getParent();
        TextView textView_currID = (TextView) parent.findViewById(R.id.textView_current_ID);
        String id_String = String.valueOf(textView_currID.getText());
        int id_Int = Integer.parseInt(id_String);       // wartość _id jako Integer
        //Zapisujemy tą wartość do zmiennej deklarowanej w klasie:
        schedule_id_to_Delete = id_Int;



        //-----------------------------
        // Alert Dialog: Are you sure?
        //-----------------------------
        //To jest chyba w innym wątku!
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScheduleActivity.this);
        alertDialogBuilder.setMessage("Are you sure ?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Skasowanie z bazy ###
                        //Akcja musi być w tym miejscu - to jest chyba jakiś inny wątek...
                        dbm.dbDeleteTakingsPlan(schedule_id_to_Delete);
                        updateUI2();        //Refresh Interface
                        schedule_id_to_Delete = 0;  //zerowanie zmiennej - niepotrzebne, ale dla bezpieczeństwa
                    }
                });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        //-----------------------------
    }


    public void scheduleEdit(View view){
        Toast.makeText(this,"Schedule Edit", Toast.LENGTH_SHORT).show();


/*        //Aktualizacja pojedynczego rekodu:
        View parent = (View) view.getParent();
        TextView textView_currID = (TextView) parent.findViewById(R.id.textView_current_ID);
        String id_String = String.valueOf(textView_currID.getText());
        Integer id_Int = Integer.parseInt(id_String);       // wartość _id jako Integer
        Log.i("DB","Edycja TakingsPlan-u. ID: " + id_String);*/


        //============================================
        //TEST 2018.04.24 godz. 22:53:
        TextView textView_currID = (TextView) view.findViewById(R.id.textView_current_ID);
        String id_String = String.valueOf(textView_currID.getText());
        Integer id_Int = Integer.parseInt(id_String);       // wartość _id jako Integer
        Log.i("DB","Edycja TakingsPlan-u. TEST wieczorny -  ID: " + id_String);
        //=============================================


        //Wywołamy teraz nową formatkę - przekażemy do niej numer rekordu w tabeli TAKINGS_PLAN_TABLE.
        //Rekord ten bedzie w wywoływanej formatce aktualizowany
        //Help: http://hmkcode.com/android-passing-data-to-another-activities/
        Intent editScheduleIntent = new Intent(this,ScheduleNewUpdateActivity.class);

        //TEST:
        //Intent intent = new Intent(this,SettingsActivity.class);
        //startActivity(intent);

        // Put key/value data
        editScheduleIntent.putExtra("Record_ID", id_Int);
        startActivity(editScheduleIntent);
    }


}
