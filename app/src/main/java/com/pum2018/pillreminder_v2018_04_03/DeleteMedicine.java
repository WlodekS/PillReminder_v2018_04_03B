package com.pum2018.pillreminder_v2018_04_03;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter;
import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Wlodek on 2018-04-03.
 */

public class DeleteMedicine extends AppCompatActivity{      //ListActivity

    private DataBaseManager dbm;
    private ArrayAdapter<Medicine> mAdapter;

    //adapter wielowierszowy:
    private MedicineAdapter mAdapter2;

    //variables to access activity objects:
    ListView lv;

    // Zmienna pomocnicza. Numer wiersza do skasowania.
    // Zmienna lokalna (w deleteMedicineFromStore) - nie działa - nie jest dostępna.
    private int medicin_id_to_Delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_medicine);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //initialize variables to access activity objects:
        lv = (ListView)findViewById(R.id.list_medicine_Delete);

        //initialize DataBase Manager for Activity:
        dbm = new DataBaseManager(this);

        //updateUI();
        updateUI2();

        //SQLiteDatabase db = dbm.getReadableDatabase();
        //db.close();
    }

    public void deleteMedicineToStore(View view){
        Toast.makeText(this,"Delete Medicine", LENGTH_SHORT).show();
    }

    private void updateUI() {
        ArrayList<String> medicineList = new ArrayList<>();
        SQLiteDatabase db = dbm.getReadableDatabase();
        List<Medicine> values = dbm.dbGetAllMedicines();


        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    values);
            lv.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(values);
            mAdapter.notifyDataSetChanged();
        }

        db.close();
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
            mAdapter2 = new MedicineAdapter(this, values);
            lv.setAdapter(mAdapter2);
        } else {
            mAdapter2.clear();
            mAdapter2.addAll(values);
            mAdapter2.notifyDataSetChanged();
        }
        db.close();
    }

    public void deleteMedicineFromStore(View view) {


        View parent = (View) view.getParent();
        TextView idTextView = (TextView) parent.findViewById(R.id.txt_medicine_id_del);
        String id_String = String.valueOf(idTextView.getText());
        int id_Int = Integer.parseInt(id_String);       // wartość _id jako Integer
        //Zapisujemy tą wartość do zmiennej deklarowanej w klasie:
        medicin_id_to_Delete = id_Int;


        //-----------------------------
        // Alert Dialog: Are you sure?
        //-----------------------------
        //To jest chyba w innym wątku!
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeleteMedicine.this);
        alertDialogBuilder.setMessage("Are you sure ?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Skasowanie z bazy Medicine
                                //Akcja musi być w tym miejscu - to jest chyba jakiś inny wątek...
                                dbm.dbDeleteMedicine(medicin_id_to_Delete);
                                updateUI2();        //Refresh Interface
                                medicin_id_to_Delete = 0;
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

}
