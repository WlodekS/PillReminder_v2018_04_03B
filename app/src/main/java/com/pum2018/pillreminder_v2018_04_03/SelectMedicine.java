package com.pum2018.pillreminder_v2018_04_03;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter_SelectMedicine;
import com.pum2018.pillreminder_v2018_04_03.Adapters.MedicineAdapter_StoreList;
import com.pum2018.pillreminder_v2018_04_03.Adapters.TakingAdapter;
import com.pum2018.pillreminder_v2018_04_03.DBManager.DataBaseManager;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.DataModel.Taking;

import java.util.ArrayList;


public class SelectMedicine extends AppCompatActivity {

    private DataBaseManager dbm;
    // MulitiLine adapter:
    private MedicineAdapter_SelectMedicine mAdapter2;

    //variables to access listView objects:
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_medicine);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //initialize DataBase Manager for Activity:
        dbm = new DataBaseManager(this);

        //initialize variables to access activity objects:
        lv = (ListView)findViewById(R.id.listView_SelectMedicine);

        updateUI_SelctMedicine();

    }



    // Update User Interface:
    private void updateUI_SelctMedicine() {
        ArrayList<String> scheduleList = new ArrayList<>();
        SQLiteDatabase db = dbm.getReadableDatabase();

        //jako parametr do wyświetlenia potrzebna jest ArrayList-a obiektów TakingsPlanViewForAdapter (to są te scheduler-y):
        //ArrayList<Medicine> values = dbm.dbGetAllMedicines2();
        ArrayList<Medicine> values = dbm.dbGetAllMedicines2();
        //Jest to lista wygenerowana z jednej tabelki)


        if (mAdapter2 == null) {
            //mAdapter2 = new MedicineAdapter_Check(this, values);
            mAdapter2 = new MedicineAdapter_SelectMedicine(this, values);
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
        updateUI_SelctMedicine();
    }

    public void medicine_SEL(View view){
        //finishing Activity
        //Toast.makeText(this,"Metoda: medicine_SEL", Toast.LENGTH_SHORT).show();
        //finish();
        //Aktualizacja pojedynczego rekodu:
        View parent = (View) view.getParent();
        TextView textView_currID = (TextView) parent.findViewById(R.id.tv_rs_ID);
        String id_String = String.valueOf(textView_currID.getText());
        Integer id_Int = Integer.parseInt(id_String);       // wartość _id jako Integer

        //Kończymy pracę w Activity - przekażemy zwrotnie numer rekordu w tabeli MEDICINE_TABLE.
        //odnajdujemy parent parent:
        finish();
    }
}
