package com.pum2018.pillreminder_v2018_04_03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter;
import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter_Check;
import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wlodek on 2018-04-04.
 */

public class CheckMedicine extends AppCompatActivity {


    private DataBaseManager dbm;
    // MulitiLine adapter:
    private MedicineAdapter_Check mAdapter2;

    //variables to access listView objects:
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_medicine);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //initialize DataBase Manager for Activity:
        dbm = new DataBaseManager(this);

        //initialize variables to access activity objects:
        lv = (ListView)findViewById(R.id.list_medicine_Check);

        updateUI2();

    }

    // Update User Interface-new version:
    private void updateUI2() {
        ArrayList<String> medicineList = new ArrayList<>();
        SQLiteDatabase db = dbm.getReadableDatabase();

        //jako parametr do wyświetlenia potrzebna jest ArrayList-a obiektów Medicine:
        ArrayList<Medicine> values = dbm.dbGetAllMedicines2();

        //Sprawdzone - działa!
        //mAdapter2 = new MedicineAdapter(this, values);
        //lv.setAdapter(mAdapter2);

        if (mAdapter2 == null) {
            mAdapter2 = new MedicineAdapter_Check(this, values);
            lv.setAdapter(mAdapter2);
        } else {
            mAdapter2.clear();
            mAdapter2.addAll(values);
            mAdapter2.notifyDataSetChanged();
        }
        db.close();
    }

    public void updateMedicineInStore(View view) {

        //Aktualizacja pojedynczego rekodu:
        View parent = (View) view.getParent();
        TextView idTextView = (TextView) parent.findViewById(R.id.txt_medicine_id_check);
        String id_String = String.valueOf(idTextView.getText());
        Integer id_Int = Integer.parseInt(id_String);       // wartość _id jako Integer

        //Wywołamy teraz nową formatkę - przekażemy do niej numer rekordu w tabeli Medicine.
        //Rekord ten bedzie w wywoływanej formatce aktualizowany
        //Help: http://hmkcode.com/android-passing-data-to-another-activities/
        Intent checkMedicineIntent = new Intent(this,CheckMedicineOne.class);
        // Put key/value data
        checkMedicineIntent.putExtra("Record_ID", id_Int);
        startActivity(checkMedicineIntent);

/*        //Poprawienie danych w tabeli Medicine:
        dbm.dbDeleteMedicine(medicin_id_to_Delete);
        updateUI2();        //Refresh Interface
        medicin_id_to_Delete = 0;*/



    }

}
